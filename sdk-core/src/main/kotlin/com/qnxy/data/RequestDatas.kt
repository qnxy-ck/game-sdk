package com.qnxy.data

import com.qnxy.internal.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * @author Qnxy
 */
@Serializable
public data class LaunchUrlReq(
    val gameCode: String,
    val playerSession: String,
    val playerUniqueId: String,
    val currencyCode: String,
    val language: String,
    val rtp: Int,
    @Serializable(with = BigDecimalSerializer::class)
    val balance: BigDecimal,
    val terminalType: TerminalType,
    val subMerchantCode: String? = null,
    val returnUrl: String? = null
)

@Serializable
public data class SetPlayerRtpReq(
    val currencyCode: String,
    val rtp: Int,
    val playerUniqueId: String,
    val subMerchantCode: String? = null
)


@Serializable
public data class VerifySessionReq(
    val playerSession: String,
    val currencyCode: String
)

@Serializable
public data class BalanceReq(
    val playerSession: String,
    val currencyCode: String

)

@Serializable
public data class BetReq(
    val playerUniqueId: String,
    val currencyCode: String,
    val gameCode: String,
    val roundId: String,
    val orderNo: String,
    @Serializable(with = BigDecimalSerializer::class)
    val betAmount: BigDecimal
)


@Serializable
public data class WinReq(
    val playerUniqueId: String,
    val currencyCode: String,
    val gameCode: String,
    val roundId: String,
    val orderNo: String,
    val betOrderNo: String,
    @Serializable(with = BigDecimalSerializer::class)
    val betAmount: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val winAmount: BigDecimal,
    val type: WinType,
    val isEnd: Boolean
) {

    @Serializable
    public enum class WinType {
        @SerialName("win")
        WIN,

        @SerialName("bet_win")
        BET_WIN;
    }
}




