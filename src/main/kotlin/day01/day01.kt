package day01

import java.io.File
import kotlin.math.abs

fun main() {
    val fileName = "./src/main/resources/day01Input.txt"

    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()

    File(fileName).useLines { lines ->
        lines.forEach { line ->
            val (left, right) = line.trim().split("\\s+".toRegex()).map { it.toInt() }
            list1.add(left)
            list2.add(right)
        }
    }

    val distance1 = part1(list1, list2)
    println(distance1)

    val similarityScore = part2(list1, list2)
    println(similarityScore)

}

private fun part1 (list1: List<Int>, list2: List<Int>): Int {
    val list1Sorted = list1.sorted()
    val list2Sorted = list2.sorted()
    return list1Sorted.zip(list2Sorted).map { (a, b) -> abs(a - b) }.reduce { a, b -> a + b }
}

private fun part2 (list1: List<Int>, list2: List<Int>): Int {
    return list1.map { it * list2.count{ elem -> elem == it} }.reduce{ a, b -> a + b}
}