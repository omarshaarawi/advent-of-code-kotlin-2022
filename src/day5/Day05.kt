package day5

import java.io.File

data class Operation(val move: Int, val from: Int, val to: Int)

fun main() {
    val charMatch = """\[(\w)\]""".toRegex()
    val moveMatch = """move (\w+) from (\w) to (\w)""".toRegex()

    fun parseStack(stacks: String) = stacks.split("\n")
        .reversed()
        .asSequence()
        .drop(1)
        .map { line ->
            line.chunked(4).map {
                charMatch.find(it)?.groups?.get(1)?.value
            }
        }
        .map {
            it.mapIndexedNotNull { index, item ->
                if (item != null) {
                    Pair(index + 1, item)
                } else {
                    null
                }
            }
        }.flatten()
        .groupBy { it.first }
        .mapValues { mapItem -> mapItem.value.map { it.second }.toMutableList() }
        .toMutableMap()

    fun parseOperations(operations: String) = operations.split("\n").mapNotNull {
        val (number, startColumn, endColumn) = moveMatch.find(it)!!.destructured
        Operation(number.toInt(), startColumn.toInt(), endColumn.toInt())
    }

    fun part1(): String {
        val data = File("src/day5/Day05.txt").readText()
        val (stacks, operations) = data.split("\n\n")

        val parsedStack = parseStack(stacks)

        val parsedOperations =
            parseOperations(operations)

        parsedOperations.forEach { operation ->
            val objToMove = (1..operation.move).map { parsedStack[operation.from]!!.removeLast() }
            parsedStack[operation.to]!!.addAll(objToMove)
        }

        return parsedStack.mapNotNull { it.value.lastOrNull() }.joinToString("")
    }

    fun part2(): String {
        val data = File("src/day5/Day05.txt").readText()
        val (stacks, operations) = data.split("\n\n")
        val parsedStack = parseStack(stacks)
        val parsedOperations =
            parseOperations(operations)

        parsedOperations.forEach { operation ->
            val objToMove = (1..operation.move).map { parsedStack[operation.from]!!.removeLast() }.reversed()
            parsedStack[operation.to]!!.addAll(objToMove)
        }

        return parsedStack.mapNotNull { it.value.lastOrNull() }.joinToString("")
    }

    println(part1())
    println(part2())
}
