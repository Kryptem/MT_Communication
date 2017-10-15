package com.mortuusterrachat.managers;

import com.mortuusterrachat.objects.Frequency;
import com.mortuusterrachat.objects.FrequencyRange;

public class FrequencyManager {
	
	private FrequencyRange publicRadioCompany;
	
	public FrequencyManager() {
		setFrequencyRanges();
	}

	private void setFrequencyRanges() {
		publicRadioCompany = new FrequencyRange();
		
		publicRadioCompany.setFrequencyName("Public Radio Company (PRC)");
		publicRadioCompany.getFrequencyRange().add(new Frequency(1, "101.1"));
		publicRadioCompany.getFrequencyRange().add(new Frequency(2, "101.5"));
		publicRadioCompany.getFrequencyRange().add(new Frequency(3, "101.7"));
		publicRadioCompany.getFrequencyRange().add(new Frequency(4, "103.1"));
		publicRadioCompany.getFrequencyRange().add(new Frequency(5, "105.1"));
		
	}
	
	public FrequencyRange getPublicRadioCompany() {
		return publicRadioCompany;
	}

}
