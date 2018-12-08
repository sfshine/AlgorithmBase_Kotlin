package number

fun main(args: Array<String>) {
    println("123 reverse: ${reverseNum(123)}")
    println("8888 reverse: ${reverseNum(8888)}")
    println("54456 reverse: ${reverseNum(54456)}")
    println("1564654 reverse: ${reverseNum(1564654)}")
}

fun reverseNum(num: Int): Int {
    //对n与10取余数获取第一位
    //结果 = 结果* 10 +  这个余数
    //对n整除获取下一个值
    //终止条件: n = 0
    var n = num
    var res = 0
    while (n != 0) {
        var last = n % 10
        res = 10 * res + last
        n = n / 10
    }
    return res
}

fun reverseNumWithMath(num: Int): Int {
    //对n与10取余数获取第一位
    //结果 += 这个余数 * 10^Math.log(n)
    //对n整除获取下一个值
    //终止条件: n = 0
    var n = num
    var res = 0
    while (n != 0) {
        var last = n % 10
        var power = Math.log10(n.toDouble()).toInt()
        res += last * Math.pow(10.0, power.toDouble()).toInt()
        n = n / 10
    }
    return res
}