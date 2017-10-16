package com.mortuusterrachat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mortuusterrachat.MortuusTerraChat;
import com.mortuusterrachat.objects.PlayerObject;
import com.mortuusterrachat.utils.StringUtilities;

public class MortuusTerraChatCommands implements CommandExecutor {

	private MortuusTerraChat main;

	public MortuusTerraChatCommands(MortuusTerraChat main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mtr")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage("You may only use the 'mtr-commands' from ingame.");
				return true;
			}

			/*
			 * Commands: mtr clear contact mtr add contact <name> mtr del
			 * contact <name>
			 */

			Player p = (Player) sender;
			if (args.length == 0)
				return true;

			if (args[1].equalsIgnoreCase("contact")) {
				if (args.length == 3) {

					String contactName = args[2];

					if (args[0].equalsIgnoreCase("add")) {
						main.getPlayerManager().addPhoneContact(p, contactName);
					}

					if (args[0].equalsIgnoreCase("del")) {
						main.getPlayerManager().removePhoneContact(p, contactName);
					}

				}

				if (args.length == 2) {

					if (args[0].equalsIgnoreCase("clear")) {
						main.getPlayerManager().clearContacts(p);
					}

					if (args[0].equalsIgnoreCase("list")) {
						main.getPlayerManager().listContacts(p);
					}
				}
			}
			
			if (args[0].equalsIgnoreCase("toggle")) {
				if (args.length == 2) {
					
					if (args[1].equalsIgnoreCase("sound")) {
						PlayerObject pObject = main.getPlayerManager().getPlayer(p);
						
						if (pObject.receiveNotificationSound()) {
							pObject.setReceiveNotificationSound(false);
							p.sendMessage(MortuusTerraChat.MTC_PREFIX + StringUtilities.color("&7Notification sound turned off."));
						} else {
							pObject.setReceiveNotificationSound(true);
							p.sendMessage(MortuusTerraChat.MTC_PREFIX + StringUtilities.color("&7Notification sound turned on."));
						}
					}
				}
			}

		}
		return true;
	}

}
