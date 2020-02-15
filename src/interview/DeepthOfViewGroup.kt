package interview

fun main(args: Array<String>) {
    var contentView = initViewGroupDeepth1()
    println("depthOfViewGroup ${depthOfViewGroup(contentView)}")

    contentView = initViewGroupDeepth2()
    println("depthOfViewGroup ${depthOfViewGroup(contentView)}")

    contentView = initViewGroupDeepth3()
    println("depthOfViewGroup ${depthOfViewGroup(contentView)}")

    contentView = initViewGroupDeepth5()
    println("depthOfViewGroup ${depthOfViewGroup(contentView)}")
}

/**
 *  来到这个函数, 默认的深度是deepth = 1
 *  如果没有孩子,则返回 1, 此为退出条件
 *  否则寻找孩子中最大的深度max
 */
private fun depthOfViewGroup(view: View): Int {
    return if (view !is ViewGroup || view.childs.isEmpty()) {
        1
    } else {
        var max = 1
        for (child in view.childs) {
            max = Math.max(max, depthOfViewGroup(child) + 1)//孩子深度还要加上父亲的层级
        }
        return max
    }
}

private fun initViewGroupDeepth1(): ViewGroup {
    var contentView = ViewGroup(0)
    return contentView
}

private fun initViewGroupDeepth2(): ViewGroup {

    var contentView = ViewGroup(0)

    var vg11 = ViewGroup(11)
    contentView.addView(vg11)
    contentView.addView(View(12))
    contentView.addView(View(13))

    return contentView
}

private fun initViewGroupDeepth3(): ViewGroup {

    var contentView = ViewGroup(0)

    var vg11 = ViewGroup(11)
    contentView.addView(vg11)
    contentView.addView(View(12))
    contentView.addView(View(13))

    var vg21 = ViewGroup(21)
    vg11.addView(vg21)

    return contentView
}

private fun initViewGroupDeepth5(): ViewGroup {

    var contentView = ViewGroup(0)

    var vg11 = ViewGroup(11)
    contentView.addView(vg11)
    contentView.addView(View(12))
    contentView.addView(View(13))

    var vg21 = ViewGroup(21)
    vg11.addView(vg21)


    var vg31 = ViewGroup(31)
    vg31.addView(View(41))
    vg21.addView(vg31)
    return contentView
}

open class View(var id: Int) {
    open fun print() {
        println("I am view, id = ${id}")
    }
}

class ViewGroup(id: Int) : View(id) {
    var childs = ArrayList<View>()

    fun addView(view: View) {
        childs.add(view)
    }

    override fun print() {
        println("I am ViewGroup, id = ${id}")
    }
}

