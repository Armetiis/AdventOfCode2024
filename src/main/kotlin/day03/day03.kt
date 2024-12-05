package day03

import java.io.File

fun main() {
    val fileName = "./src/main/resources/day03Input.txt"
    val input = File(fileName).readText()

    val sum1 = part1(input)
    println(sum1)

    val sum2 = part2(input)
    println(sum2)


}

private fun part1(input: String): Int{
    val regexPattern = Regex("mul\\([0-9]{1,3},[0-9]{1,3}\\)")
    val matches = regexPattern.findAll(input)
    val multiplicationResults = matches.map {matchResult ->
        val numberRegex = Regex("[0-9]{1,3}")
        val operands = numberRegex.findAll(matchResult.value).map { it.value.toInt() }.toList()
        operands[0] * operands[1]
    }
    return multiplicationResults.reduce{a,b -> a + b}
}

private fun part2(input:String): Int {
    val conditionalRegexPattern = Regex("mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)")
    val conditionalMatches = conditionalRegexPattern.findAll(input).map { it.value }.reduce{ a, b -> a + b}
    var remainingToAnalyze = conditionalMatches
    var matches = ""
    while (remainingToAnalyze.contains("do()")){
        matches+= remainingToAnalyze.substringBefore("don't()").replace("do()", "")
        remainingToAnalyze = remainingToAnalyze.substringAfter("don't()").substringAfter("do()")
    }
    return part1(matches)
}