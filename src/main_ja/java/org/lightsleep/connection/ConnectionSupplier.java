/*
	ConnectionSupplier.java
	(C) 2016 Masato Kokubo
*/
package org.lightsleep.connection;

import java.sql.Connection;
import java.util.function.Supplier;

/**
	データベース・コネクションのサプライヤのインタフェースです。

	@since 1.0.0
	@author Masato Kokubo
*/
public interface ConnectionSupplier extends Supplier<Connection> {
}
