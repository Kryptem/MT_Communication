/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see <https://github.com/kadeska/MT_Communication/blob/master/LICENSE>.
 */
package com.mortuusterrachat.utils;

import org.bukkit.ChatColor;

public class StringUtilities {

	public static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
}
