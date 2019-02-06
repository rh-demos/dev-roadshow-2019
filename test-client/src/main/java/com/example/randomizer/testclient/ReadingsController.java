package com.example.randomizer.testclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.randomizer.model.ClientServiceReading;
import com.example.randomizer.testclient.scheduler.StaticTestClientReadings;

@RestController
public class ReadingsController {
	
	@Autowired
	private StaticTestClientReadings staticTestClientReadings;

    @RequestMapping("/readings/{idx}")
    public ClientServiceReading clientServiceReadingById(@PathVariable int idx) {
    	return staticTestClientReadings.getClientServiceReadings().get(idx);
    }
    @RequestMapping("/readings")
    public List<ClientServiceReading> clientServiceReading() {
    	return staticTestClientReadings.getClientServiceReadings();
    }
}
