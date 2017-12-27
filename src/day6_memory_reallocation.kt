package day6_memory_reallocation

fun main(args: Array<String>) {

//    val input = "0 2 7 0"
    val input = "5	1	10	0	1	7	13	14	3	12	8	10	7	12	0	6"
    val whitespace = "\\s".toRegex()
    var passPhraseWords = HashSet<List<Char>>()

//    for (word in phrase.split(whitespace)) {

    var memoryBanks = ArrayList<Int>()
    input.split(whitespace).map { memoryBanks.add(Integer.parseInt(it)) }
    var history = HashSet<ArrayList<Int>>()
    var loop = false
    var cycles = 0
    while (!loop) {
        history.add(ArrayList(memoryBanks))
        var max = memoryBanks.max()
        var index = memoryBanks.indexOf(max)
        memoryBanks[index] = 0
        while (max!! > 0) {
            index = (index + 1) % memoryBanks.size
            memoryBanks[index]++
            max--
        }
        cycles++
        println("cycles:$cycles $memoryBanks")
        if (memoryBanks in history) {
            loop = true
        }
    }
    history = HashSet<ArrayList<Int>>()
    loop = false
    cycles = 0
    while (!loop) {
        history.add(ArrayList(memoryBanks))
        var max = memoryBanks.max()
        var index = memoryBanks.indexOf(max)
        memoryBanks[index] = 0
        while (max!! > 0) {
            index = (index + 1) % memoryBanks.size
            memoryBanks[index]++
            max--
        }
        cycles++
        println("cycles:$cycles $memoryBanks")
        if (memoryBanks in history) {
            loop = true
        }
    }
}