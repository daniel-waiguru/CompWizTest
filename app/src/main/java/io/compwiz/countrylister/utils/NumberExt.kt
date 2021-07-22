package io.compwiz.countrylister.utils

import java.text.DecimalFormat

fun Int.format(): String {
    val formatter = DecimalFormat("##,###,###.##")
    return formatter.format(this)
}