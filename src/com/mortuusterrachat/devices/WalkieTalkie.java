/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see <https://github.com/kadeska/MT_Communication/blob/master/LICENSE>.
 */
package com.mortuusterrachat.devices;

import static com.mortuusterrachat.utils.StringUtilities.color;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.mortuusterrachat.MortuusTerraChat;
import com.mortuusterrachat.objects.Frequency;
import com.mortuusterrachat.objects.FrequencyRange;

public class WalkieTalkie {

	private Frequency currentFrequency;
	private FrequencyRange frequencyRange;
	private MortuusTerraChat main;

	public WalkieTalkie(MortuusTerraChat main, Frequency currentFrequency) {
		this.main = main;
		this.currentFrequency = currentFrequency;
		frequencyRange = main.getFrequencyManager().getPublicRadioCompany();
	}

	public void increaseFrequency(Player p) {
		currentFrequency = frequencyRange.getNextFrequency(currentFrequency);
		main.getPlayerManager().getPlayer(p).setCurrentWalkieTalkieFrequency(currentFrequency);
		p.sendMessage(MortuusTerraChat.WALKIETALKIE_PREFIX
				+ color("&eCh. " + currentFrequency.getChannel() + " (" + currentFrequency.getFrequency() + " MHz)"));
	}

	public void decreaseFrequency(Player p) {
		currentFrequency = frequencyRange.getPreviousFrequency(currentFrequency);
		
		main.getPlayerManager().getPlayer(p).setCurrentWalkieTalkieFrequency(currentFrequency);
		p.sendMessage(MortuusTerraChat.WALKIETALKIE_PREFIX
				+ color("&eCh. " + currentFrequency.getChannel() + " (" + currentFrequency.getFrequency() + " MHz)"));
	}

	public void walkieTalkieRings(Player p, String message, Frequency frequency) {
		p.playSound(p.getLocation(), Sound.BLOCK_NOTE_CHIME, 5, 1);
		p.sendMessage(MortuusTerraChat.WALKIETALKIE_PREFIX
				+ color("&8&l[&eCh. " + frequency.getChannel() + "&8&l]&7 " + message));
	}
}
