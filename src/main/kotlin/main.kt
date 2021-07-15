import java.io.File

fun main(args: Array<String>) {
    firstPart()
    secondPart()

    firstPartBetterSolution()
    secondPartBetterSolution()
}

private fun firstPart() {
    val numbers = File("src/main/resources/input.txt")
        .readLines()
        .map(String::toInt)

    for (first in numbers) {
        for (second in numbers) {
            if (first + second == 2020) {
                println("$first, $second")
                println(first * second)
                return
            }
        }
    }
}

private fun secondPart() {
    val numbers = File("src/main/resources/input.txt")
        .readLines()
        .map(String::toInt)

    for (first in numbers) {
        for (second in numbers) {
            for (third in numbers) {
                if (first + second + third == 2020) {
                    println("$first, $second, $third")
                    println(first * second * third)
                    return
                }
            }
        }
    }
}

private fun firstPartBetterSolution() {
    val numbers = File("src/main/resources/input.txt")
        .readLines()
        .map(String::toInt)
    val pair = numbers.findPairOfSum(2020)
    println(pair)

    //if (pair != null) println(pair.first * pair.second)
    println(pair?.let { (x, y) -> x * y })
}

private fun secondPartBetterSolution() {
    val numbers = File("src/main/resources/input.txt")
        .readLines()
        .map(String::toInt)

    val pair = numbers.findPairOfSum(2020)
    println(pair)
    //println(pair.first * pair.second)

    val complementPairs: Map<Int, Pair<Int, Int>?> =
        numbers.associateWith { x ->
            numbers.findPairOfSum(2020 - x)
    }
    //println(complementPairs)

    val triple = numbers.findTripleOfSum()
    println(triple)
    println(triple?.let { (x, y, z) -> x * y * z })
}

fun List<Int>.findTripleOfSum(): Triple<Int, Int, Int>? =
    firstNotNullOfOrNull { x ->
        findPairOfSum(2020 - x)?.let { pair ->
            Triple(x, pair.first, pair.second)
        }
}

fun List<Int>.findPairOfSum(sum: Int): Pair<Int, Int>? {
    val complements = associateBy { sum - it }
    //
    return firstNotNullOfOrNull { number ->
        complements[number]?.let { complement ->
            Pair(number, complement)
        }
    }
}