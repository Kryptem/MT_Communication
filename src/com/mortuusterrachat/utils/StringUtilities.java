/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see <https://github.com/kadeska/MT_Communication/blob/master/LICENSE>.
 */
package com.mortuusterrachat.utils;

import org.bukkit.ChatColor;

public class StringUtilities {

	public static String[] helpPage(String command, String... args) {
		String title = color("&8---------- &6" + command + " Helppage &8----------");
		if (args.length % 2 != 0)
			return new String[] { title };

		String[] out = new String[args.length / 2 + 1];
		out[0] = title;
		for (int i = 0; i < args.length / 2; i++) {
			out[i + 1] = color("&e/" + command + " &7" + args[2 * i] + "&e  -  " + args[2 * i + 1]);
		}
		return out;
	}
	
	public static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
}
