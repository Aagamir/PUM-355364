package Lista1
fun isPrime(input: Int, dividers: MutableList<Int>){
    var sum = 0
    for (i in 1..input-1){
        if (input % i == 0){
            dividers.add(i)
            sum += i
        }
    }
    if (sum == 1) {
        println("Podana liczba jest liczbą pierwszą")
    } else {
        println("Podana liczba nie jest liczbą pierwszą")
    }
}

fun main(){
    val dividers = mutableListOf<Int>()
    println("Podaj liczbę")
    val input = readln().toInt()
    isPrime(input, dividers)


}