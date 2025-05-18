package com.qnxy;

import com.qnxy.data.LaunchUrlReq;
import com.qnxy.data.TerminalType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

/**
 * @author Qnxy
 */
@SpringBootApplication
public class ApplicationMain implements InitializingBean {

    private final GameClient gameClient;

    public ApplicationMain(GameClient gameClient) {
        this.gameClient = gameClient;
    }


    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        final var launchUrlReq = new LaunchUrlReq(
                "126",
                "testSession",
                "test_player_unique_id",
                "BR_BRL",
                "pt",
                90,
                new BigDecimal("1000.01"),
                TerminalType.PHONE,
                null,
                null
        );
        final var gameLaunchUrlRespReturnValue = this.gameClient.launchUrlBlocking(launchUrlReq);

        System.out.println("gameLaunchUrlRespReturnValue: " + gameLaunchUrlRespReturnValue);


    }
}
