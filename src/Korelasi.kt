import kotlin.math.sqrt

fun main() {

    // Sample
//    val x = arrayOf(90, 100, 100, 95, 105, 110, 105, 105, 115, 120)
//    val y = arrayOf(70, 75, 80, 80, 85, 85, 85, 90, 95, 100)

    // Sample 2
//    val x = arrayOf(1, 2, 3, 4, 4, 5, 6, 6, 7, 8, 8, 9)
//    val y = arrayOf(1, 4, 2, 4, 6, 2, 3, 7, 5, 4, 9, 8)

    // Task
    val x = listOf(
        15, 20, 25, 16, 12, 26, 22, 14, 24, 18,
        27, 21, 11, 26, 17, 19, 22, 13, 24, 15,
        19, 21, 15, 25, 14, 23, 16, 22, 13, 19
    )
    val y = listOf(
        35, 39, 41, 33, 32, 44, 38, 34, 40, 37,
        44, 37, 31, 41, 36, 35, 40, 34, 42, 36,
        37, 41, 38, 42, 32, 39, 34, 43, 31, 41
    )

    val xPow = x.map { it * it }
    val yPow = y.map { it * it }

    val xy = List(x.size) { index ->
        x[index] * y[index]
    }

    println("X² : $xPow")
    println("Y²: $yPow")
    println("XY: $xy")
    println()

    println("=================")
    println("Tabel Korelasi")
    println("=================")

    println("\t\tX\t\tY\t\tXY\t\tX²\t\tY²")
    x.forEachIndexed { i, _ ->
        val additionalTabX2 = if (xy[i] < 1000) "\t" else ""
        println("\t\t${x[i]}\t\t${y[i]}\t\t${xy[i]}\t$additionalTabX2${xPow[i]}\t\t${yPow[i]}")
    }
    println("Total:\t${x.sum()}\t\t${y.sum()}\t${xy.sum()}\t${xPow.sum()}\t${yPow.sum()}")
    println()

    val n = x.size
    val totalXy = xy.sum()
    println(
        """
            r =     ${n}($totalXy) - (${x.sum()})(${y.sum()})
                -----------------------------------------
                 √$n(${xPow.sum()})-(${x.sum()})² * √$n(${yPow.sum()})-(${y.sum()})²
        """.trimIndent()
    )
    println()

    val sigmaXy = n * totalXy
    val sigmaXSigmaY = x.sum() * y.sum()
    val nSigmaXPow = n * xPow.sum()
    val sigmaXPowed = x.sum() * x.sum()
    val nSigmaYPow = n * yPow.sum()
    val sigmaYPowed = y.sum() * y.sum()
    println(
        """
            r =     $sigmaXy - $sigmaXSigmaY
                -----------------------------------------
                 √$nSigmaXPow-$sigmaXPowed * √$nSigmaYPow-$sigmaYPowed
        """.trimIndent()
    )
    println()

    val topOperation = sigmaXy - sigmaXSigmaY
    val firstLowerOperation = nSigmaXPow - sigmaXPowed
    val secondLowerOperation = nSigmaYPow - sigmaYPowed
    println(
        """
            r =     $topOperation
                -----------------------------------------
                 √$firstLowerOperation * √$secondLowerOperation
        """.trimIndent()
    )
    println()

    val lowerOperation = sqrt(firstLowerOperation.toDouble()) * sqrt(secondLowerOperation.toDouble())
    println(
        """
            r =     $topOperation
                -----------------------------------------
                    $lowerOperation
        """.trimIndent()
    )
    println()

    val finalResult = topOperation / lowerOperation
    println(
        """
            r = $finalResult
        """.trimIndent()
    )

}