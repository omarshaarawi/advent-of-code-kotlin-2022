package day6

import java.io.File

fun main() {
    fun findUniqueMarker(distinctCount: Int): Int {
        val input = File("src/day6/Day06.txt").readText()

        val seen = mutableListOf<Char>()
        val uniqueGroups = mutableListOf<Int>()
        val firstDistinct = input.mapIndexed { index, char ->
            seen.add(char)
            if (seen.size > distinctCount) {
                seen.removeAt(0)
            }
            if (seen.distinct().count() == distinctCount) {
                uniqueGroups.add(index + 1)
            }
            uniqueGroups
        }.flatten().first()

        return firstDistinct
    }

    fun part1(): Int {
        return findUniqueMarker(4)
    }

    fun part2(): Int {
        return findUniqueMarker(14)
    }

    println(part1())
    println(part2())
}
