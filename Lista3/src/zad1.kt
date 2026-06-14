fun findDuplicates(list: List<Int>): Set<Int>{
    var duplicates = mutableListOf<Int>()
    var counter = 0
    for (i in 0 .. list.lastIndex) {
        var cnt = true
        for (j in 0..list.lastIndex) {

            if (list[i] == list[j]) {
                counter++
            }
            if (counter >= 2 && cnt == true) {
                duplicates.add(list[i])
                cnt = false
            }
        }
        counter = 0
    }
    return duplicates.sorted().toSet()
}

fun main(){
    val lst = listOf(0, 1, 1, 1, 4, 4, 4, 9, 3, 3, 3, 3, 3, 3)
    println(findDuplicates(lst))
}