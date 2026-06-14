fun check(n: Int, list: List<Int>): Int {
    for (i in n until list.size) {
        val target = list[i]
        val preamble = list.subList(i - n, i)
        var isValid = false

        for (j in 0 until preamble.size) {
            for (k in j + 1 until preamble.size) {
                if (preamble[j] + preamble[k] == target) {
                    isValid = true
                    break
                }
            }
            if (isValid) break
        }

        if (!isValid) {
            return target
        }
    }

    return -1
}

fun main() {
    val testList = listOf(1, 2, 3, 4, 5, 16)
    println(check(2, testList))

    val bigList = listOf(35, 25, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576)
    println(check(5, bigList))
}