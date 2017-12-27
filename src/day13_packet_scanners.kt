package day13_packet_scanners

val separator = ":\\s*".toRegex()

class Scanner(val depth: Int, val range: Int, var position: Int = 0, var direction: Int = 1) {
    fun catches(picoseconds:Int):Boolean {
        val result = picoseconds % ((range - 1) * 2) == 0
        return result
    }
}

fun main(args: Array<String>) {

    val input = fullInput

    var lastScanner: Scanner? = null
    val scanners: HashMap<Int, Scanner> = hashMapOf()
    input.reader().forEachLine { line ->
        val (depth, range) = line.split(separator).map { Integer.parseInt(it) }
        scanners[depth] = Scanner(depth, range)
        lastScanner = scanners[depth]
    }
    println("scanners: $scanners")

    val lastLayer = lastScanner!!.depth
//    var severity = 0
    var initialDelay = 0
    var picoseconds: Int
    do {
        println("Trying with initialDelay: $initialDelay")
        var caught = false
        picoseconds = initialDelay
        println("Starting penetration")
        for (layer in 0..lastLayer) {
            val scanner = scanners[layer]
            if (scanner is Scanner) {
                if (scanner.catches(picoseconds)) {
                    println("CAUGHT by scanner")
                    caught = true
                    break
                }
            }
            picoseconds++
        }
        initialDelay++
    } while (caught)
    println("delay without getting caught: ${initialDelay - 1}")
    println("picoseconds to get through: $picoseconds")
}

val testInput1 = """
    0: 3
    1: 2
    4: 4
    6: 4
""".trimIndent()

val fullInput = """
0: 3
1: 2
2: 4
4: 4
6: 5
8: 6
10: 6
12: 8
14: 6
16: 6
18: 8
20: 12
22: 8
24: 8
26: 9
28: 8
30: 8
32: 12
34: 20
36: 10
38: 12
40: 12
42: 10
44: 12
46: 12
48: 12
50: 12
52: 12
54: 14
56: 14
58: 12
62: 14
64: 14
66: 14
68: 14
70: 14
72: 14
74: 14
76: 14
78: 14
80: 18
82: 17
84: 14
""".trimIndent()

