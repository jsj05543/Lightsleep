/*
	NonInsertProperty.java
	(C) 2016 Masato Kokubo
*/
package org.lightsleep.entity;

import java.lang.annotation.*;

/**
	Indicates that the column related the field is not used in INSERT SQL.<br>
	Specifies the field by the property name.

	<div class="sampleTitle"><span>Example of use</span></div>
<div class="sampleCode"><pre>
{@literal @}KeyProperty("id")<b>{@literal @}NonInsertProperty("id")</b>
public class Contact {
  public int id;
</pre></div>

	@since 1.3.0
	@see NonInsert
	@see NonInsertProperties
	@author Masato Kokubo
*/
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NonInsertProperties.class)
@Target({ElementType.TYPE})
public @interface NonInsertProperty {
	/** @return the property name */
	String value();
}
