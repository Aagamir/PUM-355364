import java.time.Month
//naprawic formatterem

fun groupedCosts(list: List<Cost>): Map<Month, List<Cost>>{
    return list
        .groupBy { it.date.month }
        .toSortedMap()
        .mapValues { entry ->
            entry.value.sortedBy { it.date.dayOfMonth }
        }
}

fun main() {
    val pogrupowane = groupedCostMap(DataProvider.generalCosts)

    pogrupowane.forEach { (miesiac, koszty) ->
        println("$miesiac")
        koszty.forEach { koszt ->
            println("${koszt.date.dayOfMonth} ${koszt.type} ${koszt.amount} zł")
        }
    }
}