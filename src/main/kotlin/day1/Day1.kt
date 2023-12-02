package day1

import load
import java.lang.Exception

private val mappedDigits = mapOf(
    "zero" to "0",
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9"
)

private val allPossibleValues = mappedDigits.keys + mappedDigits.values

private fun fetchDigit(raw: String): String {
    return if (raw.length > 1) {
        mappedDigits[raw] ?: throw Exception("Could not find digit for $raw")
    } else {
        raw
    }
}
fun sumAll(calibration: String): Int {
    val first = fetchDigit(calibration.findAnyOf(allPossibleValues)!!.second)
    val last = fetchDigit(calibration.findLastAnyOf(allPossibleValues)!!.second)

    return (first + last).toInt()
}

fun sumDigitsOnly(calibration: String): Int {
    val firstNum = calibration.first { Character.isDigit(it) }
    val lastNum = calibration.last { Character.isDigit(it) }

    return (firstNum.toString() + lastNum.toString()).toInt()
}

fun day1PartOne(loadedData: List<String>): Int {
    return loadedData.sumOf { sumDigitsOnly(it) }
}

fun day1PartTwo(loadedData: List<String>): Int {
    return loadedData.sumOf { sumAll(it) }
}

fun main() {
    val loadedData = load("/day-1.txt")
    val loadedData2 = load("/day1-2.txt")
    println("Solution to day 1, part 1 is '${day1PartOne(loadedData)}'")
    println("Solution to day 1, part 2 is '${day1PartTwo(loadedData2)}'")
}


