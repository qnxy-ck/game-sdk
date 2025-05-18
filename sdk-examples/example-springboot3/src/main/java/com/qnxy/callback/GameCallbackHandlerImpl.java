package com.qnxy.callback;

import com.qnxy.GameCallbackHandler;
import com.qnxy.data.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * @author Qnxy
 */
@Component
public class GameCallbackHandlerImpl implements GameCallbackHandler {

    @Override
    public @NotNull ReturnValue<VerifySessionResp> verifySession(@NotNull VerifySessionReq verifySessionReq) {
        return ReturnValue.failed("Not yet implemented");
    }

    @Override
    @NotNull
    public ReturnValue<BalanceResp> getBalance(@NotNull BalanceReq balanceReq) {
        return ReturnValue.failed("Not yet implemented");
    }

}
