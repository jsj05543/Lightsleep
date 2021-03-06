/*
	NonUpdate.java
	(C) 2016 Masato Kokubo
*/
package org.lightsleep.entity;

import java.lang.annotation.*;

/**
	Indicates that the column related the field is not used in UPDATE SQL.

	<div class="sampleTitle"><span>Example of use</span></div>
<div class="sampleCode"><pre>
public class Contact {

 {@literal @}Insert("CURRENT_TIMESTAMP")<b>{@literal @}NonUpdate</b>
  public Timestamp created;
</pre></div>

	@since 1.0.0
	@see NonUpdateProperty
	@see NonUpdateProperties
	@author Masato Kokubo
*/
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface NonUpdate {
}
