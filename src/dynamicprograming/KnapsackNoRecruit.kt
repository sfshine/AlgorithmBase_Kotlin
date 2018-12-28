package dynamicprograming

import kotlin.math.max

class KnapsackNoRecruit {
    /**
     *  用 [0...index]的物品,填充容积为c的背包的最大价值
     * 背包问题非递归解法
     * 自底向上求每一层中的值,上层的结果依赖下层,同样是 res = max(F(n -1, curC), value[index] + F(n -1 , curC - w[index]))
     * 选择有两种方式
     * 1.不选择index球,也就是之前的组合以及是最大值bestValue(index -1 , c)
     * 2.如果c比w[index]大,可以尝试选择index这个球,也就是 v[index] + bestValue(index-1, c - w[index])可能是一种选择方式
     * 3.终止添加有2: a.背包容量<=0,此时可以容纳的最大值为0 b.一个球也不能选择,此时最大值当然也是0
     */
    fun bestValue(w: IntArray, v: IntArray, c: Int): Int {
        if (w.isEmpty() || v.isEmpty()) return 0
        var n = w.size
        if (n == 0 || c == 0) return 0
        //选择第一个,c如果小于第一个值,则最大值只能是0,否则就是第一个值
        var memo = Array(n) { IntArray(c + 1) { -1 } }
        //如果只有一个球选择,可以直接计算结果
        for (curC in 0..c) {
            memo[0][curC] = if (curC >= w[0]) v[0] else 0
        }
        //大于一个球的计算,依据底层求出上层的值  res = max(F(n -1, curC), value[index] + F(n -1 , curC - w[index]))
        for (index in 1 until n) {
            for (curC in 0..c) {
                memo[index][curC] = memo[index - 1][curC]
                if (curC >= w[index]) {
                    memo[index][curC] = max(memo[index][curC], v[index] + memo[index - 1][curC - w[index]])
                }
            }
        }
        return memo[n - 1][c]
    }
}

fun main(args: Array<String>) {
    var knapsack = KnapsackNoRecruit()
    var w = intArrayOf(5, 6, 5, 1, 19, 7)
    var v = intArrayOf(2, 3, 1, 4, 6, 5)
    var c = 18
    var time = System.currentTimeMillis()
    println(knapsack.bestValue(w, v, c))
    println("cost ${System.currentTimeMillis() - time}")
}