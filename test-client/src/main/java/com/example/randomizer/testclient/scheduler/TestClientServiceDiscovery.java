package com.example.randomizer.testclient.scheduler;

import java.util.ArrayList;
import java.util.List;

import com.example.randomizer.model.ClientServiceReading;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class TestClientServiceDiscovery {
	private static final String SERVICE_LABEL = "type=randomizer-service";

	public List<ClientServiceReading> getClientServiceReadings() {
		KubernetesClient client = null;
    	List<ClientServiceReading> endpoints = new ArrayList<ClientServiceReading>();
		try {
	        client = new DefaultKubernetesClient();
	        String namespace = client.getNamespace();
	        List<Service> services = client.services().inNamespace(namespace).withLabel(SERVICE_LABEL).list().getItems();
	        for(Service service: services) {
	        	String url =
	        			"http://" +
	        			service.getMetadata().getName() + "." +
	        			service.getMetadata().getNamespace() + ".svc" +
	        			":8080/randomizer/random";
	        	endpoints.add(new ClientServiceReading(url));
	        }
		} finally {
			client.close();
		}
        return endpoints;
    }

}
