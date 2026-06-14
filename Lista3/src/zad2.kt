fun addToBoolean():Map<Int, Boolean>{
    var map = HashMap<Int, Boolean>()
    for (i in 1..20 ) {
        map.put(i,i%2==0)
    }
    return map
}

fun main(){
    println(addToBoolean())
}