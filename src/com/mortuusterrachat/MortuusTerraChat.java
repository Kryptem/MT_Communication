package com.mortuusterrachat;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterra.MortuusTerraCore;
import com.mortuusterrachat.commands.CellPhoneCommand;
import com.mortuusterrachat.listeners.CellularPhoneListener;
import com.mortuusterrachat.listeners.PlayerChatListener;
import com.mortuusterrachat.listeners.PlayerListener;
import com.mortuusterrachat.managers.MessageScrambler;
import com.mortuusterrachat.managers.PlayerManager;
import com.mortuusterrachat.managers.TextMessageManager;
import com.mortuusterrachat.utils.StringUtilities;
import com.mortuusterrachat.utils.files.FileManager;

public class MortuusTerraChat extends JavaPlugin {

  private MortuusTerraCore core;

  public static final String MTC_PREFIX = StringUtilities.color("&7&l[&b&lMTChat&7&l] ");
  public static final String PREFIX = StringUtilities.color("&7&l[&b&lMTRadio&7&l] ");
  public static final String CELLPHONE_PREFIX = StringUtilities.color("&7&l[&b&l&6Cellular Phone&7&l] ");

  private MessageScrambler messageScrambler;
  private PlayerManager playerManager;
  private TextMessageManager textMessageManager;
  private FileManager fileManager;

  private PlayerChatListener playerChatListener;

  public void onEnable() {
    core = JavaPlugin.getPlugin(MortuusTerraCore.class);

    fileManager = new FileManager();
    playerManager = new PlayerManager();
    textMessageManager = new TextMessageManager();
    messageScrambler = new MessageScrambler();
    playerChatListener = new PlayerChatListener();

    getFileManager().loadFiles();

    registerCommands();
    registerListeners();
  }

  public void onDisable() {
    getFileManager().saveFiles();
  }

  public void registerListeners() {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(this.playerChatListener, this);
    pm.registerEvents(new CellularPhoneListener(this), this);
    pm.registerEvents(new PlayerListener(this), this);
    // pm.registerEvents(new PlayerChatListener(), this);
  }

  public void registerCommands() {
    getCommand("mtr").setExecutor(new CellPhoneCommand(this));
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
