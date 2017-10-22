/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see <https://github.com/kadeska/MT_Communication/blob/master/LICENSE>.
 */
package com.mortuusterrachat.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import com.mortuusterrachat.objects.TextMessage;
import com.mortuusterrachat.utils.files.FileType;
import com.mortuusterrachat.utils.files.PluginFile;

public class TextMessageManager {

	private PluginFile file;
	private List<TextMessage> textMessages = new ArrayList<>();

	public List<TextMessage> getMessagesForName(String name) {
		List<TextMessage> messages = new ArrayList<>();

		for (TextMessage tm : textMessages) {
			if (tm.getRecipient().equalsIgnoreCase(name))
				messages.add(tm);
		}
		return messages;
	}

	public void addTextMessage(String sender, String recipient, String message) {
		textMessages.add(new TextMessage(sender, recipient, message));
	}

	public void removeTextMessage(String sender, String recipient, String message) {
		TextMessage toDelete = new TextMessage(sender, recipient, message);

		for (TextMessage tm : textMessages) {
			if (tm.getRecipient().equalsIgnoreCase(recipient))
				toDelete = tm;
		}

		textMessages.remove(toDelete);
	}

	public void markMessageAsRead(TextMessage message) {
		for (TextMessage tm : getMessagesForName(message.getRecipient())) {
			if (tm.equals(message))
				message.markAsRead();
		}
	}

	public List<TextMessage> getTextMessages() {
		return textMessages;
	}

	public void saveMessagessToDisk() {
		YamlConfiguration config = file.returnYaml();
		List<String> messageStrings = new ArrayList<>();
		
		for (TextMessage m : textMessages) {
			messageStrings.add(m.getConfigFormat());
		}
		
		config.set("messages", messageStrings);
		
		file.save(config);
	}

	public void loadMessagesFromDisk() {
		file = new PluginFile("textMessages", FileType.YAML);
		YamlConfiguration config = file.returnYaml();
		
		for (String s : config.getStringList("messages")) {
			String[] split = s.split("-");
			
			textMessages.add(new TextMessage(split[0], split[1], split[2]));
		}

	}

}
