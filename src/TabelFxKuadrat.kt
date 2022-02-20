import java.math.BigDecimal
import kotlin.math.*

fun main() {

    // Sample
//    val listData = listOf(
//        79, 49, 48, 74, 81, 98, 87, 80,
//        80, 84, 90, 70, 91, 93, 82, 78,
//        70, 71, 92, 38, 56, 91, 74, 73,
//        68, 72, 85, 53, 65, 93, 83, 86,
//        90, 31, 83, 73, 74, 43, 86, 68,
//        92, 93, 76, 71, 90, 72, 67, 75,
//        80, 91, 61, 72, 97, 91, 88, 81,
//        70, 74, 99, 95, 80, 59, 71, 77,
//        63, 60, 83, 82, 60, 67, 89, 63,
//        76, 63, 88, 70, 66, 80, 79, 75,
//    ).sorted()

    // No 1
//    val listData = listOf(
//        70, 85, 60, 70, 75, 65,
//        80, 95, 55, 70, 75, 60,
//        50, 80, 70, 75, 55, 65,
//        75, 60, 65, 70, 85, 90,
//        70, 60, 75, 65, 85, 75,
//        55, 80, 70, 65, 80, 75,
//        60, 70, 80, 85, 65, 75,
//        60, 80, 65, 90, 75, 70,
//        65, 70
//    ).sorted()

    // No 2
    val listData = listOf(
        90, 95, 110, 80, 95,
        105, 100, 85, 115, 100,
        110, 85, 90, 105, 95,
        90, 110, 85, 100, 105,
        95, 110, 90, 110, 90,
        95, 90
    ).sorted()

//    // No 3
//    val listData = listOf(
//        5, 4, 9, 0, 5, 6, 10, 2, 4, 8, 1, 7, 5, 9, 3, 4, 8, 6, 2, 5, 3, 7, 4, 7, 5, 3, 6, 5, 6, 4
//    ).sorted()

    val xMin = listData.first()
    val xMax = listData.last()
    val range = xMax - xMin

    val k = (1 + 3.322 * log10(listData.size.toFloat()))
    val kRounded = k.roundToInt()

//    val intervalLength = (range.toDouble() / kRounded).roundToInt() + 1
//    val intervalLength = (range.toDouble() / kRounded).roundToInt()
    val intervalLength = ceil((range.toDouble() / kRounded)).toInt()

    println("Banyak data: ${listData.size}")
    println("Nilai terendah: $xMin")
    println("Nilai tertinggi: $xMax")
    println("Jangkauan : $range")
    println("Banyak kelas interval: $k ≈ $kRounded")
    println("Panjang kelas interval: $intervalLength")

    fun getDistributedData(min: Int, max: Int): List<Int> {
        return listData.filter { it in min..max }
    }

    val valueLevels = mutableListOf<List<Int>>()
    var currentLastValue = xMin
    for (i in 1..kRounded) {
        val maxIntervalValue = currentLastValue + (intervalLength - 1)
        valueLevels.add(
            (currentLastValue..maxIntervalValue).toList()
        )
        currentLastValue = maxIntervalValue + 1
    }
    println(valueLevels)

    val mappedDistribution = mutableMapOf<Pair<Int, Int>, List<Int>>()

    valueLevels.forEachIndexed { _, values ->
        val intervalRange = Pair(values.first(), values.last())
        mappedDistribution[intervalRange] = getDistributedData(values.first(), values.last())
    }

    var totalDataSize = 0
    var totalFx = 0.0
    var totalFxPow = 0.0

    println()
    println("=================")
    println("Tabel Distribusi")
    println("=================")

    println("Nilai\tf\tMedian\tfX\t\tf*X2\t\ttData")
    mappedDistribution.forEach {
        val intervalTitle = "${it.key.first}-${it.key.second}"
        val freq = it.value.size
        val median = (it.key.first + it.key.second) / 2.0
        val fx = median * freq
        val fx2 = freq * median.pow(2.0)

        println("$intervalTitle\t$freq\t$median\t$fx\t$fx2\t\t${it.value}")
        totalDataSize += freq
        totalFx += fx
        totalFxPow += fx2
    }
    println("Total:\t$totalDataSize\t\t\t$totalFx\t$totalFxPow")
    println()

    val sigmaTotalFxPowed = totalFx.pow(2.0)
    val variance = (totalFxPow - (sigmaTotalFxPowed / totalDataSize)) / totalDataSize
//    val variance = (476650.0 - (6070.0.pow(2.0) / 80)) / 80
    println(
        """
            Variansi = $totalFxPow - ($totalFx^2/$totalDataSize)
                       ---------------------------
                                $totalDataSize
        """.trimIndent()
    )
    println(
        """
            Variansi = $totalFxPow - (${BigDecimal(sigmaTotalFxPowed).toPlainString()}/$totalDataSize)
                       ---------------------------
                                $totalDataSize
        """.trimIndent()
    )
    println(
        """
            Variansi = $totalFxPow - ${sigmaTotalFxPowed / totalDataSize}
                       ---------------------------
                                $totalDataSize
        """.trimIndent()
    )
    println(
        """
            Variansi = ${totalFxPow - sigmaTotalFxPowed / totalDataSize}
                       ---------------------------
                                $totalDataSize
        """.trimIndent()
    )
    println("Variansi = $variance")

    println()
    println("Simpangan Baku = ${sqrt(variance)}")

    println(BigDecimal(6070.0.pow(2)).toPlainString())
}