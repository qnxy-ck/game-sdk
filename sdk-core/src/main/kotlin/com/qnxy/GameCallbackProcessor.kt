package com.qnxy

import com.qnxy.internal.jsonInstance
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * @author Qnxy
 */
public class GameCallbackProcessor(
    private val merchantCode: String,
    merchantKey: String,
    private val handler: GameCallbackHandler,
) {

    private companion object {
        private const val ALGORITHM = "HmacSHA256"
        private val MAC = Mac.getInstance(ALGORITHM)

    }

    init {
        MAC.init(SecretKeySpec(merchantKey.toByteArray(), ALGORITHM))
    }

    public fun handler(headerMap: Map<String, String>, data: String, path: String): String {
        checkCallbackInfo(headerMap, data)

        return when (path) {
            "verifySession" -> handler.verifySession(jsonInstance.decodeFromString(data))
                .let { jsonInstance.encodeToString(it) }

            "getBalance" -> handler.getBalance(jsonInstance.decodeFromString(data))
                .let { jsonInstance.encodeToString(it) }

            "bet" -> handler.bet(jsonInstance.decodeFromString(data))
                .let { jsonInstance.encodeToString(it) }

            "win" -> handler.win(jsonInstance.decodeFromString(data))
                .let { jsonInstance.encodeToString(it) }

            else -> throw GameCallbackException("Unknown callback type: $path")
        }
    }

    private fun checkCallbackInfo(headerMap: Map<String, String>, data: String) {
        val m = headerMap.mapKeys { it.key.lowercase() }

        val merchantCode = m.requireNonBlank("x-merchant-code").takeIf { it == this.merchantCode } ?: throw IllegalArgumentException("merchant code is not matching $merchantCode")
        val sign = m.requireNonBlank("x-sign")
        val timestamp = m.requireNonBlank("x-timestamp")
        val nonce = m.requireNonBlank("x-nonce")
        val contentProcessingType = m.requireNonBlank("x-content-processing-type")

        verifySign(merchantCode, sign, timestamp, nonce, contentProcessingType, data)
    }

    private fun Map<String, String>.requireNonBlank(key: String): String {
        return this[key]?.takeIf { it.isNotBlank() } ?: throw GameCallbackException("Missing $key")
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun verifySign(merchantCode: String, sign: String, timestamp: String, nonce: String, contentProcessingType: String, data: String) {
        val d = merchantCode + timestamp + nonce + contentProcessingType + data

        if (MAC.doFinal(d.toByteArray()).toHexString() != sign)
            throw GameCallbackException("Invalid sign string: $sign")
    }


}