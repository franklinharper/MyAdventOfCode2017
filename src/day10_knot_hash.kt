package day10_knot_hash

val separator = ",\\s*".toRegex()

val testInput = ""
val fullInput = "34,88,2,222,254,93,150,0,199,255,39,32,137,136,1,167"

fun main(args: Array<String>) {
    val NUM_ELEMENTS = 256
    val input = fullInput

    var numbers = MutableList<Int>(NUM_ELEMENTS) { it }

    val inputLengths = input.map { it.toInt() }
    val lengths = inputLengths + listOf<Int>(17, 31, 73, 47, 23)
    println("lengths: $lengths")

    var currentPosition = 0
    var skipSize = 0

    for (round in 0..63) {
        lengths.forEach() { length ->
            println("currentPosition: $currentPosition length:$length")
            println("numbers: $numbers")
            var index = currentPosition
            val subList = List<Int>(length) {
                numbers[index++ % numbers.size]
            }
            println("subList: $subList")
            val reversed = subList.reversed()
            println("reversed: $reversed")
            var reverseIndex = currentPosition
            reversed.forEach() {
                numbers[reverseIndex++ % numbers.size] = it
            }
            currentPosition += length + skipSize
            skipSize++
        }
    }
    val sparseHashes = numbers.toList()
//    val sparseHashes = listOf<Int>(
//            65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22,
//            65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 23
//    )
    println("sparseHash: $sparseHashes")
    val denseHashes = MutableList(16) { 0 }
    for (sparseIndex in 0 until sparseHashes.size) {
        val denseIndex = sparseIndex / 16
        denseHashes[denseIndex] = denseHashes[denseIndex] xor sparseHashes[sparseIndex]
    }

//    val denseHashes = listOf<Int>(64, 7, 255)
    println("denseHashes: $denseHashes")

    var hexString = ""
    denseHashes.forEach() {
        hexString += String.format("%02x", it)
    }
    println("hexString: $hexString")

}
