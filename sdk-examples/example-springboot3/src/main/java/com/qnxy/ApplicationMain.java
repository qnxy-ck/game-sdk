package com.qnxy;

import com.qnxy.data.LaunchUrlReq;
import com.qnxy.data.TerminalType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.math.BigDecimal;

/**
 * @author Qnxy
 */
@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ApplicationMain implements ApplicationListener<ApplicationReadyEvent> {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }


    private final GameClient gameClient;

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        val launchUrlReq = new LaunchUrlReq(
                "126",
                "testSession",
                "test_player_unique_id",
                "BR_BRL",
                "pt",
                90,
                new BigDecimal("1000.99"),
                TerminalType.PHONE,
                null,
                null
        );

        val r = this.gameClient.launchUrlBlocking(launchUrlReq);
        log.info("result: {}", r);
    }
}
