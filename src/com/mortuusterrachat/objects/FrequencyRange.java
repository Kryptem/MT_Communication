/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see <https://github.com/kadeska/MT_Communication/blob/master/LICENSE>.
 */
package com.mortuusterrachat.objects;

import java.util.ArrayList;
import java.util.List;

public class FrequencyRange {
	
	//private MortuusTerraChat main = JavaPlugin.getPlugin(MortuusTerraChat.class);

	private String frequencyName;
	private List<Frequency> frequencies;

	public FrequencyRange() {
		this.frequencies = new ArrayList<>();
	}

	public Frequency getNextFrequency(Frequency currentFrequency) {

		Frequency nextFrequency = new Frequency(-1, "");

		int frequencyIndex = 0;
		int nextFrequencyIndex = 0;

		for (Frequency frequency : frequencies) {

			if (frequency.equals(currentFrequency)) {

				if ((frequencyIndex + 1) == frequencies.size()) {

					nextFrequencyIndex = 0;

				} else {

					nextFrequencyIndex = frequencyIndex + 1;

				}
				break;
			}
			frequencyIndex++;
		}

		nextFrequency = frequencies.get(nextFrequencyIndex);
		
		return nextFrequency;

	}

	public Frequency getPreviousFrequency(Frequency currentFrequency) {

		Frequency nextFrequency = new Frequency(-1, "");

		int frequencyIndex = 0;
		int nextFrequencyIndex = 0;

		for (Frequency frequency : frequencies) {

			if (frequency.equals(currentFrequency)) {

				if (frequencyIndex == 0) {

					nextFrequencyIndex = frequencies.size() - 1;

				} else {

					nextFrequencyIndex = frequencyIndex - 1;

				}
				break;
			}
			frequencyIndex++;
		}

		nextFrequency = frequencies.get(nextFrequencyIndex);
		return nextFrequency;
	}

	public List<Frequency> getFrequencyRange() {
		return frequencies;
	}

	public String getFrequencyName() {
		return frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}
}
