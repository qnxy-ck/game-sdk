package com.qnxy

import com.qnxy.data.GameLaunchUrlResp
import com.qnxy.data.LaunchUrlReq
import com.qnxy.data.ReturnValue
import com.qnxy.internal.InternalHttpClient
import io.ktor.util.reflect.*
import kotlinx.coroutines.runBlocking
import java.util.concurrent.CompletableFuture


/**
 * @author Qnxy
 */
public class GameClient(
    environment: EndpointEnvironment,
    merchantCode: String,
    merchantKey: String
) {


    private val internalHttpClient = InternalHttpClient(environment.url, merchantCode, merchantKey)


    public suspend fun launchUrl(launchUrlReq: LaunchUrlReq): ReturnValue<GameLaunchUrlResp?> {
        return internalHttpClient.post(
            "/game/launchUrl",
            launchUrlReq,
            LaunchUrlReq.serializer(),
            typeInfo<ReturnValue<GameLaunchUrlResp?>>()
        )
    }

    public fun launchUrlFuture(launchUrlReq: LaunchUrlReq): CompletableFuture<ReturnValue<GameLaunchUrlResp?>> {
        return internalHttpClient.future { launchUrl(launchUrlReq) }
    }

    public fun launchUrlBlocking(launchUrlReq: LaunchUrlReq): ReturnValue<GameLaunchUrlResp?> {
        return runBlocking { launchUrl(launchUrlReq) }
    }


    public enum class EndpointEnvironment(public val url: String) {
        TEST("https://pre-api.agsoftgames.com/game/v3"),
        PROD("https://api.agsoftgames.com/game/v3")
    }


}