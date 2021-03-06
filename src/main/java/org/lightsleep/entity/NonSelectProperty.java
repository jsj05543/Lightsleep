/*
	NonSelectProperty.java
	(C) 2016 Masato Kokubo
*/
package org.lightsleep.entity;

import java.lang.annotation.*;

/**
	Indicates that the column related the field is not used in SELECT SQL.<br>
	Specifies the field by the property name.

	<div class="sampleTitle"><span>Example of use</span></div>
<div class="sampleCode"><pre>
<b>{@literal @}NonSelectProperty("created")</b>
{@literal @}InsertProperty(property="created", expression="CURRENT_TIMESTAMP")
{@literal @}NonUpdateProperty("created")
<b>{@literal @}NonSelectProperty("modified")</b>
{@literal @}InsertProperty(property="modified", expression="CURRENT_TIMESTAMP")
{@literal @}UpdateProperty(property="modified", expression="CURRENT_TIMESTAMP")
public class Contact {

  public Timestamp created;
  public Timestamp modified;
</pre></div>

	@since 1.3.0
	@see NonSelect
	@see NonSelectProperties
	@author Masato Kokubo
*/
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NonSelectProperties.class)
@Target({ElementType.TYPE})
public @interface NonSelectProperty {
	/** @return the property name */
	String value();
}
