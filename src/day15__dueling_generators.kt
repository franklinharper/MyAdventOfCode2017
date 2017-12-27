package day15_dueling_generators

val separator = "\\s+".toRegex()

class Generator(val factor: Int, var previous: Long, val multiple: Long) {
    fun next(): Long {
        while (true) {
            val next = (previous * factor) % 2147483647;
            previous = next
            if( next % multiple == 0L) {
                return next
            }
        }
    }
}

fun advMatches(a: Long, b: Long): Boolean {
    return (a and 0xFFFF) == (b and 0xFFFF)
}

fun main(args: Array<String>) {

    val input = fullInput

    val lines = input.reader().readLines()
    val initialA = Integer.parseInt(lines[0].split(separator)[4]).toLong()
    val generatorA = Generator(16807, initialA, 4)
    val initialB = Integer.parseInt(lines[1].split(separator)[4]).toLong()
    val generatorB = Generator(48271, initialB, 8)
    var matchCount = 0
//    for (i in 0..40000000) {
    for (i in 0..5000000) {
        val a = generatorA.next()
        val b = generatorB.next()
        if (advMatches(a, b)) {
            println("match $i")
            println(String.format("%x",a))
            println(String.format("%x",b))
            matchCount++
        }
    }
    println("matchCount: $matchCount")
}

    val testInput1 = """
Generator A starts with 65
Generator B starts with 8921
""".trimIndent()

    val fullInput = """
Generator A starts with 873
Generator B starts with 583
""".trimIndent()