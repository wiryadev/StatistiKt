fun main() {
    val x = listOf(90, 100, 100, 95, 105, 110, 105, 105, 115, 120)
    val y = listOf(70, 75, 80, 80, 85, 85, 85, 90, 95, 100)

    val xPow = x.map { it * it }
    val yPow = y.map { it * it }

    println("=================")
    println("Tabel Korelasi")
    println("=================")

    var totalXy = 0
    println("\t\tX\t\tY\t\tXY\t\tX2\t\tY2")
    x.forEachIndexed { i, item ->
        val xy = item * y[i]
        totalXy += xy

        println("\t\t$item\t\t${y[i]}\t\t$xy\t${xPow[i]}\t${yPow[i]}")
    }
    println("Total:\t${x.sum()}\t${y.sum()}\t\t$totalXy\t${xPow.sum()}\t${yPow.sum()}")
    println()
}