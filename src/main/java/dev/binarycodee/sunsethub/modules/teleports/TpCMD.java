package dev.binarycodee.sunsethub.modules.teleports;

import dev.binarycodee.sunsethub.Core;
import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class TpCMD implements CommandExecutor {

    private final FileConfiguration config;

    public TpCMD() {
        this.config = Core.getFileManager().getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getConfigText("modules.teleports.messages.player-only"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(config.getString("modules.teleports.permission"))) {
            player.sendMessage(ChatUtils.getConfigText("modules.teleports.messages.no-permission"));
            return true;
        }

        if (args.length != 2) {
            player.sendMessage(ChatUtils.getConfigText("modules.teleports.messages.usage-tp"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            player.sendMessage(ChatUtils.getConfigText("modules.teleports.messages.player-not-found").replace("{player}", args[1]));
            return true;
        }

        Player destination = Bukkit.getPlayer(args[0]);
        if (destination == null) {
            player.sendMessage(ChatUtils.getConfigText("modules.teleports.messages.player-not-found").replace("{player}", args[0]));
            return true;
        }

        destination.teleport(target);
        player.sendMessage(ChatUtils.getConfigText("modules.teleports.messages.tp-success").replace("{player1}", destination.getName()).replace("{player2}", target.getName()));
        return true;
    }
}
