/*
	KeyProperty.java
	(C) 2016 Masato Kokubo
*/
package org.lightsleep.entity;

import java.lang.annotation.*;

/**
	プロパティに関連するカラムがプライマリー・キーの一部である事を示します。<br>
	フィールドは、プロパティ名で指定します。

	<div class="sampleTitle"><span>使用例</span></div>
<div class="sampleCode"><pre>
<b>{@literal @}KeyProperty("contactId")</b>
<b>{@literal @}KeyProperty("childIndex")</b>
public class Phone {
  public int contactId;
  public short childIndex;
</pre></div>

	<div class="sampleTitle"><span>SQL</span></div>
<div class="sampleCode"><pre>
UPDATE Phone ... WHERE <b>contactId=100 AND childIndex=0</b>
</pre></div>

	@since 1.3.0
	@see Key
	@see KeyProperties
	@author Masato Kokubo
*/
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(KeyProperties.class)
@Target({ElementType.TYPE})
public @interface KeyProperty {
	/** @return プロパティ名 */
	String value();
}
