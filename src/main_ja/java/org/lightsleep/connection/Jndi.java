/*
	Jndi.java
	(C) 2016 Masato Kokubo
*/
package org.lightsleep.connection;

import javax.sql.DataSource;

/**
	<b>Jndi</b> は、JNDI (Java Naming and Directory Interface) API
	で取得するデータソースを使用する場合に使用します。<br>
	lightsleep.properties ファイルの以下のプロパティを参照します。<br>

	<div class="blankline">&nbsp;</div>

	<table class="additinal">
		<caption><span>lightsleep.properties の参照</span></caption>
		<tr><th>プロパティ名</th><th>内 容</th></tr>
		<tr><td>dataSource</td><td>データソースのリソース名</td></tr>
	</table>

	@since 1.1.0
	@author Masato Kokubo
*/
public class Jndi extends AbstractConnectionSupplier {
	/**
		<b>Jndi</b> を構築します。<br>
		lightsleep.properties
		ファイルで指定された値を接続情報に使用します。

		@see #Jndi(java.lang.String)
	*/
	public Jndi() {
	}

	/**
		<b>Jndi</b> を構築します。<br>
		<b>"java:/comp/env/" + dataSourceName</b> の文字列でデータソースを検索します。
		<b>dataSourceName</b> が <b>null</b> の場合、
		lightsleep.properties ファイルで指定された値を使用します。

		@param dataSourceName データソース名 (<b>null</b> 可)
	*/
	public Jndi(String dataSourceName) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	protected DataSource getDataSource() {
		return null;
	}
}
