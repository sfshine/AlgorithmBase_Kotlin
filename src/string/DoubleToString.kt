package string

/**
 * 字符串转double
 */
fun main(args: Array<String>) {
    println(strToDouble("5.0"))
    println(strToDouble(".0"))
    println(strToDouble("1."))
    println(strToDouble("1.556"))
}

fun strToDouble(str: String): Double {
    var regex = Regex("[0-9]+(\\.[0-9]+){0,1}")//正则[0-9] 后面如果有就必须是 .[0-9]+或者没有
    if (!regex.matches(str)) {
        return 0.0
    }
    //先求最大位
    var maxCarry = str.indexOf(".") - 1
    var result = 0.0
    var charArray = str.toCharArray()
    //for循环cha数组,只要不是".", 就 值 * 10的carry次方
    for (ch in charArray) {
        if (ch != '.') {
            result += Math.pow(10.0, maxCarry.toDouble()) * (ch - '0')
            maxCarry--
        }
    }
    return result
}

