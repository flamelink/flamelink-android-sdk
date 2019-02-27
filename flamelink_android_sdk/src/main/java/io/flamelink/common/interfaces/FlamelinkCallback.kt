package io.flamelink.common.interfaces

interface FlamelinkCallBack {
    fun <T, E> onAsyncMapResponse(response: Map<E, T>)

    fun <T> onAsyncListResponse(response: List<T>)

    fun <T> onAsyncResponse(response: T)
}

interface FlamelinkListCallback {
    fun <T> response(response: List<T>)
}