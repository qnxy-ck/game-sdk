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
        return code == SUCCESS_CODE && data != null
    }


    public companion object {
        private const val SUCCESS_CODE = "C10000"

        @JvmStatic
        public fun <T> successful(data: T): ReturnValue<T> {
            return ReturnValue(SUCCESS_CODE, "success", data)
        }

        @JvmStatic
        @JvmOverloads
        public fun <T> failed(msg: String, code: String = ""): ReturnValue<T> {
            return ReturnValue("", msg, null)
        }

    }


}
