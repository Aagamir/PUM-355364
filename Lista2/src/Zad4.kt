fun safeParseAndClassify(input: String?): String{
    if (input.isNullOrEmpty()) return "BRAK_DANYCH"
    return input.toIntOrNull()?.let {
        if (it % 2 == 0) "PARZYSTA" else "NIEPARZYSTA"
    } ?: "BŁĄD_FORMATU"
}

fun main() {
    println(safeParseAndClassify(input = null))
}
