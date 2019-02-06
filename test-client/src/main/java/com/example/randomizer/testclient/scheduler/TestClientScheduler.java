package com.example.randomizer.testclient.scheduler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.example.randomizer.model.ClientServiceReading;
import com.example.randomizer.model.Random;

@Component
public class TestClientScheduler {
	
	private static final int NUMBER_OF_CONCURRENT_REQUESTS = 100;
	private static final int NUMBER_OF_RANDOM_NUMBERS = 40;
	
	@Autowired
	private StaticTestClientReadings staticTestClientReadings;
	
	@Scheduled(fixedRate=5000)
	public void doScheduledTask() {
		System.out.println("starting tests");
		System.out.println("**********************************");
		for(ClientServiceReading reading: staticTestClientReadings.getClientServiceReadings()) {
			Thread[] threads = new Thread[NUMBER_OF_CONCURRENT_REQUESTS];
			long[] responseTimes = new long[NUMBER_OF_CONCURRENT_REQUESTS];
			for(int i = 0; i < NUMBER_OF_CONCURRENT_REQUESTS; i++) {
				final int idx = i;
				threads[i] = new Thread() {
					public void run() {
						for(int j = 0; j < NUMBER_OF_RANDOM_NUMBERS; j++) {
							responseTimes[idx] = getAccessTimeInMs(reading.getUrl() + "/" + (j + 1));
						}
					}
				};
				threads[i].start();
			}
			try {
				for(int i = 0; i < NUMBER_OF_CONCURRENT_REQUESTS; i++) {
					threads[i].join(4000);
				}
				long sum = 0;
				for(int i = 0; i < NUMBER_OF_CONCURRENT_REQUESTS; i++) {
					sum += responseTimes[i];
				}
				reading.setLatestReading(sum/NUMBER_OF_CONCURRENT_REQUESTS);
			} catch(InterruptedException e) {
				e.printStackTrace();
				reading.setLatestReading(4000);
			}
		}
		System.out.println("**********************************");
	}
	private long getAccessTimeInMs(String url) {
/* Couldn't get RestTemplate to handle errors correctly
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ResponseErrorHandler() {
			
			@Override
			public boolean hasError(ClientHttpResponse arg0) throws IOException {
				// TODO Auto-generated method stub
				System.out.println("hasError");
				return true;
			}
			
			@Override
			public void handleError(ClientHttpResponse arg0) throws IOException {
				System.out.println("handleError");
				// TODO Auto-generated method stub
			}
		});
		long start = System.currentTimeMillis();
		ResponseEntity<Random> response = restTemplate.getForEntity(url, Random.class);
		long end = System.currentTimeMillis();
		if(HttpStatus.OK == response.getStatusCode()) {
			return end - start;
		} else {
			System.out.println("XXXXXXXXXXXXX");
			return 5000;//TODO punish failure somehow else?
		}
		*/
		try {
			URL obj = new URL(url);
			long start = System.currentTimeMillis();
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			long end = System.currentTimeMillis();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				return end - start;
			} else {
				return 5000;
			}
		} catch (IOException e) {
			return 5000;
		}
	}
}
