/*
	EntityInfo.java
	(C) 2016 Masato Kokubo
*/
package org.lightsleep.helper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.lightsleep.component.Expression;
import org.lightsleep.entity.*;

/**
	Has information of an entity class.

	@param <E> the type the entity

	@since 1.0
	@author Masato Kokubo
*/
public class EntityInfo<E> {
	// The entity class 
	private final Class<E> entityClass;

	// The accessor
	private final Accessor<E> accessor;

	// The table name
	private final String tableName;

	// The column information map (key: column name)
	private final Map<String, ColumnInfo> columnInfoMap;

	// The list of information of the columns
	private final List<ColumnInfo> columnInfos;

	// The list of information of the columns which are key
	private final List<ColumnInfo> keyColumnInfos;

	/**
		Constructs a new <b>EntityInfo</b>.

		@param entityClass the entity class

		@throws NullPointerException <b>entityClass</b> is null
	*/
	public EntityInfo(Class<E> entityClass) {
		if (entityClass == null) throw new NullPointerException("EntityInfo.<init>: entityClass == null");

		this.entityClass = entityClass;
		accessor = new Accessor<>(entityClass);

		// @Table / the table name
		Class<? super E> superEntityClass = entityClass;
		String tableName = null;
		for (;;) {
			Table table = superEntityClass.getAnnotation(Table.class);
			if (table == null) {
				tableName = superEntityClass.getSimpleName();
				break;
			}
			tableName = table.value();
			if (!tableName.equals("super"))
				break;
			superEntityClass = superEntityClass.getSuperclass();
		}
		this.tableName = tableName;

	// 1.3.0
		// @KeyProperty, @KeyProperties
		Set<String> keySet = new HashSet<>();
		KeyProperty keyProperty = entityClass.getAnnotation(KeyProperty.class);
		if (keyProperty != null) keySet.add(keyProperty.value());
		KeyProperties keyProperties = entityClass.getAnnotation(KeyProperties.class);
		if (keyProperties != null)
			Arrays.stream(keyProperties.value()).forEach(annotation -> keySet.add(annotation.value()));

		// @NonSelectProperty, @NonSelectProperties
		Set<String> nonSelectSet = new HashSet<>();
		NonSelectProperty nonSelectProperty = entityClass.getAnnotation(NonSelectProperty.class);
		if (nonSelectProperty != null) nonSelectSet.add(nonSelectProperty.value());
		NonSelectProperties nonSelectProperties = entityClass.getAnnotation(NonSelectProperties.class);
		if (nonSelectProperties != null)
			Arrays.stream(nonSelectProperties.value()).forEach(annotation -> nonSelectSet.add(annotation.value()));

		// @NonInsertProperty, @NonInsertProperties
		Set<String> nonInsertSet = new HashSet<>();
		NonInsertProperty nonInsertProperty = entityClass.getAnnotation(NonInsertProperty.class);
		if (nonInsertProperty != null) nonInsertSet.add(nonInsertProperty.value());
		NonInsertProperties nonInsertProperties = entityClass.getAnnotation(NonInsertProperties.class);
		if (nonInsertProperties != null)
			Arrays.stream(nonInsertProperties.value()).forEach(annotation -> nonInsertSet.add(annotation.value()));

		// @NonUpdateProperty, @NonUpdateProperties
		Set<String> nonUpdateSet = new HashSet<>();
		NonUpdateProperty nonUpdateProperty = entityClass.getAnnotation(NonUpdateProperty.class);
		if (nonUpdateProperty != null) nonUpdateSet.add(nonUpdateProperty.value());
		NonUpdateProperties nonUpdateProperties = entityClass.getAnnotation(NonUpdateProperties.class);
		if (nonUpdateProperties != null)
			Arrays.stream(nonUpdateProperties.value()).forEach(annotation -> nonUpdateSet.add(annotation.value()));

		// @ColumnProperty, @ColumnProperties
		Map<String, String> columnMap = new HashMap<>();
		ColumnProperty columnProperty = entityClass.getAnnotation(ColumnProperty.class);
		if (columnProperty != null) columnMap.put(columnProperty.property(), columnProperty.column());
		ColumnProperties columnProperties = entityClass.getAnnotation(ColumnProperties.class);
		if (columnProperties != null)
			Arrays.stream(columnProperties.value())
				.forEach(annotation -> columnMap.put(annotation.property(), annotation.column()));

		// @SelectProperty, @SelectProperties
		Map<String, String> selectMap = new HashMap<>();
		SelectProperty selectProperty = entityClass.getAnnotation(SelectProperty.class);
		if (selectProperty != null) selectMap.put(selectProperty.property(), selectProperty.expression());
		SelectProperties selectProperties = entityClass.getAnnotation(SelectProperties.class);
		if (selectProperties != null)
			Arrays.stream(selectProperties.value())
				.forEach(annotation -> selectMap.put(annotation.property(), annotation.expression()));

		// @InsertProperty, @InsertProperties
		Map<String, String> insertMap = new HashMap<>();
		InsertProperty insertProperty = entityClass.getAnnotation(InsertProperty.class);
		if (insertProperty != null) insertMap.put(insertProperty.property(), insertProperty.expression());
		InsertProperties insertProperties = entityClass.getAnnotation(InsertProperties.class);
		if (insertProperties != null)
			Arrays.stream(insertProperties.value())
				.forEach(annotation -> insertMap.put(annotation.property(), annotation.expression()));

		// @UpdateProperty, @UpdateProperties
		Map<String, String> updateMap = new HashMap<>();
		UpdateProperty updateProperty = entityClass.getAnnotation(UpdateProperty.class);
		if (updateProperty != null) updateMap.put(updateProperty.property(), updateProperty.expression());
		UpdateProperties updateProperties = entityClass.getAnnotation(UpdateProperties.class);
		if (updateProperties != null)
			Arrays.stream(updateProperties.value())
				.forEach(annotation -> updateMap.put(annotation.property(), annotation.expression()));
	////

		columnInfoMap = new LinkedHashMap<>();

		for (String propertyName : accessor.valuePropertyNames()) {
			// the field
			Field field = accessor.getField(propertyName);

			// @Column / the column name
		// 1.3.0
		//	Column column = field.getAnnotation(Column.class);
		//	String columnName = column != null ? column.value() : field.getName();
			String columnName = columnMap.get(propertyName);
			if (columnName == null) {
				Column column = field.getAnnotation(Column.class);
				columnName = column != null ? column.value() : field.getName();
			}
		////

			// @Key / is key?
		// 1.3.0
		//	boolean isKey = field.getAnnotation(Key.class) != null;
			boolean isKey = field.getAnnotation(Key.class) != null
				|| keySet.contains(propertyName);
		////

			// @NonSelect
		// 1.3.0
		//	boolean nonSelect = field.getAnnotation(NonSelect.class) != null;
			boolean nonSelect = field.getAnnotation(NonSelect.class) != null
				|| nonSelectSet.contains(propertyName);
		////

			// @Select
		// 1.3.0
		//	Select select = field.getAnnotation(Select.class);
		//	Expression selectExpression = nonSelect
		//			? null
		//			: select == null
		//				? Expression.EMPTY
		//				: new Expression(select.value());
			String selectString = selectMap.get(propertyName);
			if (selectString == null) {
				Select select = field.getAnnotation(Select.class);
				if (select != null) selectString = select.value();
			}
			Expression selectExpression = selectString != null
				? new Expression(selectString)
				: nonSelect ? null : Expression.EMPTY;
		////

			// @NonInsert / the expression to be used to create INSERT SQL
		// 1.3.0
		//	boolean nonInsert = field.getAnnotation(NonInsert.class) != null;
			boolean nonInsert = field.getAnnotation(NonInsert.class) != null
				|| nonInsertSet.contains(propertyName);
		////

			// @Insert
		// 1.3.0
		//	Insert insert = field.getAnnotation(Insert.class);
		//	Expression insertExpression = nonInsert
		//				? null
		//				: insert == null
		//					? new Expression("{#" + propertyName + "}")
		//					: new Expression(insert.value());
			String insertString = insertMap.get(propertyName);
			if (insertString == null) {
				Insert insert = field.getAnnotation(Insert.class);
				if (insert != null) insertString = insert.value();
			}
			Expression insertExpression = insertString != null
				? new Expression(insertString)
				: nonInsert ? null : new Expression("{#" + propertyName + "}");
		////

			// @NonUpdate
		// 1.3.0
		//	boolean nonUpdate = field.getAnnotation(NonUpdate.class) != null;
			boolean nonUpdate = field.getAnnotation(NonUpdate.class) != null
				|| nonUpdateSet.contains(propertyName);
		////

			// @Update / the expression to be used to create UPDATE SQL
		// 1.3.0
		//	Update update = field.getAnnotation(Update.class);
		//	Expression updateExpression = nonUpdate
		//			? null
		//			: update == null
		//				? new Expression("{#" + propertyName + "}")
		//				: new Expression(update.value());
			String updateString = updateMap.get(propertyName);
			if (updateString == null) {
				Update update = field.getAnnotation(Update.class);
				if (update != null) updateString = update.value();
			}
			Expression updateExpression = updateString != null
				? new Expression(updateString)
				: nonUpdate ? null : new Expression("{#" + propertyName + "}");
		////

			ColumnInfo columnInfo = new ColumnInfo(this, propertyName, columnName, isKey, selectExpression, insertExpression, updateExpression);
			columnInfoMap.put(propertyName, columnInfo);
		}

		columnInfos = columnInfoMap.values().stream().collect(Collectors.toList());

		keyColumnInfos = columnInfos.stream().filter(columnInfo -> columnInfo.isKey()).collect(Collectors.toList());
	}

