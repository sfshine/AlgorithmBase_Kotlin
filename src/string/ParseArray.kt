//1213SADFASF1#@￥#@DD111=> 1213,1,111
fun main(array: Array<String>) {
    val str = "1213SADFASF1#@￥#@DD111"
    println(parseArray(str).joinToString(","))
}

fun parseArray(str: String): List<String> {
    return str.split(Regex("[^0-9]")).filter {
        !it.isEmpty()
    }
}
