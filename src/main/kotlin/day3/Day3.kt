package day3

import load


fun sumPartNumbers(schematic: List<String>): Int {
    var total = 0
    for (row in schematic.indices) {
        var col = 0
        while (col < schematic[row].length) {
            if (schematic[row][col].isDigit()) {
                val num = schematic[row].substring(col).takeWhile { it.isDigit() }.toInt()
                if (isAdjacent(schematic, row, col)) {
                    total += num
                }
                col += num.toString().length
            } else {
                col++
            }
        }
    }
    return total
}

fun isAdjacent(schematic: List<String>, row: Int, startCol: Int): Boolean {
    val endCol = startCol + schematic[row].substring(startCol).takeWhile { it.isDigit() }.length
    return (-1..1).any { dx ->
        (-1..1).any { dy ->
            (dx != 0 || dy != 0) && (row + dx in schematic.indices) && (startCol until endCol).any { col ->
                col + dy in schematic[row + dx].indices && schematic[row + dx][col + dy] !in '0'..'9' && schematic[row + dx][col + dy] != '.'
            }
        }
    }
}

fun sumaPartNumbers2(schematic: List<String>): Int {
    var total = 0
    for ((row, line) in schematic.withIndex()) {
        for ((col, char) in line.withIndex()) {
            if (char == '*') {
                val nums = getAdjacentNumbers(schematic, row, col)
                if (nums.size == 2) {
                    total += nums[0] * nums[1]
                }
            }
        }
    }
    return total
}

fun getAdjacentNumbers(schematic: List<String>, row: Int, col: Int): List<Int> {
    return (-1..1).flatMap { dx ->
        (-1..1).mapNotNull { dy ->
            if (dx != 0 || dy != 0) {
                val newRow = row + dx
                val newCol = col + dy
                if (newRow in schematic.indices && newCol in schematic[newRow].indices && schematic[newRow][newCol].isDigit()) {
                    val numStr = generateSequence(newCol) { if (it - 1 >= 0 && schematic[newRow][it - 1].isDigit()) it - 1 else null }
                        .last()
                        .let { startCol -> schematic[newRow].substring(startCol).takeWhile { it.isDigit() } }
                    numStr.toIntOrNull()
                } else null
            } else null
        }
    }.distinct()
}

fun main() {
    val loadedData = load("/day-3.txt")
    //println("Solution to day 3, part 1 is '${sumPartNumbers(loadedData)}'")
    println("Solution to day 3, part 2 is '${sumaPartNumbers2(loadedData)}'")
}