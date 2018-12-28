package heap

/**
 * 根节点从1开始
 * parent = k/2
 * lchild = i * 2
 * rchild = i * 2 +1
 */
class MaxHeap {
    var mArr: IntArray
    var mCount: Int = 0

    constructor(size: Int) {
        mArr = IntArray(size + 1)
    }

    fun insert(newVal: Int) {
        assert(mCount + 1 < mArr.size)
        mArr[++mCount] = newVal
        shiftUp(mCount)
    }

    fun extractMax(): Int {
        assert(mCount > 0)
        var result = mArr[1]
        if (mCount > 1) {
            mArr[1] = mArr[mCount]
            shiftDown(1)
        }
        mCount--
        return result
    }

    fun isEmpty(): Boolean {
        return mCount <= 0
    }

    /**
     * 插入元素是插入到末尾，这时候需要与父亲对比，如果父亲大就交换值
     */
    private fun shiftUp(count: Int) {
        var k = count
        while (k > 1) {
            if (mArr[k / 2] < mArr[k]) {
                var tmp = mArr[k / 2]
                mArr[k / 2] = mArr[k]
                mArr[k] = tmp
            }
            k /= 2
        }
    }

    /**
     * extractMax的时候会先删除根节点，然后将最后的值补充到根节点，这样就要
     * 将根节点和孩子对比，然后和最大的孩子交换位置
     */
    private fun shiftDown(start: Int) {
        var k = start//parent
        var j = 2 * k
        while (j <= mCount) {
            //find max from its children
            if (j + 1 <= mCount && mArr[j + 1] > mArr[j]) {
                j++
            }
            //swap(k,j) when k < j
            if (mArr[k] < mArr[j]) {
                var tmp = mArr[k]
                mArr[k] = mArr[j]
                mArr[j] = tmp
            }
            k = j
            j = 2 * k
        }
    }
}

fun main(args: Array<String>) {
    var maxHeap = MaxHeap(100)
    for (i in 1..30) {
        maxHeap.insert((Math.random() * 10).toInt())
    }

    while (!maxHeap.isEmpty()) {
        println(maxHeap.extractMax())
    }

}


