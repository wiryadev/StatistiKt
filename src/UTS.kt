import kotlin.math.pow

fun main() {
    // No 1
    calculateFx(
        listOf(5, 13, 20, 18, 4),
        listOf(45.5, 55.5, 65.5, 75.5, 85.5)
    )
}

fun calculateFx(
    f: List<Int>,
    x: List<Double>,
) {
    val fx = f.mapIndexed { index, i ->
        i * x[index]
    }
    val fxPow = List(fx.size) { index ->
        f[index] * x[index].pow(2)
    }

    fx.forEachIndexed { index, d ->
        println("${f[index]}\t${x[index]}\t$d\t${fxPow[index]}")
    }

    println("Total Fx: ${fx.sum()}")
    println("Total F(x²): ${fxPow.sum()}")
}