	/**
		Returns the entity class.

		@return the entity class
	*/
	public Class<E> entityClass() {
		return entityClass;
	}

	/**
		Returns the accessor.

		@return the accessor
	*/
	public Accessor<E> accessor() {
		return accessor;
	}

	/**
		Returns the table name.

		@return the table name
	*/
	public String tableName() {
		return tableName;
	}

	/**
		Returns the column information.

		@param propertyName the property name

		@return the column information

		@throws NullPointerException if <b>propertyName</b> is <b>null</b>
		@throws IllegalArgumentException if the column information related to <b>propertyName</b> can not be found
	*/
	public ColumnInfo getColumnInfo(String propertyName) {
		if (propertyName == null) throw new NullPointerException("EntityInfo.getColumnInfo: propertyName == null");

		ColumnInfo columnInfo = columnInfoMap.get(propertyName);
		if (columnInfo == null)
			throw new IllegalArgumentException(
				"EntityInfo.getColumnInfo: propertyName = " + propertyName
				+ ", entityClass = " + entityClass
				+ ", columnInfoMap.keySet = " + columnInfoMap.keySet()
				);

		return columnInfo;
	}

	/**
		Returns the list of information of the columns.

		@return the list of information of the columns
	*/
	public List<ColumnInfo> columnInfos() {
		return columnInfos;
	}

	/**
		Returns the list of information of the columns which are key

		@return the list of information of the columns
	*/
	public List<ColumnInfo> keyColumnInfos() {
		return keyColumnInfos;
	}
}
