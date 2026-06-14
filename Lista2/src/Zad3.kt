import kotlin.reflect.typeOf

fun <A> isSorted(list: List<A>, order: (A, A)  -> Boolean): Boolean{
    for (i in 0 .. list.lastIndex-1) {
        if (!order(list[i], list[i+1])) {
            return false
        }
    }
    return true
}

fun main(){
    println(isSorted(listOf(1, 2, 7, 4), {i: Int, j: Int -> i < j}))
    println(isSorted(listOf(1, 2, 1, 1), {i: Int, j: Int -> i==j}))
    println(isSorted(listOf("ahyyhh", "klkjn", "cnn", "duu"), {i: String, j: String -> i.first() < j.first()}))
}