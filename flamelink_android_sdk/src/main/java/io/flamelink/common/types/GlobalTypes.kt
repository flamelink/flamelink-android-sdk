package io.flamelink.common.types

enum class GlobalTypes {
    ADMIN_EMAIL{
        override fun field(): String = "adminEmail"
    },
    DATE_FORMAT{
        override fun field(): String = "dateFormat"
    },
    ID{
        override fun field(): String = "id"
    },
    SITE_TITLE{
        override fun field(): String = "siteTitle"
    },
    TAGLINE{
        override fun field(): String = "tagline"
    },
    TIME_FORMAT{
        override fun field(): String = "timeFormat"
    },
    TIME_ZONE{
        override fun field(): String = "timeZone"
    },
    URL{
        override fun field(): String = "url"
    };

    abstract fun field(): String
}

data class Globals(
    var id: String? = "",
    var siteTitle: String? = "",
    var tagline: String? = "",
    var adminEmail: String? = "",
    var dateFormat: String? = "",
    var timeFormat: String? = "",
    var timeZone: Int? = 0
)