fun suma(list: List<Int>): Int{
    return list
        .filter{it > 0}
        .sum()
}
fun main(){
    println(suma(listOf( 1, -4, 12, 0, -3, 29, -150)))
}