
import language.experimental.macros

import reflect.macros.Context


case class Query(s:String)

object TreeCleaner {
  def treeFormatter(s:String):String = {


    val stringBuilder= new StringBuilder
    var identLevel  = 0

    var skip = false

    def ident = "\n" + (" " * identLevel )


    def tryIdent() {
      if (identLevel % 4 == 0 && !skip) {
        stringBuilder.append(ident)
      }
    }


    for(c <- s.toCharArray) {
      c match  {
        case '(' => {
          identLevel = identLevel +1
          stringBuilder.append("(")
          skip = true
          tryIdent()
        }

        case ')' => {
          identLevel = identLevel -1
          //tryIdent()
          stringBuilder.append(")")
        }
        case ','  => {
          stringBuilder.append(ident + ",")
        }
        case x => {
          skip = false
          stringBuilder.append(x.toString)
        }
      }

    }

    stringBuilder.toString()
  }

  def outputPrefix[A](a:A):Unit = macro outputPrefixImpl[A]

  def outputPrefixImpl[A: c.WeakTypeTag](c: Context)(a: c.Expr[A]): c.Expr[Unit] = {

    val s = c.Expr[String](c.universe.Literal(c.universe.Constant(c.universe.showRaw(c.prefix.tree))   ))
    c.universe.reify(
      println(treeFormatter(s.splice))
    )
  }


  def query(s:String):Query = macro queryValidationImpl

  def queryValidationImpl(c:Context)(s:c.Expr[String]) = {

    import c.universe._
    val Literal(Constant(s_query: String)) = s.tree
     
    println(showRaw(s))
     println(s_query)
    c.universe.reify (
        new Query(s.splice)
    )
  }


  def outputExpr[A](a:A):Unit = macro outputExprImpl[A]


  def outputExprImpl[A: c.WeakTypeTag]
  (c: Context)
             (a: c.Expr[A]): c.Expr[Unit] = {

    val s = c.Expr[String](c.universe.Literal(
      c.universe.Constant(
        c.universe.showRaw(a.tree)
      )
    )
    )
    c.universe.reify(
      println(treeFormatter(s.splice))
    )

  }


}

case class Plouf(content:String) {

  def apply(addToContent:String) = Plouf(content + addToContent)

  def inspect[A](a:A) = macro TreeCleaner.outputPrefixImpl[A]
}
