package tree

import java.util.*

fun main(args: Array<String>) {
    var bstTree: BSTNoRecursion = BSTNoRecursion()
    var arr = intArrayOf(5, 8, 1, 0, 9, 5, 4, 6)
    for (i in arr) {
        println("inserting: ${i}")
        bstTree.insert(i)
    }
    bstTree.preOrder()
}

class BSTNoRecursion {
    class Node(var key: Int = 0) {
        var left: Node? = null
        var right: Node? = null
    }

    var root: Node? = null

    fun insert(key: Int) {
        insertInner(key)
    }

    fun preOrder() {
        println("---------preOrderRecursion--------")
        preOrderRecursion(root)
        println("---------preOrderNoRecursion--------")
        preOrderNoRecursion(root)
    }

    fun postOrder() {
        println("---------postOrder--------")
        postOrder(root)
    }

    fun inOrder() {
        println("---------inOrder--------")
        inOrder(root)
    }

    /**
     * 如果值相等, 表示已经存在, 返回
     * 值小去左孩子寻找目标位置
     * 值大去右孩子寻找目标位置
     */
    private fun insertInner(key: Int) {
        if (root == null) {
            root = Node(key)
            return
        }
        var curNode = root!!
        while (true) {
            if (key == curNode.key) {
                println("${key} has been in the tree")
                return
            }
            if (key < curNode.key) {
                if (curNode.left == null) {
                    curNode.left = Node(key)
                    return
                } else {
                    curNode = curNode.left!!
                }
            } else {
                if (curNode.right == null) {
                    curNode.right = Node(key)
                    return
                } else {
                    curNode = curNode.right!!
                }
            }

        }
    }

    /**
     * 1.不断找左孩子遍历打印,并将其放到stack中
     * 2.左孩子没了, 就打印stack中元素的右孩子, 然后使用1的方法遍历右孩子*的*左孩子
     * 3.直到所有的都完成
     */
    private fun preOrderNoRecursion(node: Node?) {
        var stack = Stack<Node>()
        var curNode = root
        while (curNode != null || !stack.isEmpty()) {
            while (curNode != null) {
                println("${curNode.key}")
                stack.push(curNode)
                curNode = curNode.left
            }
            if (!stack.isEmpty()) {
                curNode = stack.pop().right
            }
        }
    }

    private fun preOrderRecursion(node: Node?) {
        if (node != null) {
            println("${node.key}")
            preOrderRecursion(node.left)
            preOrderRecursion(node.right)
        }
    }

    private fun inOrder(node: Node?) {
        if (node != null) {
            inOrder(node.left)
            println("${node.key}")
            inOrder(node.right)
        }
    }

    private fun postOrder(node: Node?) {
        if (node != null) {
            postOrder(node.left)
            postOrder(node.right)
            println("${node.key}")
        }
    }
}
