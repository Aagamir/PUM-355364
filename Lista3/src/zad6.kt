fun perm(list: List<Int>): List<List<Int>> {
    if (list.size <= 1) return listOf(list)

    return list.flatMap { element ->
        perm(list - element).map { listOf(element) + it }
    }
}

fun main() {
    println(perm(listOf(1, 2,3 )))
}