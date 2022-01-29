import kotlin.math.log10
import kotlin.math.roundToInt

fun main() {

    val listData = listOf(
        45, 67, 78, 80, 43, 35, 75, 64, 87, 54,
        54, 65, 72, 82, 48, 38, 82, 64, 73, 56,
        63, 75, 70, 65, 47, 78, 80, 67, 72, 44,
        70, 56, 75, 70, 78, 55, 58, 64, 70, 75,
        58, 67, 70, 75, 82, 58, 38, 72, 78, 67,
    ).sorted()

    val xMin = listData.first()
    val xMax = listData.last()
    val range = xMax - xMin

    val k = (1 + 3.322 * log10(listData.size.toFloat()))
    val kRounded = k.roundToInt()

    val intervalLength = (range / k).roundToInt()

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
        val maxIntervalValue = currentLastValue + kRounded
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

    println()
    println("=================")
    println("Tabel Distribusi")
    println("=================")

    println("Nilai\tMedian\ttBawah\ttAtas\tFrekuensi\tData")
    mappedDistribution.forEach {
        val intervalTitle = "${it.key.first}-${it.key.second}"
        val median = (it.key.first + it.key.second) / 2.0
        val lower = it.key.first - 0.5
        val upper = it.key.second + 0.5
        val freq = it.value.size
        println("$intervalTitle\t$median\t$lower\t$upper\t$freq\t\t\t${it.value}")
        totalDataSize += it.value.size
    }
    println("Total:\t\t\t\t\t\t\t$totalDataSize")
}