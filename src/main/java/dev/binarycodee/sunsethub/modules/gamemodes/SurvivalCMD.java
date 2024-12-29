package dev.binarycodee.sunsethub.modules.gamemodes;

import dev.binarycodee.sunsethub.Core;
import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SurvivalCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getConfigText("modules.gamemodes.messages.player-only"));
            return true;
        }

        if (!sender.hasPermission(Core.getFileManager().getConfig().getString("modules.gamemodes.permission"))) {
            sender.sendMessage(ChatUtils.getConfigText("modules.gamemodes.messages.no-permission"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatUtils.getConfigText("modules.gamemodes.messages.gamemode-changed")
                    .replace("{gamemode}", "Survival"));
            player.setGameMode(GameMode.SURVIVAL);
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(ChatUtils.getConfigText("modules.gamemodes.messages.player-not-found"));
                return true;
            }

            target.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(ChatUtils.getConfigText("modules.gamemodes.messages.gamemode-changed-for-player")
                    .replace("{gamemode}", "Survival")
                    .replace("{player}", target.getName()));
            target.sendMessage(ChatUtils.getConfigText("modules.gamemodes.messages.gamemode-changed")
                    .replace("{gamemode}", "Survival"));
        }
        return true;
    }
}
