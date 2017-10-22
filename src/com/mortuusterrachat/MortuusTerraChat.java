/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see <https://github.com/kadeska/MT_Communication/blob/master/LICENSE>.
 */
package com.mortuusterrachat;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterra.MortuusTerraCore;
import com.mortuusterrachat.commands.MortuusTerraChatCommands;
import com.mortuusterrachat.listeners.CellularPhoneListener;
import com.mortuusterrachat.listeners.PlayerChatListener;
import com.mortuusterrachat.listeners.PlayerListener;
import com.mortuusterrachat.listeners.WalkieTalkieListener;
import com.mortuusterrachat.managers.FrequencyManager;
import com.mortuusterrachat.managers.MessageScrambler;
import com.mortuusterrachat.managers.PlayerManager;
import com.mortuusterrachat.managers.TextMessageManager;
import com.mortuusterrachat.utils.StringUtilities;
import com.mortuusterrachat.utils.files.FileManager;

public class MortuusTerraChat extends JavaPlugin {

	private static MortuusTerraCore core;

	public static final String MTC_PREFIX = StringUtilities.color("&7&l[&b&lMTChat&7&l] ");
	public static final String CELLPHONE_PREFIX = StringUtilities.color("&7&l[&b&l&6Cellular Phone&7&l] ");
	public static final String WALKIETALKIE_PREFIX = StringUtilities.color("&7&l[&b&l&6Walkie Talkie&7&l] ");

	private MessageScrambler messageScrambler;
	private PlayerManager playerManager;
	private TextMessageManager textMessageManager;
	private FileManager fileManager;
	private FrequencyManager frequencyManager;

	public void onEnable() {
		// Console sender
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Starting Mortuus Terra chat.");

		core = JavaPlugin.getPlugin(MortuusTerraCore.class);

		frequencyManager = new FrequencyManager();
		fileManager = new FileManager();
		playerManager = new PlayerManager();
		textMessageManager = new TextMessageManager();
		messageScrambler = new MessageScrambler();

		getFileManager().loadFiles();

		registerCommands();
		registerListeners();
		
		// Console sender
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Mortuus Terra chat ready.");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
	}

	public void onDisable() {
		// Console sender
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Stoping Mortuus Terra chat.");

		//save files
		getFileManager().saveFiles();

		// Console sender
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Mortuus Terra chat stopped.");
	}

	public void registerListeners() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new CellularPhoneListener(this), this);
		pm.registerEvents(new PlayerListener(this), this);
		pm.registerEvents(new PlayerChatListener(), this);
		pm.registerEvents(new WalkieTalkieListener(this), this);
	}

	public void registerCommands() {
		getCommand("mtr").setExecutor(new MortuusTerraChatCommands(this));
	}

	public FrequencyManager getFrequencyManager() {
		return frequencyManager;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public TextMessageManager getTextMessageManager() {
		return textMessageManager;
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public MessageScrambler getMessageScrambler() {
		return messageScrambler;
	}

	public MortuusTerraCore getCore() {
		return core;
	}
}
