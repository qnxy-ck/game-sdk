package com.qnxy.data

import kotlinx.serialization.Serializable

/**
 * @author Qnxy
 */
@Serializable
public data class ReturnValue<T>(
    val code: String,
    val msg: String,
    val data: T?
) {

    public fun isSuccessful(): Boolean {
        return code == "C10000" && data != null
    }


    public companion object {

        @JvmStatic
        public fun <T> successful(data: T): ReturnValue<T> {
            return ReturnValue("C10000", "success", data)
        }

        @JvmStatic
        public fun <T> failed(msg: String): ReturnValue<T> {
            return ReturnValue("", msg, null)
        }

    }


}
