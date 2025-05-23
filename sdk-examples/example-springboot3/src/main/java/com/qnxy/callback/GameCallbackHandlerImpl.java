package com.qnxy.callback;

import com.qnxy.GameCallbackHandler;
import com.qnxy.data.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Qnxy
 */
@Component
@Slf4j
public class GameCallbackHandlerImpl implements GameCallbackHandler {

    private final BigDecimal amount = BigDecimal.valueOf(1000);

    @Override
    public @NotNull ReturnValue<VerifySessionResp> verifySession(@NotNull VerifySessionReq verifySessionReq) {
        log.info("verifySession: {}", verifySessionReq);
        return ReturnValue.successful(new VerifySessionResp(amount));
    }

    @Override
    @NotNull
    public ReturnValue<BalanceResp> getBalance(@NotNull BalanceReq balanceReq) {
        log.info("getBalance: {}", balanceReq);
        return ReturnValue.successful(new BalanceResp(amount));
    }

    @Override
    @NotNull
    public ReturnValue<WinResp> win(@NotNull WinReq winReq) {
        log.info("win: {}", winReq);
        val b = switch (winReq.getType()) {
            case WIN -> amount.add(winReq.getWinAmount());
            case BET_WIN -> {
                val r = winReq.getWinAmount().subtract(winReq.getBetAmount());
                yield amount.add(r);
            }
        };
        return ReturnValue.successful(new WinResp(nextId(), b));
    }

    @Override
    @NotNull
    public ReturnValue<BetResp> bet(@NotNull BetReq betReq) {
        log.info("bet: {}", betReq);
        return ReturnValue.successful(new BetResp(nextId(), amount.subtract(betReq.getBetAmount())));
    }

    private static String nextId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
