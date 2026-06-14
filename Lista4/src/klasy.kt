import java.time.LocalDate
import kotlin.random.*

object DataProvider {
    val generalCosts = List(20) {
        Cost(
            CostType
                .values()[Random.nextInt(CostType.values().size)],
            LocalDate.of(
                2025,
                Random.nextInt(1,13),
                Random.nextInt(1,28)),
            Random.nextInt(5000)
        )
    }
}

data class Cost (
    val type: CostType,
    val date: LocalDate,
    val amount: Int
)

enum class CostType(val costType: String) {
    REFUELING("Tankowanie"),
    SERVICE("Serwis"),
    PARKING("Parking"),
    INSURANCE("Ubezpieczenie"),
    TICKET("Mandat")
}

// Definicja sealed class (zadanie 3)
sealed class MonthlyCostStatus {
    object NoCosts : MonthlyCostStatus() {
        override fun toString(): String = "NoCosts"
    }

    data class WithinLimit(val total: Int) : MonthlyCostStatus()

    data class OverLimit(val total: Int, val exceededBy: Int) : MonthlyCostStatus()
}


