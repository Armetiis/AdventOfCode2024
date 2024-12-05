package day04

import java.io.File
import kotlin.text.StringBuilder

const val TARGET_PART_1 = "XMAS"
const val TARGET_PART_2 = "MAS"

fun main() {
    val fileName = "./src/main/resources/day04Input.txt"
    val input = File(fileName).useLines { it.toList() }

    val count1 = part1(input)
    println(count1)

    val count2 = part2(input)
    print(count2)
}

private fun part1(input: List<String>): Int{
    var count = 0
    for (lineIndex in input.indices){
        val line = input[lineIndex]
        for (charIndex in line.indices) {
            // For each character we create 4 strings, making sure we are not out of bounds:
            // horizontal, vertical, mainDiagonal, secondaryDiagonal
            try {
                val horizontal = StringBuilder()
                    .append(line[charIndex])
                    .append(line[charIndex + 1])
                    .append(line[charIndex + 2])
                    .append(line[charIndex + 3]).toString()
                if (horizontal == TARGET_PART_1 || horizontal == TARGET_PART_1.reversed()) count +=1
            } catch (e: IndexOutOfBoundsException) {
                // Do nothing
            }

            try {
                val vertical = StringBuilder()
                    .append(input[lineIndex][charIndex])
                    .append(input[lineIndex + 1][charIndex])
                    .append(input[lineIndex + 2][charIndex])
                    .append(input[lineIndex + 3][charIndex]).toString()
                if (vertical == TARGET_PART_1 || vertical == TARGET_PART_1.reversed()) count +=1
            } catch (e: IndexOutOfBoundsException) {
                // Do nothing
            }

            try {
                val mainDiagonal = StringBuilder()
                    .append(input[lineIndex][charIndex])
                    .append(input[lineIndex + 1][charIndex + 1])
                    .append(input[lineIndex + 2][charIndex + 2])
                    .append(input[lineIndex + 3][charIndex + 3]).toString()
                if (mainDiagonal == TARGET_PART_1 || mainDiagonal == TARGET_PART_1.reversed()) count +=1
            } catch (e: IndexOutOfBoundsException) {
                // Do nothing
            }

            try {
                val mainDiagonal = StringBuilder()
                    .append(input[lineIndex][charIndex])
                    .append(input[lineIndex + 1][charIndex - 1])
                    .append(input[lineIndex + 2][charIndex - 2])
                    .append(input[lineIndex + 3][charIndex - 3]).toString()
                if (mainDiagonal == TARGET_PART_1 || mainDiagonal == TARGET_PART_1.reversed()) count +=1
            } catch (e: IndexOutOfBoundsException) {
                // Do nothing
            }

        }

    }
    return count
}

private fun part2(input: List<String>): Int {
    var count = 0
    for (lineIndex in input.indices) {
        val line = input[lineIndex]
        for (charIndex in line.indices) {
            try {
                val mainDiagonal = StringBuilder()
                    .append(input[lineIndex][charIndex])
                    .append(input[lineIndex + 1][charIndex + 1])
                    .append(input[lineIndex + 2][charIndex + 2]).toString()
                if (mainDiagonal == TARGET_PART_2 || mainDiagonal == TARGET_PART_2.reversed()){
                    val secondaryDiagonal = StringBuilder()
                        .append(input[lineIndex][charIndex + 2])
                        .append(input[lineIndex + 1][charIndex + 1])
                        .append(input[lineIndex + 2][charIndex]).toString()
                    if (secondaryDiagonal == TARGET_PART_2 || secondaryDiagonal == TARGET_PART_2.reversed())
                        count +=1
                }
            } catch (e: IndexOutOfBoundsException) {
                // Do nothing
            }
        }
    }
    return count
}