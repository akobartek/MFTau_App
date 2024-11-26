package pl.mftau.mftau.common.utils

fun <T> List<T>.swap(index1: Int, index2: Int): List<T> {
    val list = this.toMutableList()
    val tmp = this[index1]
    list[index1] = this[index2]
    list[index2] = tmp
    return list
}