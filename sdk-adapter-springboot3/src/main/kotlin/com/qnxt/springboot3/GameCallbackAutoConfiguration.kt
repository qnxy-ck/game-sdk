package com.qnxt.springboot3

import com.qnxy.GameCallbackHandler
import com.qnxy.GameCallbackProcessor
import com.qnxy.GameClient
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

/**
 * @author Qnxy
 */
@AutoConfiguration
@EnableConfigurationProperties(GameConfigProperties::class)
@ConditionalOnProperty(prefix = "game.config", name = ["enabled"], havingValue = "true")
public open class GameCallbackAutoConfiguration(
    private val gameConfigProperties: GameConfigProperties
) {

    private companion object {
        private val log = LoggerFactory.getLogger(GameCallbackAutoConfiguration::class.java)
    }

    @Bean
    public open fun gameClient(): GameClient {
        return GameClient(
            gameConfigProperties.endpointEnvironment,
            gameConfigProperties.merchantCode,
            gameConfigProperties.merchantKey
        )
            .also { log.info("GameClient bean initialized") }
    }

    @Bean
    @ConditionalOnBean(GameCallbackHandler::class)
    public open fun gameCallbackController(gameCallbackHandler: GameCallbackHandler): GameCallbackController = GameCallbackController(
        GameCallbackProcessor(
            gameConfigProperties.merchantCode,
            gameConfigProperties.merchantKey,
            gameCallbackHandler
        )
            .also { log.info("GameCallbackProcessor bean initialized") }
    )


}