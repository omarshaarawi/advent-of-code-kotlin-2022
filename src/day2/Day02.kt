package day2

import day2.Move.Companion.relationships
import day2.Move.X
import day2.Move.Y
import day2.Move.Z
import day2.Result.DRAW
import day2.Result.LOSE
import day2.Result.WIN
import readInput


enum class Move(val point: Int, val move: String) {
    A(1, "rock"), B(2, "paper"), C(3, "scissors"),
    X(1, "rock"), Y(2, "paper"), Z(3, "scissors");

    companion object {
        val relationships = listOf(
            RelationShip(A, X, DRAW), // rock vs rock
            RelationShip(A, Y, WIN), // rock vs paper
            RelationShip(A, Z, LOSE), // rock vs scissors

            RelationShip(B, X, LOSE), // paper vs rock
            RelationShip(B, Y, DRAW), // paper vs paper
            RelationShip(B, Z, WIN), // paper vs scissors

            RelationShip(C, X, WIN), // scissor vs rock
            RelationShip(C, Y, LOSE), // scissor vs paper
            RelationShip(C, Z, DRAW), // scissor vs scissor
        )
    }
}

enum class Result(val points: Int) {
    WIN(6), LOSE(0), DRAW(3)
}

data class RelationShip(val opponent: Move, val me: Move, val result: Result)

fun main() {

    fun part1(): Int {
        val input = readInput("day2/Day02").map {
            val (a, b) = it.split(" ")
            Move.valueOf(a.first().toString()) to Move.valueOf(b.first().toString())
        }

        val points = input.map { match ->
            val relationship = relationships.firstOrNull {
                it.opponent == match.first && it.me == match.second
            }
            relationship!!.me.point.plus(relationship.result.points)
        }

        return points.sum()

    }

    fun part2(): Int {
        val input = readInput("day2/Day02").map {
            val (a, b) = it.split(" ")
            Move.valueOf(a.first().toString()) to Move.valueOf(b.first().toString())
        }
        val points = input.map { match ->
            val relationship = relationships.filter {
                when (match.second) {
                    X -> {
                        it.opponent == match.first && it.result == LOSE
                    }

                    Y -> {
                        it.opponent == match.first && it.result == DRAW
                    }

                    Z -> {
                        it.opponent == match.first && it.result == WIN
                    }

                    else -> throw Exception("Invalid move")
                }
            }.first()
            relationship.me.point.plus(relationship.result.points)
        }


        return points.sum()
    }

// test if implementation meets criteria from the description, like:
    println(part1())
    println(part2())

}