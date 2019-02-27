package io.flamelink.common.types

enum class ImageTypes(key: String) {
    WIDTH("width"),
    HEIGHT("height"),
    QUALITY("quality")
}

data class Image(
    var height: Long? = 0,
    var width: Long? = 0,
    var quality: Int? = 0
)