package com.example.randomizer.testclient.scheduler;

import java.util.ArrayList;
import java.util.List;

import com.example.randomizer.model.ClientServiceReading;

public class StaticTestClientReadings {
	private List<ClientServiceReading> clientServiceReadings;
	
	public StaticTestClientReadings() {
		clientServiceReadings = new ArrayList<ClientServiceReading>();
		clientServiceReadings.add(new ClientServiceReading("http://localhost:8080/randomizer/random/1"));
		clientServiceReadings.add(new ClientServiceReading("http://localhost:8080/randomizer/random/2"));
		clientServiceReadings.add(new ClientServiceReading("http://localhost:8080/randomizer/random/3"));
	}
	
	public List<ClientServiceReading> getClientServiceReadings() {
		return clientServiceReadings;
	}
}
