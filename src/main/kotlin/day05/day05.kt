package day05

import java.io.File

fun main() {
    val fileName = "./src/main/resources/day05Input.txt"

    val rules = mutableListOf<Pair<Int, Int>>()
    val orders = mutableListOf<List<Int>>()

    var firstPart = true
    File(fileName).useLines { lines ->
        lines.forEach { line ->
            if (line == "") {
                firstPart = false
            } else {
                if (firstPart) {
                    val (left, right) = line.split("|").map { it.toInt() }
                    rules.add(Pair(left, right))
                } else {
                    val order = line.split(",").map { it.toInt() }
                    orders.add(order)
                }
            }
        }
    }

    print(part1(orders, rules))
    print(part2(orders, rules))
}

private fun part1(orders: List<List<Int>>, rules: List<Pair<Int, Int>>): Int {
    val validOrders = orders.filter { isOrderValid(it, rules) }
    return validOrders.sumOf { it[it.size / 2] }
}

private fun part2(orders: List<List<Int>>, rules: List<Pair<Int, Int>>): Int {
    val incorrectOrders = orders.filterNot { isOrderValid(it, rules) }

    val myCustomComparator =  Comparator<Int> { a, b ->
        when {
            rules.contains(Pair(a,b)) -> -1
            rules.contains((Pair(b,a))) -> 1
            else -> 0
        }
    }

    val correctedIncorrectOrders = incorrectOrders.map {
        it.sortedWith(myCustomComparator)
    }

    return correctedIncorrectOrders.sumOf { it[it.size / 2] }
}

private fun isOrderValid(order: List<Int>, rules: List<Pair<Int, Int>>): Boolean{
    println("Checking for list $order")
    order.forEachIndexed { index, currElem ->
        // For each element in the list we make sure all the ones that were before do not break any rule
        order.take(index).forEach { elem ->
            if (rules.contains(Pair(currElem,elem))) return false
        }
    }
    return true
}

// I thought it was recursive... I'm keeping this as a nice souvenir ^^
private fun checkIfBefore(a: Int, b: Int, rules: List<Pair<Int, Int>>): Boolean {
    // Base condition: return if there is a pair of a and b
    if (rules.contains(Pair(a,b))) return true

    // Otherwise we check if the rules contain entries with the first number and second number
    val rule1 = rules.filter { it.first == a }
    val rule2 = rules.find { it.second == b }

    // If they don't then that means there is no specific ordering required
    if (rule1.isEmpty() || rule2 == null) return false

    // Otherwise, we want to look recursively if the second element of the rule with a is before b
    return rule1.any{ checkIfBefore(it.second, b, rules)}
}
