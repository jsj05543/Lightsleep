/*
	NonUpdateProperty.java
	(C) 2016 Masato Kokubo
*/
package org.lightsleep.entity;

import java.lang.annotation.*;

/**
	フィールドに関連するカラムが UPDATE SQL で使用されない事を示します。<br>
	フィールドは、プロパティ名で指定します。

	<div class="sampleTitle"><span>使用例</span></div>
<div class="sampleCode"><pre>
{@literal @}InsertProperty(property="created", expression="CURRENT_TIMESTAMP")
<b>{@literal @}NonUpdateProperty("created")</b>
public class Contact {

  public Timestamp created;
</pre></div>

	@since 1.3.0
	@see NonUpdate
	@see NonUpdateProperties
	@author Masato Kokubo
*/
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NonUpdateProperties.class)
@Target({ElementType.TYPE})
public @interface NonUpdateProperty {
	/** @return プロパティ名 */
	String value();
}
