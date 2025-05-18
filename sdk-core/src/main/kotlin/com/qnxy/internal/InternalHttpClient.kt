package com.qnxy.internal

import com.qnxy.data.ReturnValue
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.reflect.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.future.future
import kotlinx.serialization.KSerializer
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * @author Qnxy
 */
internal class InternalHttpClient(
    private val baseUrl: String,
    private val merchantCode: String,
    merchantKey: String,
) {

    private companion object {
        private const val ALGORITHM = "HmacSHA256"
        private val MAC = Mac.getInstance(ALGORITHM)
    }

    init {
        MAC.init(SecretKeySpec(merchantKey.toByteArray(), ALGORITHM))
    }

    private val httpClient: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(jsonInstance)
        }
        engine {
            proxy = ProxyBuilder.http("http://127.0.0.1:7897")
        }
    }


    fun <R> future(block: suspend CoroutineScope.() -> R) = httpClient.future(block = block)

    suspend fun <REQ, RESP> post(requestPath: String, reqData: REQ, reqSerializer: KSerializer<REQ>, returnType: TypeInfo): ReturnValue<RESP?> {
        return httpClient.post(baseUrl + requestPath) {
            val jsonData = jsonInstance.encodeToString(reqSerializer, reqData)
            setHeaders(jsonData)

            setBody(jsonData)
        }.body(returnType)
    }

    private fun HttpRequestBuilder.setHeaders(jsonData: String) {
        val timestamp = System.currentTimeMillis().toString()
        val nonce = UUID.randomUUID().toString().replace("-", "")

        headers {
            contentType(ContentType.Application.Json)
            append("x-merchant-code", merchantCode)
            append("x-timestamp", timestamp)
            append("x-nonce", nonce)
            append("x-content-processing-type", ALGORITHM)
            append("x-sign", sign(jsonData, nonce, timestamp))
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun sign(data: String, nonce: String, timestamp: String): String {
        val data = merchantCode + timestamp + nonce + ALGORITHM + data
        return MAC.doFinal(data.toByteArray()).toHexString()
    }
}