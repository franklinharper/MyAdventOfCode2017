package day3_spiral_memory

fun main(args: Array<String>) {

    val input = """
            0
            3
            0
            1
            -3
""".trimIndent()

    input.reader().forEachLine {
        println("it: $it")
    }
}