package com.qnxy

import com.qnxy.data.*

/**
 * @author Qnxy
 */
public interface GameCallbackHandler {

    public fun verifySession(verifySessionReq: VerifySessionReq): ReturnValue<VerifySessionResp>

    public fun getBalance(balanceReq: BalanceReq): ReturnValue<BalanceResp>

    public fun bet(betReq: BetReq): ReturnValue<BetResp>

    public fun win(winReq: WinReq): ReturnValue<WinResp>

}