
fun isPerfect(input: Int, dividers: MutableList<Int>): Int{
    var sum = 0
    for (i in 1..input-1){
        if (input % i == 0){
            dividers.add(i)
            sum += i
        }
    }
    return sum
}

fun main(){
        val dividers = mutableListOf<Int>()
        println("Podaj liczbę")
        val input = readln().toInt()
        val result = isPerfect(input, dividers)
        if (result < input) {
            println("Suma akwilowa podanej liczby to $result co oznacza, że jest to liczba deficytowa")
        } else if (result == input) {
            println("Suma akwilowa podanej liczby to $result co oznacza, że jest to liczba doskonała")
        } else if (result > input) {
            println("Suma akwilowa podanej liczby to $result co oznacza, że jest to liczba nadmiarowa")
        }

}