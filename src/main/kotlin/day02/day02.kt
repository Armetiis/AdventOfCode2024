package day02

import java.io.File
import kotlin.math.abs

const val MIN_DIFFERENCE = 1 // inclusive
const val MAX_DIFFERENCE = 3 // inclusive

fun main() {
    val fileName = "./src/main/resources/day02Input.txt"

    // reportsList holds a list of all the reports (list of ints)
    val reportsList = File(fileName).useLines { lines ->
        lines.map { it.split(" ").map(String::toInt) }.toList()
    }

    println("There are ${reportsList.size} reports in total")

    val size1 = part1(reportsList)
    println("Part1: There are $size1 safe reports")

    val size2 = part2(reportsList)
    println("Part2: There are $size2 safe reports when allowing 1 error")
}

private fun part1(reportsList:  List<List<Int>>): Int {
    val safeReportsList = reportsList.filter {
        isMonotonous(it) && isDiffOfConsecutiveElementsInBounds(it, MIN_DIFFERENCE, MAX_DIFFERENCE)
    }
    return safeReportsList.size
}

private fun part2(reportsList: List<List<Int>>): Int {
    return reportsList.count { report ->
        generateAllSublists(report).any { sublist ->
            isMonotonous(sublist) && isDiffOfConsecutiveElementsInBounds(sublist, MIN_DIFFERENCE, MAX_DIFFERENCE)
        }
    }
}

private fun generateAllSublists(report: List<Int>): List<List<Int>>{
    return buildList {
        add(report)
        for (i in report.indices) {
            add(report.filterIndexed { index, _ -> index != i })
        }
    }
}

private fun isMonotonous(list: List<Int>): Boolean {
    return list == list.sorted() || list == list.sortedDescending()
}

private fun isDiffOfConsecutiveElementsInBounds(list: List<Int>, min: Int, max: Int): Boolean{
    val diffList = list.zipWithNext { a, b -> abs(b - a) }
    return diffList.foldRight(true) { newElem, accumulator ->
        accumulator && newElem in min .. max
    }
}