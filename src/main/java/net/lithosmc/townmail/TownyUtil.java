package net.lithosmc.townmail;

import com.palmergames.bukkit.towny.object.Translation;
import com.palmergames.bukkit.util.ChatTools;
import org.bukkit.ChatColor;

// Methods that have defaults
// in case Towny refactors the existing methods.
public class TownyUtil {
    public static String trString(String key, String def, Object... objs) {
        try {
            return Translation.of(key, objs);
        } catch (Throwable ex) {
            ex.printStackTrace();
            return def;
        }
    }

    public static String formatCmdTitle(String cmd) {
        try {
            return ChatTools.formatTitle(cmd);
        } catch (Throwable ex) {
            ex.printStackTrace();
            return ChatColor.AQUA + cmd + ":";
        }
    }

    public static String formatCmd(String cmd, String sub, String help) {
        try {
            return ChatTools.formatCommand(cmd, sub, help);
        } catch (Throwable ex) {
            ex.printStackTrace();
            return ChatColor.GOLD + cmd + " " + sub + ChatColor.WHITE + " : " + ChatColor.AQUA + help;
        }
    }
}
