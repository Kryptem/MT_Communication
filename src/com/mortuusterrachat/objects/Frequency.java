/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see <https://github.com/kadeska/MT_Communication/blob/master/LICENSE>.
 */
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
