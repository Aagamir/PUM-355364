import java.time.format.DateTimeFormatter
import java.time.LocalDate

interface CostFormatter {
    fun format(cost: Cost): String
}

// Singleton implementujący interfejs
object PlCostFormatter : CostFormatter {
    override fun format(cost: Cost): String {
        // Używamy DateTimeFormatter, aby uzyskać dwucyfrowy dzień (DD)
        val day = cost.date.format(DateTimeFormatter.ofPattern("dd"))
        return "$day ${cost.type} ${cost.amount} zł"
    }
}

fun formatCosts(costs: List<Cost>, formatter: CostFormatter): String {
    return costs
        .sortedBy { it.date }
        .map { formatter.format(it) }
        .joinToString("\n")
}

fun main() {
    val costs = listOf(
        Cost(CostType.PARKING, LocalDate.of(2025, 1, 15), 30),
        Cost(CostType.SERVICE, LocalDate.of(2025, 1, 5), 900)
    )

    println(formatCosts(costs, PlCostFormatter))
}