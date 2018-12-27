package interview

import kotlin.math.max

/**
 * 背包问题
 * 用 [0...index]的物品,填充容积为c的背包的最大价值
 * 选择有两种方式
 * 1.不选择index球,也就是之前的组合以及是最大值bestValue(index -1 , c)
 * 2.如果c比w[index]大,可以尝试选择index这个球,也就是 v[index] + bestValue(index-1, c - w[index])可能是一种选择方式
 */
private fun bestValue(w: IntArray, v: IntArray, index: Int, c: Int): Int {
    if (w.isEmpty() || v.isEmpty()) return 0
    if (c == 0) return 0
    if (index == 0) return v[index]
    if (memo[index][c] != -1) {
        memo[index][c]
        println("memo[$index][$c] = ${memo[index][c]}")
    }
    var res = bestValue(w, v, index - 1, c)
    if (c >= w[index]) {
        res = max(res, v[index] + bestValue(w, v, index - 1, c - w[index]))
    }
    memo[index][c] = res
    return res
}

lateinit var memo: Array<IntArray>
fun main(args: Array<String>) {
    var w = intArrayOf(1, 2, 3)
    var v = intArrayOf(6, 10, 12)
    var c = 10
    var time = System.currentTimeMillis()
    memo = Array(w.size) { IntArray(c + 1) { -1 } }
    println(bestValue(w, v, w.size - 1, c))
    println("cost ${System.currentTimeMillis() - time}")
}