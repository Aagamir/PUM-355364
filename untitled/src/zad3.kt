import java.time.Month
import java.time.LocalDate


// funkcja klasyfikujaca
fun classifyMonthlyCosts(costs: List<Cost>, month: Month, limit: Int): MonthlyCostStatus {
    val total = costs
        .filter { it.date.month == month }
        .sumOf { it.amount }

    return when {
        total == 0 -> MonthlyCostStatus.NoCosts
        total <= limit -> MonthlyCostStatus.WithinLimit(total)
        else -> MonthlyCostStatus.OverLimit(total, exceededBy = total - limit)
    }
}

fun main() {
    val costs = listOf(
        Cost(CostType.REFUELING, LocalDate.of(2025, 1, 10), 300),
        Cost(CostType.PARKING, LocalDate.of(2025, 1, 12), 50),
        Cost(CostType.SERVICE, LocalDate.of(2025, 2, 4), 1200)
    )

    println(classifyMonthlyCosts(costs, Month.JANUARY, 400))
    println(classifyMonthlyCosts(costs, Month.FEBRUARY, 1000))
    println(classifyMonthlyCosts(costs, Month.MARCH, 500))
}