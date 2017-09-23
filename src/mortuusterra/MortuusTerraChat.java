package mortuusterra;

import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.listeners.PlayerChat;
import mortuusterra.managers.MessageScrambler;

public class MortuusTerraChat extends JavaPlugin {
	
	private final MortuusTerraCore core = JavaPlugin.getPlugin(MortuusTerraCore.class);
	
	private MessageScrambler messageScrambler;
	
	private PlayerChat playerChatListener;
	
	public void onEnable() {
		
		messageScrambler = new MessageScrambler();
		playerChatListener = new PlayerChat();
		getServer().getPluginManager().registerEvents(this.playerChatListener, this);
		
	}
	public void onDisable() {
		
	}
	
	public MessageScrambler getMessageScrambler() {
		return messageScrambler;
	}
	public MortuusTerraCore getCore() {
		return core;
	}
}
