/*
	Insert.java
	(C) 2016 Masato Kokubo
*/
package org.lightsleep.entity;

import java.lang.annotation.*;

/**
	Indicates the expression instead of the field value used in INSERT SQL.

	<div class="sampleTitle"><span>Example of use</span></div>
<div class="sampleCode"><pre>
public class Contact {

 <b>{@literal @}Insert("CURRENT_TIMESTAMP")</b>
  public Timestamp created;

 <b>{@literal @}Insert("CURRENT_TIMESTAMP")</b>
  public Timestamp modified;
</pre></div>

	<div class="sampleTitle"><span>SQL</span></div>
<div class="sampleCode"><pre>
INSERT INTO Contact (..., <b>created</b>, <b>modified</b>) VALUES (..., <b>CURRENT_TIMESTAMP</b>, <b>CURRENT_TIMESTAMP</b>)
</pre></div>

	@since 1.0.0
	@see InsertProperty
	@see InsertProperties
	@author Masato Kokubo
*/
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Insert {
	/** @return the expression */
// 1.2.0
//	String value() default "";
	String value();
////
}
