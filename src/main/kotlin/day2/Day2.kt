package day2

fun resourceAsText(path: String): String = object {}.javaClass.getResource(path)?.readText()!!

fun processGames(): Map<Int, List<List<Pair<Int, String>>>> {
    return resourceAsText("/day-2.txt")
        .lines()
        .associate { line ->
            val matchResult = "^Game (?<gameid>[0-9]{1,3}): (?<rest>.*)$"
                .toRegex()
                .matchEntire(line)
                ?: throw NullPointerException(line)

            val groups = matchResult.groups
            val gameId = groups["gameid"]!!.value.toInt()
            val sets = groups["rest"]!!.value

            gameId to sets.split(";")
                .map { it.trim() }
                .map { set ->
                    set.split(",")
                        .map { it.trim() }
                        .map {
                            val (num, color) = it.split(" ")
                            num.toInt() to color
                        }
                }
        }
}

fun day2part1() {
    val games = processGames()

    val gameIdSum = games
        .filter { (_, sets) ->
            sets.none { set ->
                set.any { (num, color) ->
                    when (color) {
                        "red" -> num > 12
                        "green" -> num > 13
                        "blue" -> num > 14
                        else -> throw RuntimeException("unreachable")
                    }
                }
            }
        }
        .keys.sum()

    println(gameIdSum)
}

fun day2part2() {
    val games = processGames()

    val gameIdSum = games
        .map { (_, sets) ->
            sets.fold(mutableMapOf<String, Int>()) { acc, set ->
                set.associate { (num, color) -> color to num }
                    .forEach { (color, amount) ->
                        acc.merge(color, amount, ::maxOf)
                    }
                acc
            }
        }
        .map { it.values.reduce(Int::times) }
        .sum()

    println(gameIdSum)
}

fun main() {
    day2part1()
    day2part2()
}
