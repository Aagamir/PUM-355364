fun foo(num: Int): String  {
    if (num % 3 == 0)
        result += ("trzy")
    if (num % 5 == 0)
        result += ("piec")
    else if (num % 3 != 0 && num % 5 != 0)
        result = num.toString()
    return result
}

fun main() {
    for (i in 1..num) {
        println(foo(i))
        result = ""
    }
}