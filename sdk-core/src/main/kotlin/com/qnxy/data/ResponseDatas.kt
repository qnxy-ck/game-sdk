package com.qnxy.data

import com.qnxy.internal.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * @author Qnxy
 */
@Serializable
public data class GameLaunchUrlResp(val url: String)


@Serializable
public data class VerifySessionResp(
    @Serializable(with = BigDecimalSerializer::class)
    val balance: BigDecimal,
    val nickName: String? = null,
    val avatarUrl: String? = null
) {
    public constructor(balance: BigDecimal) : this(balance, null, null)
}

@Serializable
public data class BalanceResp(
    @Serializable(with = BigDecimalSerializer::class)
    val balance: BigDecimal
)