package com.example.randomizer.testclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.randomizer.model.ClientServiceReading;
import com.example.randomizer.testclient.scheduler.StaticTestClientReadings;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;

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
    @RequestMapping("/endpoints")
    public List<String> getEndpoints() {
    	List<String> endpoints = new ArrayList<String>();
        KubernetesClient client = new DefaultKubernetesClient();
        String namespace = client.getNamespace();
        List<Service> services = client.services().inNamespace(namespace).list().getItems();
        for(Service service: services) {
        	endpoints.add(service.getSpec().getClusterIP());
        }
        return endpoints;
    }
}
