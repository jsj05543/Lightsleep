/*
	ColumnProperty.java
	(C) 2016 Masato Kokubo
*/
package org.lightsleep.entity;

import java.lang.annotation.*;

/**
	Indicates the column name associated with the field.<br>
	Specifies the field by the property name.

	<div class="sampleTitle"><span>Example of use</span></div>
<div class="sampleCode"><pre>
<b>{@literal @}ColumnProperty(property="familyName" column="family_name")</b>
<b>{@literal @}ColumnProperty(property="givenName" column="given_name")</b>
public class Contact {

  public String familyName;
  public String givenName;
</pre></div>

	<div class="sampleTitle"><span>SQL</span></div>
<div class="sampleCode"><pre>
SELECT ..., <b>family_name</b>, <b>given_name</b>, ... FROM Contact
</pre></div>

	@since 1.3.0
	@see Column
	@see ColumnProperties
	@author Masato Kokubo
*/
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ColumnProperties.class)
@Target({ElementType.TYPE})
public @interface ColumnProperty {
	/** @return the property name */
	String property();

	/** @return the column name */
	String column();
}
