package com.example.randomizer.testclient.scheduler;

import java.util.ArrayList;
import java.util.List;

import com.example.randomizer.model.ClientServiceReading;

public class StaticTestClientReadings {
	private List<ClientServiceReading> clientServiceReadings;
	
	public StaticTestClientReadings() {
		clientServiceReadings = new ArrayList<ClientServiceReading>();
//		clientServiceReadings.add(new ClientServiceReading("http://localhost:8080/randomizer/random"));
		clientServiceReadings.add(new ClientServiceReading("http://randomizer-app.workshop-demo.svc:8080/randomizer/random"));
	}
	
	public List<ClientServiceReading> getClientServiceReadings() {
		return clientServiceReadings;
	}
}
