package com.example.randomizer.testclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.randomizer.testclient.scheduler.StaticTestClientReadings;
import com.example.randomizer.testclient.scheduler.TestClientServiceDiscovery;

@SpringBootApplication
@EnableScheduling
@Configuration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public StaticTestClientReadings getStaticTestClientLocations() {
    	return new StaticTestClientReadings();
    }

    @Bean
    public TestClientServiceDiscovery getTestClientServiceDiscovery() {
    	return new TestClientServiceDiscovery();
    }
}

