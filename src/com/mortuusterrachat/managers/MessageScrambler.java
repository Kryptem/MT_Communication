/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see <https://github.com/kadeska/MT_Communication/blob/master/LICENSE>.
 */
package com.mortuusterrachat.managers;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterrachat.MortuusTerraChat;

public class MessageScrambler {

  private MortuusTerraChat main = JavaPlugin.getPlugin(MortuusTerraChat.class);

  final int MIN_DISTANCE = 0;
  final int MAX_DISTANCE = 100;
  final int EIGHTY_PERCENT = MAX_DISTANCE * 80 / 100;
  final int SIXTY_PERCENT = MAX_DISTANCE * 60 / 100;
  final int FORTY_PERCENT = MAX_DISTANCE * 40 / 100;
  final int TWENTY_PERCENT = MAX_DISTANCE * 20 / 100;

  final Random ran = new Random();

  public String scramble(String message, Player from, Player to) {

    int distance = (int) from.getLocation().distance(to.getLocation());

    // Too far away, send nothing
    if (distance >= MAX_DISTANCE) {
      main.getLogger().info("Distance over 100");
      return null;
    }

    // Distance between 99 - 80
    if (MAX_DISTANCE > distance && distance >= EIGHTY_PERCENT) {
      main.getLogger().info("Distance between 99 - 80");

      return obfuscate(message, 8);
    }

    // Distance between 79 - 60
    if (EIGHTY_PERCENT > distance && distance >= SIXTY_PERCENT) {
      main.getLogger().info("Distance between 79 - 60");

      return obfuscate(message, 6);
    }

    // Distance between 59 - 40
    if (SIXTY_PERCENT > distance && distance >= FORTY_PERCENT) {
      main.getLogger().info("Distance between 59 - 40");

      return obfuscate(message, 4);
    }

    // Distance between 39 - 20
    if (FORTY_PERCENT > distance && distance >= TWENTY_PERCENT) {
      main.getLogger().info("Distance between 39 - 20");

      return obfuscate(message, 2);
    }

    // Distance less than 20. Message clear
    if (distance < TWENTY_PERCENT) {
      main.getLogger().info("Distance less 20");

      return message;
    }

    main.getLogger().info("nothing");

    return message;
  }

  private String obfuscate(String toObfuscate, int amount) {
    // Get rid of the blank spaces
    String[] temp = toObfuscate.split(" ");
    String noBlanks = "";
    
    for (String s : temp)
      noBlanks += s;
    
    if (amount > noBlanks.length())
      amount = noBlanks.length() - 1;

    int index;
    char[] charsToReplace = new char[amount];

    for (int i = 0; i < amount; i++) {
      index = ran.nextInt(noBlanks.length());
      charsToReplace[i] = noBlanks.charAt(index);
    }
    
    String obfuscated = toObfuscate;

    final String[] replacements = { "&k%&r", "&k%&r", "&l!&r", "&m#&r", "&n%&r", "&o%&r", "$", "@", "!", "?", "ö", "*", "ä", "ü"};
    
    for (Character c : charsToReplace) {
      main.getLogger().info("Selected char " + c);
      obfuscated = obfuscated.replace(String.valueOf(c), replacements[ran.nextInt(replacements.length)]);
    }

    return obfuscated;

  }
}
