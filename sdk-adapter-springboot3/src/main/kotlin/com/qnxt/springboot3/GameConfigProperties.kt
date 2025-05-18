package com.qnxt.springboot3

import com.qnxy.GameClient
import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * @author Qnxy
 */
@ConfigurationProperties(prefix = "game.config")
public data class GameConfigProperties(
    val enabled: Boolean = false,
    val merchantCode: String,
    val merchantKey: String,
    val callbackUri: String,
    val endpointEnvironment: GameClient.EndpointEnvironment = GameClient.EndpointEnvironment.TEST
) {
    
    init {
        require(merchantCode.isNotBlank()) { "merchantCode must not be blank" }
        require(merchantKey.isNotBlank()) { "merchantKey must not be blank" }
    }
}
