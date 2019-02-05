package com.example.randomizer.testclient.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.randomizer.model.ClientServiceReading;
import com.example.randomizer.model.Random;

@Component
public class TestClientScheduler {
	@Autowired
	private StaticTestClientReadings staticTestClientReadings;
	
	@Scheduled(fixedRate=5000)
	public void doScheduledTask() {
		System.out.println("starting tests");
		System.out.println("**********************************");
		for(ClientServiceReading reading: staticTestClientReadings.getClientServiceReadings()) {
			long time = getAccessTimeInMs(reading.getUrl());
			reading.setLatestReading(time);
		}
		System.out.println("**********************************");
	}
	private long getAccessTimeInMs(String url) {
		RestTemplate restTemplate = new RestTemplate();
		long start = System.currentTimeMillis();
		Random random = restTemplate.getForObject(url, Random.class);
		long end = System.currentTimeMillis();
		return end - start;
	}
}
