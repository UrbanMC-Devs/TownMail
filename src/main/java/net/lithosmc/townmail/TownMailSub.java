package net.lithosmc.townmail;

import com.earth2me.essentials.Essentials;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TownMailSub implements CommandExecutor {

    private final static String PERM = "towny.command.town.mailall";

    private final Essentials essentials;

    public TownMailSub(Essentials essentials) {
        this.essentials = essentials;
    }

    // Args includes the sub-command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(PERM)) {
            sender.sendMessage(TownyUtil.trString(
                    "msg_err_command_disable",
                    ChatColor.RED + "You don't have permission to use this command!"
            ));

            return true;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED +"You must be a player to execute this command!");
            return true;
        }

        if (args.length < 2) {
            player.sendMessage(TownyUtil.formatCmdTitle("/town mailall"));
            player.sendMessage(TownyUtil.formatCmd("/town mailall", "[message]", "Send mail to all town residents!"));
            return true;
        }

        var resident = TownyAPI.getInstance().getResident(player.getUniqueId());

        if (resident == null) {
            player.sendMessage(ChatColor.RED + "You must be a resident!");
            return true;
        }

        if (!resident.hasTown()) {
            player.sendMessage(ChatColor.RED + "You must be in a town to use this command!");
            return true;
        }

        var town = TownyAPI.getInstance().getResidentTownOrNull(resident);

        var message = Arrays.stream(args).skip(1).collect(Collectors.joining(" "));
        var mail = ChatColor.GOLD + "[" + ChatColor.WHITE + player.getName() + ChatColor.GOLD + "] " +
                ChatColor.WHITE + message;

        for (Resident townResident : town.getResidents()) {
            if (townResident.isNPC())
                continue;

            var essRes = essentials.getUser(townResident.getUUID());
            essRes.addMail(mail);
        }

        player.sendMessage(ChatColor.GREEN + "Mail sent to all town residents!");

        return false;
    }
}
