package com.breader;

import com.breader.config.AwsConfig;
import com.breader.config.TestcontainersConfig;
import com.breader.config.TicketDbConfig;
import org.springframework.boot.SpringApplication;

public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.from(Application::main)
                .with(TestcontainersConfig.class, AwsConfig.class, TicketDbConfig.class)
                .run(args);
    }
}
