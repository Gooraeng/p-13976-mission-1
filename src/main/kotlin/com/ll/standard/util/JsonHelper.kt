package com.ll.standard.util

object JsonHelper {

    fun toMap(jsonString: String) : Map<String, Any> {
        return jsonString
            .removeSurrounding("{", "}")
            .split(",")
            .mapNotNull {
                // ':' 기준으로 리스트로 나누고 이를 기준으로 key, value 찾기
                // 이 때, 지정된 size (2) 를 만족하지 못하면 아예 추가하지 않도록
                val (key, value) = it.split(":", limit = 2)
                    .map(String::trim)
                    .takeIf { it.size == 2 }
                    ?: return@mapNotNull null

                val actualKey = key.removeSurrounding("\"")

                val actualValue = if (value.startsWith("\"") && value.endsWith("\"")) {
                    value.removeSurrounding("\"")
                } else {
                    value.toInt()
                }

                actualKey to actualValue
            }.toMap()
    }

    fun toString(list: List<Map<String, Any>>): String {
        return list.joinToString(
            prefix = "[\n", separator = ",\n", postfix = "\n]"
        ) {map -> toString(map).prependIndent("    ")}
    }

    fun toString(map: Map<String, Any>): String {
        return map.entries.joinToString(
            prefix = "{\n", separator = ",\n", postfix = "\n}"
        ) { (key, value) ->
            val finalKey = "\"$key\""
            val finalValue = when (value) {
                is String -> "\"$value\""
                else -> value
            }
            "    $finalKey: $finalValue"
        }
    }
}