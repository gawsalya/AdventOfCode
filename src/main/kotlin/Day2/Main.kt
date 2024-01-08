import kotlin.math.max

fun main(args: Array<String>) {
    loopThroughLines(inputData)
}

// create some sort of max numbers for each colour
val maxColours = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14
    )

// have a loop to add game numbers
fun loopThroughLines(allLines: List<String>) {
    var total = 0
    allLines.forEach { total += findMinNeeded(it) }
    print(total)
}

// compare each line to the max number and if it exceeds then do not add to the running total
fun compareToMax(line: String) : Int {
    val game = line.split(';')
    var numberOfFalse = game.size
    for (round in game) {
        if (compareToColour(round)) {
            numberOfFalse -= 1
        }
    }
    if (numberOfFalse == 0) {
        return game[0].substringBefore(":").filter { it.isDigit() }.toInt()
    }
    return 0
}

fun compareToColour(line: String) : Boolean {
    val eachColour = line.substringAfter(":").split(",")
    for (colour in eachColour) {
        for (key in maxColours.keys) {
            if (colour.contains(key)) {
                if ((colour.filter { it.isDigit() }).toInt() > maxColours[key]!!) {
                    return false
                }
            }
        }
    }
    return true
}

fun findMinNeeded(line: String) : Int {
    val game = line.split(';')
    var minColours = mutableMapOf(
        "red" to 0,
        "green" to 0,
        "blue" to 0
    )
    var power = 1
    for (round in game) {
        updateColour(round, minColours)
        }
    for (value in minColours.values) {
        power *= value
    }
    print("$power\n")
    return power
    }

fun updateColour(line: String, minColours : MutableMap<String, Int>) {
    val eachColour = line.substringAfter(":").split(",")
    for (colour in eachColour) {
        for (key in minColours.keys) {
            if (colour.contains(key)) {
                val newValue = colour.filter { it.isDigit() }.toInt()
                if (newValue > minColours[key]!!) {
                    minColours[key] = newValue
                }
            }
        }
    }
}

