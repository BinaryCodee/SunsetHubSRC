package dev.binarycodee.sunsethub.modules.teleports;

import dev.binarycodee.sunsethub.Core;
import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class TpHereCMD implements CommandExecutor {

    private final FileConfiguration config;

    public TpHereCMD() {
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

        if (args.length != 1) {
            player.sendMessage(ChatUtils.getConfigText("modules.teleports.messages.usage-tphere"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatUtils.getConfigText("modules.teleports.messages.player-not-found").replace("{player}", args[0]));
            return true;
        }

        target.teleport(player);
        player.sendMessage(ChatUtils.getConfigText("modules.teleports.messages.tphere-success").replace("{player}", target.getName()));
        return true;
    }
}
