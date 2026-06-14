val testList = listOf(1, 2, 3, 4, 5, 16)

fun <T> List<T>.tail(): List<T>{
    val list_tail = this.toMutableList()
    list_tail.removeAt(0)
    return list_tail
}

fun <T> List<T>.head(): T{
    val list_head = this.toMutableList().removeAt(0)
    return list_head
}

fun main(){
    println(testList.tail())
    println(testList.head())
}