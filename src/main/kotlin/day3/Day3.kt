package day3

import load


fun sumPartNumbers(schematic: List<String>): Int {
    var total1 = 0
    for (r in schematic.indices) {
        var c = 0
        while (c < schematic[r].length) {
            if (schematic[r][c].isDigit()) {
                var end = c
                while (end < schematic[r].length && schematic[r][end].isDigit()) {
                    end++
                }
                val num = schematic[r].substring(c, end).toInt()
                if ((c until end).any { isAdjacent(schematic, r, it) }) {
                    total1 += num
                }
                c = end
            } else {
                c++
            }
        }
    }
    return total1
}

fun isAdjacent(grid: List<String>, r: Int, c: Int): Boolean {
    val directions = listOf(
        -1 to -1, -1 to 0, -1 to 1,
        0 to -1,  0 to 1,
        1 to -1,  1 to 0,  1 to 1
    )

    for ((dr, dc) in directions) {
        val nr = r + dr
        val nc = c + dc
        if (nr in grid.indices && nc in grid[0].indices && grid[nr][nc] !in '0'..'9' && grid[nr][nc] != '.') {
            return true
        }
    }
    return false
}


fun main() {
    val loadedData = load("/day-3.txt")
    println("Solution to day 3, part 1 is '${sumPartNumbers(loadedData)}'")
}