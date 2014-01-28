


import language.experimental.macros
import reflect.macros.Context


object Timeinspector {



  @inline
  def currentTime(): Long = System.currentTimeMillis()


  def watchImpl[A:c.WeakTypeTag](c:Context)(a:c.Expr[A]):c.Expr[A] = {
     import c.universe._

    val text = c.Expr[String](Literal(Constant(show(a.tree))))

    a.tree match  {
      case Block(xs,x) =>
    }



    reify {
       val startTime = currentTime()
       val result = a.splice
       println(text.splice + (currentTime() - startTime)/1000 + "s")
       result
     }


  }


  def watch[A](a:A):A = macro watchImpl[A]


}
