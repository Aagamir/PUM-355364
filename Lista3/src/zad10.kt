data class Point(val x: Int, val y: Int) {

    // p1 + p2
    operator fun plus(other: Point) = Point(this.x + other.x, this.y + other.y)

    // p1 += 1
    operator fun plus(scalar: Int) = Point(this.x + scalar, this.y + scalar)

    // p1 - p2
    operator fun minus(other: Point) = Point(this.x - other.x, this.y - other.y)

    // p1 * p2
    operator fun times(other: Point) = Point(this.x * other.x, this.y * other.y)

    // p1++
    operator fun inc() = Point(this.x + 1, this.y + 1)

    // p1--
    operator fun dec() = Point(this.x - 1, this.y - 1)

    // !p1
    operator fun not() = Point(-this.x, -this.y)
}

fun main() {
    var p1 = Point(1, 1)
    val p2 = Point(2, 2)

    println(p1 + p2)

    p1 += 1
    println(p1)

    p1 = Point(1, 1)

    println(p1 - p2)
    println(p1 * p2)

    p1++
    println(p1)

    p1 = Point(1, 1)
    p1--
    println(p1)

    p1 = Point(1, 1)
    println(!p1)
}