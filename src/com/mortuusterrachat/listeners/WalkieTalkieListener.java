/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see <https://github.com/kadeska/MT_Communication/blob/master/LICENSE>.
 */
package com.mortuusterrachat.listeners;

import static com.mortuusterrachat.utils.StringUtilities.color;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.mortuusterrachat.MortuusTerraChat;
import com.mortuusterrachat.devices.WalkieTalkie;
import com.mortuusterrachat.objects.Frequency;

public class WalkieTalkieListener implements Listener {

	private MortuusTerraChat main;

	public WalkieTalkieListener(MortuusTerraChat main) {
		this.main = main;
	}

	@EventHandler
	public void onWalkieTalkieChat(AsyncPlayerChatEvent e) {
		// Comparator as temporary walkie talkie
		if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.REDSTONE_COMPARATOR)
			return;

		e.setCancelled(true);

		Player sender = e.getPlayer();
		Frequency senderFreq = main.getPlayerManager().getPlayer(sender).getCurrentWalkieTalkieFrequency();

		// Search for players with walkie talkie in their inv.
		for (Player p : sender.getWorld().getPlayers()) {
			if (p.getInventory().contains(Material.REDSTONE_COMPARATOR)) {

				// Are walkie talkies on the same frequency.
				Player recipient = p;
				Frequency recipientFreq = main.getPlayerManager().getPlayer(recipient)
						.getCurrentWalkieTalkieFrequency();
				if (recipientFreq.getChannel() == senderFreq.getChannel()) {

					if (p != recipient) {
						if (main.getPlayerManager().getPlayer(recipient).receiveNotificationSound())
							p.playSound(recipient.getLocation(), Sound.BLOCK_NOTE_CHIME, 5, 1);
					}
					
					p.sendMessage(MortuusTerraChat.WALKIETALKIE_PREFIX + color("&8&l[&e" + sender.getName()
							+ "&8&l] &eCh." + senderFreq.getChannel() + ">&7 " + e.getMessage()));
				}
			}
		}
	}

	@EventHandler
	public void onWalkieTalkieInteract(PlayerInteractEvent e) {
		if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.REDSTONE_COMPARATOR)
			return;
		if (e.getHand() == EquipmentSlot.OFF_HAND)
			return;

		WalkieTalkie wt = new WalkieTalkie(main,
				main.getPlayerManager().getPlayer(e.getPlayer()).getCurrentWalkieTalkieFrequency());

		// Left click to tune frequency.
		if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (e.getPlayer().isSneaking()) {
				wt.decreaseFrequency(e.getPlayer());
			} else {
				wt.increaseFrequency(e.getPlayer());
			}
		}

	}

}
