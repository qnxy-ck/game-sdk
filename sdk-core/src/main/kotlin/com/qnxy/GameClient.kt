package com.qnxy

import com.qnxy.data.GameCategoryListResponse
import com.qnxy.data.GameLaunchUrlResp
import com.qnxy.data.LaunchUrlReq
import com.qnxy.data.ReturnValue
import com.qnxy.internal.InternalHttpClient
import io.ktor.util.reflect.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.serializer
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

    public suspend fun categoryInfo(): ReturnValue<GameCategoryListResponse?> {
        return internalHttpClient.post(
            "/game/getCategory",
            Unit,
            Unit.serializer(),
            typeInfo<ReturnValue<GameCategoryListResponse?>>()
        )
    }

    public fun categoryInfoFuture(): CompletableFuture<ReturnValue<GameCategoryListResponse?>> {
        return internalHttpClient.future { categoryInfo() }
    }

    public fun categoryInfoBlocking(): ReturnValue<GameCategoryListResponse?> {
        return runBlocking { categoryInfo() }
    }

    public enum class EndpointEnvironment(public val url: String) {
        TEST("https://pre-api.agsoftgames.com/game/v3"),
        PROD("https://api.agsoftgames.com/game/v3")
    }


}