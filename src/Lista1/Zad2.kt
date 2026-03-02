package Lista1

fun palindrome(input: String) {
    val input_rev = input.reversed()
    if (input.lowercase() == input_rev.lowercase()) {
        println("Podane słowo jest palindromem")
    }
    else
        println("Podane słowo nie jest palindromem")
}

fun main(){
    println("Podaj słowo")
    val input = readln()
    palindrome(input)
}