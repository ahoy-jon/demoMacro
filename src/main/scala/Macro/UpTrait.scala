package Macro


import language.experimental.macros
import language.higherKinds

import reflect.macros.Context


object UpTrait {

  type Id[X] = X

  type Opt[X] = Option[X]

  def compileTimeParseInt(c: Context)(s: c.Expr[String]): c.Expr[Int] = {
    import c.{universe => u}
    import u._
    s.tree match {
      case Literal(Constant(string: String)) =>

        val int = Integer.parseInt(string)
        c.Expr[Int](Literal(Constant(int)))
    }
  }


}


trait UpTrait[M[_]] {


  def parseInt(s:String):M[Int]
}








