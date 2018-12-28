package recruit_and_backstracking

class LetterCombinationsOfAPhoneNumber {
    class Solution {
        var res: MutableList<String> = ArrayList()

        var mLatterMap = mapOf(
            0 to " ",
            1 to "",
            2 to "abc",
            3 to "def",
            4 to "ghi",
            5 to "jkl",
            6 to "mno",
            7 to "pqrs",
            8 to "tuv",
            9 to "wxyz"
        )

        fun letterCombinations(digits: String): List<String> {
            res.clear()
            if (digits.isNullOrBlank()) return res
            conbinationRecruit(digits, 0, "")
            return res
        }

        /**
         * 处理digits中第n个数字与前面字母的组合
         */
        fun conbinationRecruit(digits: String, index: Int, previousRes: String) {
            if (index >= digits.length) {
                res.add(previousRes)
                return
            }
            var digitInt = digits[index] - '0'
            assert((digitInt < 0 || digitInt > 9))

            var curDigitToStr = mLatterMap[digitInt]!!

            if (digitInt == 0 || digitInt == 1) {
                conbinationRecruit(digits, index + 1, previousRes + curDigitToStr)
            } else {
                for (latter in curDigitToStr.toCharArray()) {
                    conbinationRecruit(digits, index + 1, previousRes + latter)
                }
            }

        }
    }
}
fun main(args: Array<String>) {
    LetterCombinationsOfAPhoneNumber.Solution().letterCombinations("0238").forEach {
        println(it)
    }
}