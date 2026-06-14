import java.time.Month

fun groupedCostMap(list: List<Cost>): Map<Month, List<Cost>>{
    return list
        .groupBy { it.date.month }
        .toSortedMap()
}


fun main() {
    val pogrupowane = groupedCostMap(DataProvider.generalCosts)

    pogrupowane.forEach { (miesiac, koszty) ->
        println("$miesiac")
        koszty.forEach { koszt ->
            println("$koszt")
        }
    }
}