package list

class LinkList {
    var head: Node? = null
    var current: Node? = null

    class Node(var value: Int) {
        var next: Node? = null
        override fun toString(): String {
            return "${value}"
        }
    }

    fun add(value: Int) {
        if (head == null) {
            head = Node(value)
            current = head
        } else {
            current!!.next = Node(value)
            current = current!!.next
        }
    }

    fun print(node: Node?) {
        if (node == null) {
            return
        }
        current = node;
        while (current != null) {
            println(current)
            current = current!!.next
        }
    }

    fun reverse(node: Node?): Node? {
        if (node == null) return node;
        var pre = node;//上一节点
        var cur = node.next;//当前节点
        var temp: Node?
        while (cur != null) {
            temp = cur.next
            cur.next = pre
            pre = cur;
            cur = temp;
        }
        node.next = null
        return pre;
    }
}


fun main(args: Array<String>) {
    var linkList = LinkList()
    for (i in 0..10) {
        linkList.add(i)
    }
    linkList.print(linkList.head)

    linkList.print(linkList.reverse(linkList.head))
}

