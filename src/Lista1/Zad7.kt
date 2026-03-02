package Lista1
fun sumEven(input: Int): Int{
    var sum = 0
    for (i in 1 .. input)
        if (i % 2 == 0) {
            sum += i
        }
    return(sum)
}

fun main(){
    println("Podaj liczbę")
    val input = readln().toInt()
    println(sumEven(input))
}