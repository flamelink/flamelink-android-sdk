package io.flamelink.common.types

enum class EventTypes(value: String) {
    CHILD_ADDED("child_added"),
    CHILD_CHANGED("child_changed"),
    CHILD_REMOVED("child_removed"),
    CHILD_MOVED("child_moved"),
    VALUE("value")
}