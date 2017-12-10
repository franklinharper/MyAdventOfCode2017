class day4 {

fun main(args: Array<String>) {

    val input = """
    abcde fghij
    abcde xyz ecdab
    a ab abc abd abf abj
    iiii oiii ooii oooi oooo
    oiii ioii iioi iiio
    """.trimIndent()

    var validPassPhrases = 0
    input.reader().forEachLine { passPhrase ->
        if (isValidPassPhrase(passPhrase)) validPassPhrases++
    }
    println("validPassPhrases: $validPassPhrases")

}

fun isValidPassPhrase(phrase: String): Boolean {
    val reader = phrase.reader()
    val whitespace = "\\s".toRegex()
    var passPhraseWords = HashSet<List<Char>>()

    for (word in phrase.split(whitespace)) {
        val sortedChars = word.toList().sorted()
        if (sortedChars in passPhraseWords) {
            return false
        } else {
            passPhraseWords.add(sortedChars)
        }
    }
    return true
}

}
