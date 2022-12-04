package day4

import readInput

fun main() {
    fun IntRange.overlaps(other: IntRange): Boolean {
        return (this.first >= other.first && this.last <= other.last) ||
            (other.first >= this.first && other.last <= this.last)
    }

    fun String.toRange(): IntRange {
        return this.split("-")
            .let { (a, b) -> a.toInt()..b.toInt() }
    }

    fun part1(): Int {
        val input = readInput("day4/Day04")
            .map { it.split(" ") }
            .flatten()
            .map { range -> range.split(",").map { it.toRange() } }

        val countOfOverlaps = input.map {
            val (a, b) = it
            a.overlaps(b)
        }.count { it }

        return countOfOverlaps
    }

    fun part2(): Int {
        val input = readInput("day4/Day04")
            .map { it.split(" ") }
            .flatten()
            .map { range -> range.split(",").map { it.toRange() } }

        val countOfIntersections = input.map {
            val (a, b) = it
            a.intersect(b)
        }.count { it.isNotEmpty() }
        return countOfIntersections
    }

    println(part1())
    println(part2())
}
