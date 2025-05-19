package com.qnxy.data

import com.qnxy.internal.BigDecimalSerializer
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
public data class VerifySessionReq(
    val playerSession: String,
    val currencyCode: String
)

@Serializable
public data class BalanceReq(
    val playerSession: String,
    val currencyCode: String

)