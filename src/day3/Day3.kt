package day3

import readInput

fun main() {
    fun part1(): Int {
        val input = readInput("day3/Day03").map { lines ->
            val (a, b) = lines.chunked(lines.length / 2)
            Pair(a.toHashSet(), b.toHashSet())
        }

        val lowercaseAlphabet = ('a'..'z').toMutableList()
        val uppercaseAlphabet = ('A'..'Z').toMutableList()
        val alphabet = lowercaseAlphabet.plus(uppercaseAlphabet)
        val priorityList = mutableListOf<Int>()

        input.forEach {
            it.first.forEach { c: Char ->
                if (it.second.contains(c)) {
                    priorityList.add(alphabet.indexOf(c) + 1)
                }
            }
        }
        return priorityList.sum()
    }

    fun part2(): Int {
        val lowercaseAlphabet = ('a'..'z').toMutableList()
        val uppercaseAlphabet = ('A'..'Z').toMutableList()
        val alphabet = lowercaseAlphabet.plus(uppercaseAlphabet)
        val priorityList = mutableListOf<Int>()

        val input = readInput("day3/Day03")
            .chunked(3)

        val listOfGroups = input.map { stringList ->
            stringList.map {
                it.toCharArray().toHashSet()
            }
        }

        listOfGroups.forEach {
            val (a, b, c) = it
            val intersectA = a.intersect(b)
            val result = intersectA.intersect(c)
            priorityList.add(alphabet.indexOf(result.first()) + 1)
        }
        return priorityList.sum()
    }

    println(part1())
    println(part2())
}
