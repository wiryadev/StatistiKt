fun main() {
    // sample data
//    val x1 = listOf(6, 5, 6, 6, 5, 5, 5, 6, 4, 4)
//    val x1Pow = calculatePow(x1)
//
//    val x2 = listOf(6, 6, 7, 8, 6, 6, 5, 6, 5, 6)
//    val x2Pow = calculatePow(x2)
//
//    val x3 = listOf(7, 8, 8, 9, 7, 6, 5, 6, 7, 7)
//    val x3Pow = calculatePow(x3)

    // No 1
//    val x1 = listOf(7, 5, 8, 6, 5, 7, 5, 6, 6, 7)
//    val x1Pow = calculatePow(x1)
//
//    val x2 = listOf(5, 6, 7, 8, 9, 6, 7, 7, 5, 8)
//    val x2Pow = calculatePow(x2)
//
//    val x3 = listOf(9, 8, 8, 7, 7, 9, 8, 6, 7, 7)
//    val x3Pow = calculatePow(x3)
//
//    val xt = sumThreeList(listOf(x1, x2, x3))
//    val allXPow = sumThreeList(listOf(x1Pow, x2Pow, x3Pow))
//
//    println("X1\tX1²\t\tX2\tX2²\t\tX3\tX3²\t\tXt\tX1²+X2²+X3²")
//    x1.forEachIndexed { i, _ ->
//        println("${x1[i]}\t${x1Pow[i]}\t\t${x2[i]}\t${x2Pow[i]}\t\t${x3[i]}\t${x3Pow[i]}\t\t${xt[i]}\t${allXPow[i]}")
//    }
//    println("${x1.sum()}\t${x1Pow.sum()}\t\t${x2.sum()}\t${x2Pow.sum()}\t\t${x3.sum()}\t${x3Pow.sum()}\t\t${xt.sum()}\t${allXPow.sum()}")

    // No 2
    val x1 = listOf(9, 7, 8, 7, 8, 7, 5, 9, 8, 8, 8, 7)
    val x1Pow = calculatePow(x1)

    val x2 = listOf(7, 8, 7, 8, 6, 7, 5, 6, 6, 8, 7)
    val x2Pow = calculatePow(x2)

    val x3 = listOf(6, 8, 8, 6, 6, 6, 5, 6, 7, 6)
    val x3Pow = calculatePow(x3)

    val xt = mutableListOf<Int>()
    val allXPow = mutableListOf<Int>()

    x3.forEachIndexed { i, _ ->
        val summed = x1[i] + x2[i] + x3[i]
        val powSummed = x1Pow[i] + x2Pow[i] + x3Pow[i]
        xt.add(summed)
        allXPow.add(powSummed)
    }

    println("X1\tX1²\t\tX2\tX2²\t\tX3\tX3²\t\tXt\tX1²+X2²+X3²")
    x3.forEachIndexed { i, _ ->
        println("${x1[i]}\t${x1Pow[i]}\t\t${x2[i]}\t${x2Pow[i]}\t\t${x3[i]}\t${x3Pow[i]}\t\t${xt[i]}\t${allXPow[i]}")
    }
    println("${x1.sum()}\t${x1Pow.sum()}\t\t${x2.sum()}\t${x2Pow.sum()}\t\t${x3.sum()}\t${x3Pow.sum()}\t\t${xt.sum()}\t${allXPow.sum()}")

}

private fun calculatePow(numbers: List<Int>): List<Int> {
    val powList = mutableListOf<Int>()
    numbers.forEach {
        powList.add(it * it)
    }
    return powList
}

private fun sumThreeList(nestedList: List<List<Int>>): List<Int> {
    val initialList = mutableListOf<Int>()
    repeat(nestedList[0].size) {
        initialList.add(0)
    }
    return nestedList.fold(initialList.toList()) { x, y ->
        x.zip(y, Int::plus)
    }
}