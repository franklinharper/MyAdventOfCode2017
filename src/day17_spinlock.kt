package day17_spinlock

import java.util.*

class Spinlock(val iterations: Int, val stepsPerIteration: Int) {


    fun calculate(): Pair<Int, List<Int>> {
        val start = System.currentTimeMillis()
        val values: LinkedList<Int> = LinkedList()
        values.add(0)
        var currentIndex = 0
        for (iteration in 1..iterations) {
            val nextIndex = (currentIndex + stepsPerIteration) % values.size + 1
            values.add(nextIndex, iteration)
            currentIndex = nextIndex
        }
        val elapsedSeconds = (System.currentTimeMillis() - start) / 1000.0
        println("iterations: $iterations, stepsPerIteration: $stepsPerIteration, elapsedSeconds: $elapsedSeconds")
        println("values: $values")
        return Pair<Int, List<Int>>(currentIndex, values)
    }
}

class Spinlock2(val iterations: Int, val stepsPerIteration: Int) {

    fun calculate(test:Boolean): Pair<Int, List<Int>> {
        val start = System.currentTimeMillis()
        val values: LinkedList<Int> = LinkedList()
        values.add(0)
        var currentIndex = 0
        var zeroIndex = 0
        var answer = -1
        for (iteration in 1..iterations) {
            val nextIndex = (currentIndex + stepsPerIteration) % iteration + 1
            if (test) { values.add(nextIndex, iteration) }
            if (nextIndex <= zeroIndex ) {
                zeroIndex++
                println("zeroIndex: $zeroIndex, iteration: $iteration")
                if (test) { println("values: $values") }
            }
            if (nextIndex == zeroIndex + 1 ) {
                answer = iteration
                println("update after zero. value: $answer, zeroIndex: $zeroIndex")
                if (test) {
                    println("values: $values")
                    val realValue = values[values.indexOf(0) + 1]
                    if (realValue != iteration) { throw IllegalStateException() }
                }
            }
            currentIndex = nextIndex
        }
        val elapsedSeconds = (System.currentTimeMillis() - start) / 1000.0
        println("iterations: $iterations, stepsPerIteration: $stepsPerIteration, elapsedSeconds: $elapsedSeconds")
        println("answer: $answer")
        return Pair<Int, List<Int>>(answer, values)
    }
}

fun main(args: Array<String>) {

    runTests()

    val firstAnswer = firstAnswer(2017, 337)
    println("first answer: $firstAnswer")

//    Spinlock2(50000, 337).calculate(test = true)
    Spinlock2(50000000, 337).calculate(test = false)
}

private fun runTests() {
    tests.forEachIndexed { index, function ->
        function();
        println("test $index passed")
    }
    println("All tests passed!")
}

fun firstAnswer(iterations: Int, stepsPerIteration: Int): Int {
    var (finalIndex, values) = Spinlock(iterations, stepsPerIteration).calculate()
    return values[finalIndex + 1]
}

val tests = listOf<() -> Unit>(
        {
            var (finalIndex, values) = Spinlock(1, 3).calculate()
            if (finalIndex != 1) {
                println("$finalIndex")
                throw IllegalStateException()
            }
            if (values != listOf(0, 1)) {
                println("$values")
                throw IllegalStateException()
            }
        },

        {
            var (finalIndex, values) = Spinlock(2, 3).calculate()
            if (finalIndex != 1) {
                println("$finalIndex")
                throw IllegalStateException()
            }
            if (values != listOf(0, 2, 1)) {
                println("$values")
                throw IllegalStateException()
            }
        },

        {
            var (finalIndex, values) = Spinlock(3, 3).calculate()
            if (finalIndex != 2) {
                println("$finalIndex")
                throw IllegalStateException()
            }
            if (values != listOf(0, 2, 3, 1)) {
                println("$values")
                throw IllegalStateException()
            }
        },

        {
            var (finalIndex, values) = Spinlock(2017, 3).calculate()
            val partialList = values.subList(finalIndex - 3, finalIndex + 4)
            if (partialList != listOf(1512, 1134, 151, 2017, 638, 1513, 851)) {
                println("$partialList")
                throw IllegalStateException()
            }
        },
        {
            val answer = firstAnswer(2017, 3)
            if (answer != 638) {
                println("$answer")
                throw IllegalStateException()
            }
        }
)