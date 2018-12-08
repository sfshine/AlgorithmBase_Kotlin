package interview

fun main(args: Array<String>) {
    var contentView = initViewGroupDeepth1()
    println("maxDeepthOfViewGroup ${maxDeepthOfViewGroup(contentView)}")

    contentView = initViewGroupDeepth2()
    println("maxDeepthOfViewGroup ${maxDeepthOfViewGroup(contentView)}")

    contentView = initViewGroupDeepth3()
    println("maxDeepthOfViewGroup ${maxDeepthOfViewGroup(contentView)}")

    contentView = initViewGroupDeepth4()
    println("maxDeepthOfViewGroup ${maxDeepthOfViewGroup(contentView)}")
}

private fun maxDeepthOfViewGroup(view: View): Int {
    //来到这个函数, 默认的深度是deepth = 1
    //如果是view, 则返回 deepth
    //如果是viewgroup
    //  如果vg没有孩子返回 deepth
    //  如果vg有孩子
    //  寻找孩子中最大的深度max, deepth += max
    var deepth = 1
    if (view is ViewGroup) {
        if (view.childs.isEmpty()) {
            return deepth
        } else {
            var max = 0
            for (child in view.childs) {
                max = Math.max(max, maxDeepthOfViewGroup(child))
            }
            deepth += max
            return deepth
        }
    } else {
        return deepth
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

private fun initViewGroupDeepth4(): ViewGroup {

    var contentView = ViewGroup(0)

    var vg11 = ViewGroup(11)
    contentView.addView(vg11)
    contentView.addView(View(12))
    contentView.addView(View(13))

    var vg21 = ViewGroup(21)
    vg11.addView(vg21)


    vg21.addView(View(31))
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

