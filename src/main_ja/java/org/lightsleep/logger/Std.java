/*
	StdLogger.java
	Copyright (c) 2016 Masato Kokubo
*/

package org.lightsleep.logger;

import java.io.PrintStream;
import java.util.function.Supplier;

/**
	標準出力または標準エラー出力にログを出力します。

	@since 1.0.0
	@author Masato Kokubo
*/
public abstract class Std implements Logger {
	/**
		標準出力にログを出力します。
	*/
	public static abstract class Out extends Std {
		/**
			<b>Std.Out</b> を構築します。
			@param level the log level
		*/
		public Out(Level level) {
			super(System.out, level);
		}

		/**
			標準出力に TRACE レベルでログを出力します。
		*/
		public static class Trace extends Out {
			/**
				<b>Std.Out.Trace</b> を構築します。
				@param name 名前 (使用しない)
			*/
			public Trace(String name) {super(Level.TRACE);}
		}

		/**
			標準出力に DEBUG レベルでログを出力します。
		*/
		public static class Debug extends Out {
			/**
				<b>Std.Out.Debug</b> を構築します。
				@param name 名前 (使用しない)
			*/
			public Debug(String name) {super(Level.DEBUG);}
		}

		/**
			標準出力に INFO レベルでログを出力します。
		*/
		public static class Info extends Out {
			/**
				<b>Std.Out.Info</b> を構築します。
				@param name 名前 (使用しない)
			*/
			public Info (String name) {super(Level.INFO);}
		}

		/**
			標準出力に WARN レベルでログを出力します。
		*/
		public static class Warn extends Out {
			/**
				<b>Std.Out.Warn</b> を構築します。
				@param name 名前 (使用しない)
			*/
			public Warn (String name) {super(Level.WARN);}
		}

		/**
			標準出力に ERROR レベルでログを出力します。
		*/
		public static class Error extends Out {
			/**
				<b>Std.Out.Error</b> を構築します。
				@param name 名前 (使用しない)
			*/
			public Error(String name) {super(Level.ERROR);}
		}

		/**
			標準出力に FATAL レベルでログを出力します。
		*/
		public static class Fatal extends Out {
			/**
				<b>Std.Out.Fatal</b> を構築します。
				@param name 名前 (使用しない)
			*/
			public Fatal(String name) {super(Level.FATAL);}
		}
	}

	/**
		標準エラー出力にログを出力します。
	*/
	public static abstract class Err extends Std {
		/**
			<b>Std.Err</b> を構築します。
			@param level the log level
		*/
		public Err(Level level) {
			super(System.err, level);
		}

		/**
			標準エラー出力に TRACE レベルでログを出力します。
		*/
		public static class Trace extends Err {
			/**
				<b>Std.Err.Trace</b> を構築します。
				@param name 名前 (使用しない)
			*/
			public Trace(String name) {super(Level.TRACE);}
		}

		/**
			標準エラー出力に DEBUG レベルでログを出力します。
		*/
		public static class Debug extends Err {
			/**
				<b>Std.Err.Debug</b> を構築します。
				@param name 名前 (使用しない)
			*/
			public Debug(String name) {super(Level.DEBUG);}
		}

		/**
			標準エラー出力に INFO レベルでログを出力します。
		*/
		public static class Info extends Err {
			/**
				<b>Std.Err.Info</b> を構築します。
				@param name 名前 (使用しない)
			*/
			public Info (String name) {super(Level.INFO);}
		}

		/**
			標準エラー出力に WARN レベルでログを出力します。
		*/
		public static class Warn extends Err {
			/**
				<b>Std.Err.Warn</b> を構築します。
				@param name 名前 (使用しない)
			*/
			public Warn (String name) {super(Level.WARN);}
		}

		/**
			標準エラー出力に ERROR レベルでログを出力します。
		*/
		public static class Error extends Err {
			/**
				<b>Std.Err.Error</b> を構築します。
				@param name 名前 (使用しない)
			*/
			public Error(String name) {super(Level.ERROR);}
		}

		/**
			標準エラー出力に FATAL レベルでログを出力します。
		*/
		public static class Fatal extends Err {
			/**
				<b>Std.Err.Fatal</b> を構築します。
				@param name 名前 (使用しない)
			*/
			public Fatal(String name) {super(Level.FATAL);}
		}
	}

	/** ロガーレベル */
	protected enum Level {TRACE, DEBUG, INFO, WARN, ERROR, FATAL}

	/**
		<b>Std</b> を構築します。

		@param stream PrintStream
		@param level ログレベル
	*/
	protected Std(PrintStream stream, Level level) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void trace(String message) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void debug(String message) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void info(String message) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void warn(String message) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void error(String message) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void fatal(String message) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void trace(String message, Throwable t) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void debug(String message, Throwable t) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void info(String message, Throwable t) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void warn(String message, Throwable t) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void error(String message, Throwable t) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void fatal(String message, Throwable t) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void trace(Supplier<String> messageSupplier) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void debug(Supplier<String> messageSupplier) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void info(Supplier<String> messageSupplier) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void warn(Supplier<String> messageSupplier) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void error(Supplier<String> messageSupplier) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public void fatal(Supplier<String> messageSupplier) {
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public boolean isTraceEnabled() {
		return false;
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public boolean isDebugEnabled() {
		return false;
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public boolean isInfoEnabled() {
		return false;
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public boolean isWarnEnabled() {
		return false;
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public boolean isErrorEnabled() {
		return false;
	}

	/**
		{@inheritDoc}
	*/
	@Override
	public boolean isFatalEnabled() {
		return false;
	}
}