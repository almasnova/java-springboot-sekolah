package org.test.sekolah;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.test.sekolah.controller.AuthController;
import org.test.sekolah.util.Constants;

import java.util.Calendar;
import java.util.TimeZone;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("org.test.sekolah")
@EnableAsync
public class Main {
    private static final Logger logger = LogManager.getLogger(AuthController.class);

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        TimeZone localTimeZone = calendar.getTimeZone();
        System.out.println("TIme Zone: "+localTimeZone);
        System.out.println("TIme Zone ID: "+localTimeZone.getID());
        if (!Constants.timeZones.contains(localTimeZone.getID())) {
            logger.warn("Please set your timezone");
            return;
        }
        SpringApplication.run(Main.class, args);
    }
}
