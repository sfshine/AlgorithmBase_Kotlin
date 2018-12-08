package tree

class BST {
    class Node(var key: Int, var value: Int) {
        var left: Node? = null
        var right: Node? = null
        override fun toString(): String {
            return "key:${key}, value:${value}"
        }
    }

    var count: Int = 0
    var root: Node? = null

    fun insert(key: Int, value: Int) {
        root = insert(root, key, value)
    }

    fun search(key: Int): Node? {
        return search(root, key)
    }

    /**
     * 先打印根
     */
    fun preOrder(node: Node?) {
        if (node != null) {
            println(node.toString())
            preOrder(node.left)
            preOrder(node.right)
        }
    }

    /**
     * 先打印左孩子
     */
    fun inOrder(node: Node?) {
        if (node != null) {
            inOrder(node.left)
            println(node.toString())
            inOrder(node.right)
        }
    }

    /**
     * 先打印右孩子
     */
    fun postOrder(node: Node?) {
        if (node != null) {
            postOrder(node.left)
            postOrder(node.right)
            println(node.toString())
        }
    }

    fun removeMax() {
        removeMax(root)
    }

    /**
     * 检查这个节点有没有右孩子,如果有,就检查这个节点的右孩子
     * 如果没有, 就是最大值, 删除之, 并尝试返回这个节点的左孩子, 作为右孩子
     *
     * 父亲:"右孩子,去删除最大值把!"
     * 右孩子:"爹地,我没有右孩子了,我是最后的右孩子,删除我吧!但是我还有比我小的左孩子呢,怎么办?"
     * 父亲:"你还有左孩子? 过继给我吧, 右孩子再见!"
     */
    private fun removeMax(node: Node?): Node? {
        if (node == null) {
            println("empty node")
            return null;
        }
        if (node.right == null) {//2 这次调用来自78行. "爹地,我没有右孩子了,我是最后的右孩子, 是最大值,删除我吧!但是我还有比我小的左孩子呢,怎么办?"
            println("removeMax : ${node}")
            count--
            return node.left;
        } else {
            node.right = removeMax(node.right)//1"右孩子,去删除最大值把!" 3"What? 你还有左孩子? 过继给我吧, 右孩子再见!"
            return node
        }
    }

    /**
     * 如果key值和根的key相等,就返回
     * 如果 key < root.key, 就让左孩子去找
     * 如果 key > root.key, 就让右孩子去找
     * 如果孩子们没找到, 返回null "臣妾找不到呀!"
     */
    private fun search(root: Node?, key: Int): Node? {
        if (root == null) return null
        if (key == root.key) {
            return root
        } else if (key < root.key) {
            return search(root.left, key)
        } else {
            return search(root.right, key)
        }
    }

    /**
     * 如果这个根有不是空, 就比较插入的值与这个根的key的大小.
     * 相等就更新当前的值
     * 小就交给左孩子处理
     * 大就交给右孩子处理
     * 如果这个根是空的,也就是这个"根"的父亲命令他要么添加元素要么从他孩子中找要插入的地方, 既然找不到孩子,那就乖乖的做父亲的孩子吧,
     */
    private fun insert(node: Node?, key: Int, value: Int): Node {
        println("inserting:${root}")
        if (node == null) {
            count++
            return Node(key, value)
        }
        if (key == node.key) {
            node.value = value
        } else if (key < node.key) {
            node.left = insert(node.left, key, value)//左孩子可能要做调整, 总之让左孩子自己调整吧.
        } else {
            node.right = insert(node.right, key, value)
        }
        return node
    }

}

fun main(args: Array<String>) {
    val bst = BST()
    for (i in 0..10) {
        bst.insert(i, (Math.random() * 10).toInt())
    }

    bst.preOrder(bst.root)
    bst.inOrder(bst.root)
    bst.postOrder(bst.root)

    println("${bst.search(1)}")
    println("${bst.search(11)}")

    bst.removeMax()

}
