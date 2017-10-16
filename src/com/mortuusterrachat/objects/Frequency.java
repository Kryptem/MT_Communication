package com.mortuusterrachat.objects;

public class Frequency {
	
	private String frequency;
	private int channel;
	
	public Frequency(int channel, String frequency) {
		this.frequency = frequency;
		this.channel = channel;
	}
	
	public int getChannel() {
		return channel;
	}
	
	public String getFrequency() {
		return frequency;
	}
	
	public void setChannel(int channel) {
		this.channel = channel;
	}
	
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	

}
