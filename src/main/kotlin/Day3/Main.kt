fun main(args: Array<String>) {
    val symbolLocations = extractSymbolLocations(inputData)
    val listToCheck = findAdjacentPositions(symbolLocations)
    val listOfNum = checkForNumbersAndExtract(listToCheck, inputData)
    println(listOfNum.sum())


}

fun extractSymbolLocations(data: List<String>) : List<Pair<Int,Int>> {
    val pairList = emptyList<Pair<Int,Int>>().toMutableList()
    data.forEach { pairList += (findPosition(it, data.indexOf(it))) }
    return pairList
}

fun findPosition(line: String, lineNumber: Int) : List<Pair<Int, Int>> {
    val position = Pair(lineNumber,0)
    val dataList = mutableListOf<Pair<Int, Int>>()
    for (char in line) {
        if (char.isDigit() || char.isLetter() || char == '.') {
            continue
        }
        else {
            dataList.add(position.copy(second = line.indexOf(char)))
        }
    }
    return dataList
}

fun findAdjacentPositions(locations: List<Pair<Int, Int>>): MutableList<Pair<Int, Int>> {
    val positionsToCheck = mutableListOf<Pair<Int, Int>>()
   for (position in locations) {
       positionsToCheck.add(position.copy(first = position.first - 1, second = position.second - 1))
       positionsToCheck.add(position.copy(first = position.first - 1))
       positionsToCheck.add(position.copy(first = position.first - 1, second = position.second + 1))
       positionsToCheck.add(position.copy(second = position.second - 1))
       positionsToCheck.add(position.copy(second = position.second + 1))
       positionsToCheck.add(position.copy(first = position.first + 1, second = position.second - 1))
       positionsToCheck.add(position.copy(first = position.first + 1))
       positionsToCheck.add(position.copy(first = position.first + 1, second = position.second + 1))
   }
    return positionsToCheck

}

fun checkForNumbersAndExtract(locations: List<Pair<Int, Int>>, data: List<String>) : List<Int> {
    var all_numbers = listOf(0)
    for (position in locations) {
        if (data[position.first][position.second].isDigit()) {
            val num = extractNumber(data[position.first], position.second)
            if (all_numbers[all_numbers.size - 1] != num) {
                all_numbers += num
            } else {
                continue
            }
        }
    }
    return all_numbers
}


fun extractNumber(line:String, horizontalPosition: Int): Int {
    var number = ""
    if (line[horizontalPosition - 1] == '.') {
        for (i in horizontalPosition..line.length) {
            if (line[i].isDigit()) {
                number += line[i]
            }
            else if (line[i] == '.') {
                break
            }
        }
    }
    else if (line[horizontalPosition - 1].isDigit()) {
        var start = 0
        for (i in horizontalPosition -1 downTo 0) {
            if (line[i].isDigit()) {
                continue
            }
            else if (line[i] == '.') {
                start = i + 1
                break
            }
        }
        for (i in start..line.length) {
            if (line[i].isDigit()) {
                number += line[i]

            } else if (line[i] == '.') {
                break
            }
        }

    }
    print("$number \n")
    return number.toInt()
}
//if there is a number make not of this by creating a substring?
//total up these numbers