/*
	MySQL.java
	Copyright (c) 2016 Masato Kokubo
*/
package org.mkokubo.lightsleep.database;

/**
	<a href="http://www.mysql.com/" target="MySQL">MySQL</a>
	用のデータベース・ハンドラーです。<br>

	このクラスのオブジェクトは、
	{@linkplain org.mkokubo.lightsleep.helper.TypeConverter#typeConverterMap}
	に以下の <b>TypeConverter</b> を追加した <b>TypeConverter</b> マップを持ちます。

	<table class="additinal">
		<caption>登録されている TypeConverter オブジェクト</caption>
		<tr><th>変換元データ型</th><th>変換先データ型</th></tr>
		<tr><td >boolean</td><td>{@linkplain org.mkokubo.lightsleep.component.SqlString} (0, 1)</td></tr>
		<tr><td >String </td><td>{@linkplain org.mkokubo.lightsleep.component.SqlString} (エスケープ・シーケンス対応)</td></tr>
	</table>

	@since 1.0.0
	@author Masato Kokubo
*/
public class MySQL extends Standard {
	/**
		<b>MySQL</b> オブジェクトを返します。

		@return MySQL オブジェクト
	*/
	public static Database instance() {
		return null;
	}

	/**
		<b>MySQL</b> を構築します。
	*/
	protected MySQL() {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public boolean isEnableOffset() {
		return true;
	}
}
