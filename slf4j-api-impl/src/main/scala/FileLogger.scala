import java.io.PrintWriter

import org.slf4j.event.Level
import org.slf4j.{Logger, Marker}

/**
  * Created by menzelmi on 27.09.16.
  */
case class FileLogger(name: String, level: Level, fileName: String) extends Logger {

  lazy val file = new PrintWriter(fileName)

  override def warn(msg: String): Unit = file.print(generateOutput(Level.WARN, msg))

  override def warn(format: String, arg: scala.Any): Unit = throw new NotImplementedError()

  override def warn(format: String, arguments: AnyRef*): Unit = throw new NotImplementedError()

  override def warn(format: String, arg1: scala.Any, arg2: scala.Any): Unit = throw new NotImplementedError()

  override def warn(msg: String, t: Throwable): Unit = warn(mergeMsgWithThrowable(msg, t))

  override def warn(marker: Marker, msg: String): Unit = warn(msg)

  override def warn(marker: Marker, format: String, arg: scala.Any): Unit = throw new NotImplementedError()

  override def warn(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = throw new NotImplementedError()

  override def warn(marker: Marker, format: String, arguments: AnyRef*): Unit = throw new NotImplementedError()

  override def warn(marker: Marker, msg: String, t: Throwable): Unit = warn(mergeMsgWithThrowable(msg, t))

  override def isErrorEnabled: Boolean = level.toInt >= Level.ERROR.toInt

  override def isErrorEnabled(marker: Marker): Boolean = isErrorEnabled

  override def isInfoEnabled: Boolean = level.toInt >= Level.INFO.toInt

  override def isInfoEnabled(marker: Marker): Boolean = isInfoEnabled

  override def getName: String = name

  override def isDebugEnabled: Boolean = level.toInt >= Level.DEBUG.toInt

  override def isDebugEnabled(marker: Marker): Boolean = isDebugEnabled

  override def isTraceEnabled: Boolean = level.toInt >= Level.TRACE.toInt

  override def isTraceEnabled(marker: Marker): Boolean = isTraceEnabled

  override def error(msg: String): Unit = file.print(generateOutput(Level.ERROR, msg))

  override def error(format: String, arg: scala.Any): Unit = throw new NotImplementedError()

  override def error(format: String, arg1: scala.Any, arg2: scala.Any): Unit = throw new NotImplementedError()

  override def error(format: String, arguments: AnyRef*): Unit = throw new NotImplementedError()

  override def error(msg: String, t: Throwable): Unit = error(mergeMsgWithThrowable(msg, t))

  override def error(marker: Marker, msg: String): Unit = error(msg)

  override def error(marker: Marker, format: String, arg: scala.Any): Unit = throw new NotImplementedError()

  override def error(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = throw new NotImplementedError()

  override def error(marker: Marker, format: String, arguments: AnyRef*): Unit = throw new NotImplementedError()

  override def error(marker: Marker, msg: String, t: Throwable): Unit = error(mergeMsgWithThrowable(msg, t))

  override def debug(msg: String): Unit = file.print(generateOutput(Level.DEBUG, msg))

  override def debug(format: String, arg: scala.Any): Unit = throw new NotImplementedError()

  override def debug(format: String, arg1: scala.Any, arg2: scala.Any): Unit = throw new NotImplementedError()

  override def debug(format: String, arguments: AnyRef*): Unit = throw new NotImplementedError()

  override def debug(msg: String, t: Throwable): Unit = debug(mergeMsgWithThrowable(msg, t))

  override def debug(marker: Marker, msg: String): Unit = debug(msg)

  override def debug(marker: Marker, format: String, arg: scala.Any): Unit = throw new NotImplementedError()

  override def debug(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = throw new NotImplementedError()

  override def debug(marker: Marker, format: String, arguments: AnyRef*): Unit = throw new NotImplementedError()

  override def debug(marker: Marker, msg: String, t: Throwable): Unit = debug(mergeMsgWithThrowable(msg, t))

  override def isWarnEnabled: Boolean = level.toInt >= Level.WARN.toInt

  override def isWarnEnabled(marker: Marker): Boolean = isWarnEnabled

  override def trace(msg: String): Unit = file.print(generateOutput(Level.TRACE, msg))

  override def trace(format: String, arg: scala.Any): Unit = throw new NotImplementedError()

  override def trace(format: String, arg1: scala.Any, arg2: scala.Any): Unit = throw new NotImplementedError()

  override def trace(format: String, arguments: AnyRef*): Unit = throw new NotImplementedError()

  override def trace(msg: String, t: Throwable): Unit = trace(mergeMsgWithThrowable(msg, t))

  override def trace(marker: Marker, msg: String): Unit = trace(msg)

  override def trace(marker: Marker, format: String, arg: scala.Any): Unit = throw new NotImplementedError()

  override def trace(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = throw new NotImplementedError()

  override def trace(marker: Marker, format: String, argArray: AnyRef*): Unit = throw new NotImplementedError()

  override def trace(marker: Marker, msg: String, t: Throwable): Unit = trace(mergeMsgWithThrowable(msg, t))

  override def info(msg: String): Unit = file.print(generateOutput(Level.INFO, msg))

  override def info(format: String, arg: scala.Any): Unit = throw new NotImplementedError()

  override def info(format: String, arg1: scala.Any, arg2: scala.Any): Unit = throw new NotImplementedError()

  override def info(format: String, arguments: AnyRef*): Unit = throw new NotImplementedError()

  override def info(msg: String, t: Throwable): Unit = info(mergeMsgWithThrowable(msg, t))

  override def info(marker: Marker, msg: String): Unit = info(msg)

  override def info(marker: Marker, format: String, arg: scala.Any): Unit = throw new NotImplementedError()

  override def info(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = throw new NotImplementedError()

  override def info(marker: Marker, format: String, arguments: AnyRef*): Unit = throw new NotImplementedError()

  override def info(marker: Marker, msg: String, t: Throwable): Unit = info(mergeMsgWithThrowable(msg, t))


  private def generateOutput(level: Level, msg: String) = s"${level.name()}: $msg"

  private def mergeMsgWithThrowable(msg: String, t: Throwable) = s"$msg - ${t.getMessage}"

}
