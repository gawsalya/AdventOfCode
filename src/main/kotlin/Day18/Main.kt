package Day18

import kotlin.math.absoluteValue

fun main(args: Array<String>) {

    var directionsMap : Map <String, Pair<Int, Int>> = mapOf("R" to Pair(0, 1), "L" to Pair(0, -1), "D" to Pair(1, 0), "U" to Pair(-1, 0))

    data class Hole(val line: String) {
        val allSplit = line.split(" ")
        val direction = directionsMap[allSplit[0]]
        val frequency = allSplit[1].toInt()
        val colour = allSplit[2]
    }

    var allPoints = mutableListOf<Pair<Int, Int>>(Pair(0, 0))
    var exteriorPerimeter = 0

    for (line in inputData) {
        val trenchX = Hole(line).direction?.first
        val trenchY = Hole(line).direction?.second
        val endPoint = Hole(line).frequency
        exteriorPerimeter +=  endPoint
        val x = allPoints.last().first
        val y = allPoints.last().second

        allPoints.add(Pair((x + trenchX!! * endPoint), (y + trenchY!! * endPoint)))
    }

    val interiorArea = pickTheorem(shoeLaceFormula(allPoints), exteriorPerimeter)

    print(interiorArea + exteriorPerimeter)
}

fun shoeLaceFormula(listOfPoints: MutableList<Pair<Int, Int>>): Int {
    var total = 0
    val n = listOfPoints.size
    for (i in 0 until n - 1) {
        total += listOfPoints[i].first * listOfPoints[i + 1].second - listOfPoints[i + 1].first * listOfPoints[i].second
    }
    return (total.absoluteValue)/2
}

fun pickTheorem(area: Int, perimeter: Int) : Int {
    var interior = 0
    interior = area - perimeter/2 + 1
    return interior
}
