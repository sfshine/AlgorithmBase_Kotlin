package queue

import java.util.*

fun main(args: Array<String>) {
    var twoStackQueue: TwoStackQueue<Int> = TwoStackQueue()
    for (i in 0..9) {
        twoStackQueue.push(i)
    }
    for (i in 0..5) {
        println("print ${twoStackQueue.pop()}")
    }
    for (i in 10..19) {
        twoStackQueue.push(i)
    }
    while (!twoStackQueue.isEmpty())
        println("print ${twoStackQueue.pop()}")
}

/**
 * stack1是数据接收器, stack2是中转站
 * 每次put数据都放到stack1中,
 * 每次pop从stack2中读取, stack2如果没了,他会从stack1取数据
 */
class TwoStackQueue<T> {
    val stack1 = Stack<T>()
    val stack2 = Stack<T>()

    fun isEmpty(): Boolean {
        return stack1.isEmpty() && stack2.isEmpty()
    }

    fun push(t: T) {
        stack1.push(t)
    }

    fun pop(): T {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop())
            }
        }
        return stack2.pop()
    }
}