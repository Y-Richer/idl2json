import java.io.File
import kotlin.random.Random

val intType = arrayListOf("double", "float", "int32", "int64",
    "uint32", "uint64", "sint32", "sint64", "fixed32", "fixed64", "sfixed32","sfixed64")
fun getRandomValue(type: String?): String {
    if (intType.contains(type) == true) {
        return Random.nextInt(10000).toString()
    } else if ("string".equals(type)) {
        val index = Random.nextInt(text.size)
        return "\"${text[index]}\""
    } else if ("bool".equals(type)) {
        return "false"
    } else if (!type.isNullOrEmpty()) {
        return struct2Json(type)
    } else {
        throw RuntimeException("param type should not be null~")
    }
}

fun struct2Json(target: String): String {

    val typeRegex = Regex("\\b(\\w+)\\s+${target}\\s*\\{")
    val typeMatch = typeRegex.find(idlContent)
    val structType = typeMatch?.groupValues?.getOrNull(1) ?: kotlin.run {
        throw RuntimeException("${target} struct not found~")
    }

    val propRegex = Regex("${target}\\s*\\{([^\\}]*)\\}")
    val propMatch = propRegex.find(idlContent)
    val props = propMatch?.groupValues?.getOrNull(1) ?: kotlin.run {
        throw RuntimeException("${target} struct not found~")
    }
    val propsList = props.split(";")

    val json = StringBuilder()
    if ("message".equals(structType)) {
        json.append("{")
        propsList.forEachIndexed { index, it ->
            val s = it.split("=")[0].trim()
            if (s.isNotEmpty()) {
                val prop = s.split(" ")
                val lastIndex = prop.size - 1
                val name = prop.getOrNull(lastIndex)
                val type = prop.getOrNull(lastIndex - 1)
                val repeat = prop.getOrNull(lastIndex - 2)
                json.append("\"${name}\": ")
                if ("repeated".equals(repeat)) {
                    json.append("[")
                    for (i in 0 until 3) {
                        json.append(getRandomValue(type) + ", ")
                    }
                    json.append("]")
                } else {
                    json.append(getRandomValue(type))
                }
                if (index < propsList.size - 1) {
                    json.append(", ")
                }
            }
        }
        json.append("}")
    } else if ("enum".equals(structType)) {
        val randomIndex = Random.nextInt(propsList.size-1)
        val value = propsList.get(randomIndex).split("=").getOrNull(1)?.trim()
        value?.let {
            json.append(it)
        }
    } else {
        throw RuntimeException("unknow struct type~")
    }
    return json.toString()
}

val text = arrayListOf("test1", "惺惺相惜重中之重", "xxxxx")

val idlFile = File("./idl_config.txt")
if (!idlFile.exists()) {

}
val idlContent = idlFile.readText()

println("请输入目标数据：")
val input = readLine()
input?.let {
    println("${struct2Json(it)}")
} ?: kotlin.run {
    println("输入不能为空")
}





