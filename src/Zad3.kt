fun generatePascalsTriangle(n: Int): List<List<Int>> {
    val triangle = mutableListOf<List<Int>>()

    if (n <= 0) return triangle

    triangle.add(listOf(1))

    for (i in 1 until n) {
        val previousRow = triangle[i - 1]
        val currentRow = mutableListOf<Int>()

        currentRow.add(1)

        for (j in 1 until i) {
            currentRow.add(previousRow[j - 1] + previousRow[j])
        }

        currentRow.add(1)

        triangle.add(currentRow)
    }

    return triangle
}

fun printTriangle(triangle: List<List<Int>>) {
    val n = triangle.size
    for ((index, row) in triangle.withIndex()) {
        val padding = " ".repeat(n - index)
        println(padding + row.joinToString(" "))
    }
}

fun main() {
    println("Podaj wysokość trójkąta")
    val height = readln().toInt()
    val pascalsTriangle = generatePascalsTriangle(height)
    printTriangle(pascalsTriangle)
}