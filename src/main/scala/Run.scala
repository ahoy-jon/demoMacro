
import language.experimental.macros
import Macro.UpTrait


class CompileTimeParseInt {

  def parseInt(s: String): Int = macro UpTrait.compileTimeParseInt
}


class RuntimeParseInt {

  def parseInt(s: String): Option[Int] = {
    import scala.util.control.Exception._

    allCatch opt Integer.parseInt(s)
  }
}


