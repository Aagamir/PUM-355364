fun countVowels(input: String): Int {
    var count = 0
    val vowels = mutableListOf("a", "e", "i", "o", "u", "y")
    input.forEach{char ->
        if (vowels.contains(char.lowercase())){
            count++
        }
    }
    return count
}


fun main(){
    println("Podaj słowo")
    val input = readln()
    println("Liczba samogłosek w podanym słowie: ${countVowels(input)}")
}