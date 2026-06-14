package Lista1

fun foo(num: Int): String  {
    var result = ""
    if (num % 3 == 0)
        result += ("trzy")
    if (num % 5 == 0)
        result += ("piec")
    else if (num % 3 != 0 && num % 5 != 0)
        result = num.toString()
    return result
}

fun main() {
    val num = 15
    for (i in 1..num) {
        println(foo(i))
    }
}