package pl.mftau.mftau.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverters {

    @TypeConverter
    fun listToString(list: List<String>?): String {
        if (list == null) return ""

        val stringBuilder = StringBuilder()
        list.forEachIndexed { index, it ->
            if (index == 0)
                stringBuilder.append(it)
            else
                stringBuilder.append(",$it")
        }
        return stringBuilder.toString()
    }

    @TypeConverter
    fun stringToList(value: String?): List<String>? {
        if (value == null) return listOf()

        return value.split(",")
    }

    @TypeConverter
    fun arrayListToJson(list: ArrayList<String>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun jsonToArrayList(value: String?): ArrayList<String>? {
        if (value == null) return arrayListOf()

        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType) as ArrayList<String>
    }
}