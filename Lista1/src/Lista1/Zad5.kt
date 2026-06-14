package Lista1

import kotlin.math.pow

fun isArmstrong(input: Int, cyfry: MutableList<Int>): Boolean {
    var sum = 0
    val intLength = input.toString().length
    for (i in intLength downTo 1){
        var power = 10.0.pow(i-1).toInt()
        var number = (input /power) % 10
        sum += number.toDouble().pow(intLength).toInt()
        cyfry.add(number)
    }
    if (sum == input){
        println("Podana liczba jest liczbą Armstronga")
        return true
    }
    else{
        println("Podana liczba nie jest liczbą Armstronga")
        return false
    }
}

fun main(){
    val cyfry = mutableListOf<Int>()
    println("Podaj liczbę")
    val input = readln().toInt()
    isArmstrong(input, cyfry)

}