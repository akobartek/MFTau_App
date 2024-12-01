package pl.mftau.mftau.common.utils

fun <T> List<T>.swap(from: Int, to: Int): List<T> {
    if (from == to) return this

    val list = this.toMutableList()
    val tmp = this[from]
    list[from] = this[to]
    list[to] = tmp
    return list
}