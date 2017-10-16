package com.mortuusterrachat.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterrachat.MortuusTerraChat;
import com.mortuusterrachat.objects.Frequency;
import com.mortuusterrachat.objects.PlayerObject;
import com.mortuusterrachat.utils.StringUtilities;
import com.mortuusterrachat.utils.files.FileType;
import com.mortuusterrachat.utils.files.PluginFile;

public class PlayerManager {

	private MortuusTerraChat main = JavaPlugin.getPlugin(MortuusTerraChat.class);

	private PluginFile file;
	private Map<UUID, PlayerObject> players = new HashMap<>();

	public Map<UUID, PlayerObject> getPlayers() {
		return players;
	}

	public void addPlayer(Player p) {
		if (!players.containsKey(p.getUniqueId()))
			players.put(p.getUniqueId(), new PlayerObject(p.getUniqueId(), p.getName(), main.getFrequencyManager().getPublicRadioCompany().getFrequencyRange().get(0)));
	}

	public void removePlayer(Player p) {
		if (players.containsKey(p.getUniqueId()))
			players.remove(p.getUniqueId());
	}

	public PlayerObject getPlayer(Player p) {
		if (players.containsKey(p.getUniqueId()))
			return players.get(p.getUniqueId());
		return null;
	}

	public void addPhoneContact(Player player, String contactName) {
		PlayerObject pObject = getPlayer(player);
		if (pObject == null)
			return;

		if (!pObject.getContacts().contains(contactName)) {
			pObject.getContacts().add(contactName);
			player.sendMessage(
					MortuusTerraChat.MTC_PREFIX + StringUtilities.color("&7Contact &6" + contactName + "&7 added!"));
		} else {
			player.sendMessage(MortuusTerraChat.MTC_PREFIX + StringUtilities.color("&7Contact already exists."));
		}
	}

	public void removePhoneContact(Player player, String contactName) {
		PlayerObject pObject = getPlayer(player);
		if (pObject == null)
			return;

		if (pObject.getContacts().contains(contactName)) {
			pObject.getContacts().remove(contactName);
			player.sendMessage(
					MortuusTerraChat.MTC_PREFIX + StringUtilities.color("&7Contact &6" + contactName + "&7 removed!"));
		} else {
			player.sendMessage(MortuusTerraChat.MTC_PREFIX + StringUtilities.color("&7Contact does not exist."));
		}
	}

	public void clearContacts(Player player) {
		PlayerObject pObject = getPlayer(player);
		if (pObject == null)
			return;

		pObject.getContacts().clear();
		player.sendMessage(MortuusTerraChat.MTC_PREFIX + StringUtilities.color("&7Contacts cleared!"));
	}
	
	public void listContacts(Player player) {
		PlayerObject pObject = getPlayer(player);
		if (pObject == null)
			return;
		
		player.sendMessage(MortuusTerraChat.MTC_PREFIX + StringUtilities.color("&6Contacts:"));
		for (String contact : pObject.getContacts()) {
			player.sendMessage(StringUtilities.color("&b+ " + contact));
		}
	}

	public void savePlayersToDisk() {
		YamlConfiguration config = file.returnYaml();

		for (PlayerObject p : players.values()) {
			String uuid = p.getUuid().toString();
			config.set(uuid + ".current-cellphone-recipient", p.getCurrentCellPhoneRecipient());
//			config.set(uuid + ".walkie-talkie.current-channel", p.getCurrentWalkieTalkieFrequency().getChannel());
//			config.set(uuid + ".walkie-talkie.current-frequency", p.getCurrentWalkieTalkieFrequency().getFrequency());
			config.set(uuid + ".notification-sound", p.receiveNotificationSound());
			config.set(uuid + ".contacts", p.getContacts());
		}

		players.clear();
		file.save(config);
	}

	public void loadPlayersFromDisk() {
		file = new PluginFile("players", FileType.YAML);
		YamlConfiguration config = file.returnYaml();

		for (String key : config.getConfigurationSection("").getKeys(false)) {
			UUID uuid = UUID.fromString(key);
			Frequency freq = main.getFrequencyManager().getPublicRadioCompany().getFrequencyRange().get(0);
			PlayerObject pObject = new PlayerObject(uuid, config.getString(key + ".current-cellphone-recipient"), freq);
			
			pObject.setReceiveNotificationSound(config.getBoolean(key +".notification-sound"));
			pObject.setContacts(config.getStringList(key + ".contacts"));
			players.put(uuid, pObject);
			
		}

	}
}
