package dynamicprograming

import kotlin.math.max

class Knapsack {
    lateinit var memo: Array<IntArray>

    /**
     * 背包问题
     * 用 [0...index]的物品,填充容积为c的背包的最大价值
     * 选择有两种方式
     * 1.不选择index球,也就是之前的组合以及是最大值bestValue(index -1 , c)
     * 2.如果c比w[index]大,可以尝试选择index这个球,也就是 v[index] + bestValue(index-1, c - w[index])可能是一种选择方式
     * 3.终止添加有2: a.背包容量<=0,此时可以容纳的最大值为0 b.一个球也不能选择,此时最大值当然也是0
     */
    fun bestValue(w: IntArray, v: IntArray, index: Int, c: Int): Int {
        if (w.isEmpty() || v.isEmpty()) return 0
        //递归终止条件: 1 index c <= 0 则最大价值为0 2 index < 0 ,一个球也不能选择, 返回0
        if (c <= 0 || index < 0) return 0
        if (memo[index][c] != -1) {
            println("memo[$index][$c] = ${memo[index][c]}")
            return memo[index][c]
        }
        var res = bestValue(w, v, index - 1, c)
        if (c >= w[index]) {
            res = max(res, v[index] + bestValue(w, v, index - 1, c - w[index]))
        }
        memo[index][c] = res
        return res
    }


}

fun main(args: Array<String>) { var w = intArrayOf(5, 6, 5, 1, 19, 7)
    var v = intArrayOf(2, 3, 1, 4, 6, 5)
    var c = 18
    var knapsack = Knapsack()


    var time = System.currentTimeMillis()
    knapsack.memo = Array(w.size) { IntArray(c + 1) { -1 } }
    println(knapsack.bestValue(w, v, w.size - 1, c))
    println("cost ${System.currentTimeMillis() - time}")
}