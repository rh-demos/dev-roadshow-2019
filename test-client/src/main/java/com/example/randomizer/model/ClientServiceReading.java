package com.example.randomizer.model;

public class ClientServiceReading {
	private String url;
	private long latestReading;
	public ClientServiceReading(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getLatestReading() {
		return latestReading;
	}
	public void setLatestReading(long latestReading) {
		this.latestReading = latestReading;
	}
}
