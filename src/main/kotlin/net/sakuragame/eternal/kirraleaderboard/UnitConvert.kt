package net.sakuragame.eternal.kirraleaderboard

import java.text.DecimalFormat
import kotlin.math.abs


@Suppress("DuplicatedCode")
enum class UnitConvert(value: Long, symbol_cn: String, symbol_en: String) {

    THOUSAND(1000, "千", "K"),
    TEN_THOUSAND(10000, "万", "W"),
    MILLION(100000000, "百万", "M");

    private val value: Double
    private val symbolCN: String
    private val symbolEN: String

    init {
        this.value = value.toDouble()
        this.symbolCN = symbol_cn
        this.symbolEN = symbol_en
    }

    companion object {

        @JvmOverloads
        fun formatCN(unitConvert: UnitConvert, value: Double, decimalPlace: Int = 0): String {
            var cnValue = value
            val negative = cnValue < 0
            cnValue = abs(cnValue)
            return if (cnValue >= unitConvert.value) {
                (if (negative) "-" else "") + getFormat(decimalPlace).format(cnValue / unitConvert.value) + unitConvert.symbolCN
            } else (if (negative) "-" else "") + cnValue.toInt() + ""
        }

        @JvmOverloads
        fun formatEN(unitConvert: UnitConvert, value: Double, decimalPlace: Int = 0): String {
            var cnValue = value
            val negative = cnValue < 0
            cnValue = abs(cnValue)
            return if (cnValue >= unitConvert.value) {
                (if (negative) "-" else "") + getFormat(decimalPlace).format(cnValue / unitConvert.value) + unitConvert.symbolEN
            } else (if (negative) "-" else "") + cnValue.toInt() + ""
        }

        private fun getFormat(size: Int): DecimalFormat {
            val sb = StringBuilder("0")
            if (size > 0) {
                sb.append(".")
                for (i in 0 until size) {
                    sb.append("0")
                }
            }
            return DecimalFormat(sb.toString())
        }
    }
}