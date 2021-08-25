package net.lithosmc.townmail;

import com.earth2me.essentials.Essentials;
import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyCommandAddonAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TownMail extends JavaPlugin {

    // Check if Towny is properly enabled
    private boolean isTownyEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("Towny")
                && !Towny.getPlugin().isError();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        var essentials = getEssentials();
        if (essentials == null) {
            getLogger().severe("Essentials cannot be loaded properly. Disabling...");
            setEnabled(false);
            return;
        }

        if (!isTownyEnabled()) {
            getLogger().severe("Towny was not loaded correctly. Disabling...");
            setEnabled(false);
            return;
        }

        registerCmdAddon(essentials);
    }

    private Essentials getEssentials() {
        if (Bukkit.getPluginManager().isPluginEnabled("Essentials")) {
            var essentialsPlugin = Bukkit.getPluginManager().getPlugin("Essentials");
            if (essentialsPlugin instanceof Essentials essentials) {
                return essentials;
            }
            else {
                var error = String.format(
                        "Essentials plugin cannot be cast properly! Expected class '%s' but got class '%s'",
                               Essentials.class.getName(),
                                essentialsPlugin.getClass().getName()
                        );
                getLogger().severe(error);
            }
        }
        else {
            getLogger().severe("The essentials plugin is not enabled!");
        }

        return null;
    }

    private void registerCmdAddon(Essentials essentials) {
        var addonCmd = new TownMailSub(essentials);
        TownyCommandAddonAPI.addSubCommand(TownyCommandAddonAPI.CommandType.TOWN, "mailall", addonCmd);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (isTownyEnabled()) {
            // Unregister the sub-command
            TownyCommandAddonAPI.removeSubCommand(TownyCommandAddonAPI.CommandType.TOWN, "mailall");
        }
    }
}
