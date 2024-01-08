fun main(args: Array<String>) {
   println(convertStringToSum(inputData))
}

fun convertStringToSum(data: List<String>): Int {
    var total = 0
    for (line in data) {
        val lineChange = convertWordToNumber(line, numbersDict)
        val numbersOnly = extractDigits(lineChange)
        val number = "".plus(numbersOnly.first()).plus(numbersOnly.last()).toInt()
        total += number
    }
    return total
}

fun extractDigits(line: String): String {
    var onlyDigits = ""
    for (char in line) {
        if (char.isDigit()){
            onlyDigits += char
        }
    }
    return onlyDigits
}

val numbersDict = listOf(
    "one" to "o1ne",
    "two" to "t2wo",
    "three" to "t3hree",
    "four" to "f4our",
    "five" to "f5ive",
    "six" to "s6ix",
    "seven" to "s7even",
    "eight" to "e8ight",
    "nine" to "n9ine"
)

fun convertWordToNumber(line: String, numbersDict: List<Pair<String, String>>): String {
    return numbersDict.fold(line, ::replaceWord)
}

fun replaceWord(line:String, numbersDict:Pair<String, String>) : String {
    val changedLine = line.replace(numbersDict.first, numbersDict.second)
    return changedLine
}

