package dev.binarycodee.sunsethub.modules.home;

import dev.binarycodee.sunsethub.Core;
import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getConfigText("messages.player-only"));
            return true;
        }

        Player player = (Player) sender;
        Location spawn = (Location) Core.getFileManager().getConfig().get("modules.spawn.location");

        if (spawn == null) {
            player.sendMessage(ChatUtils.getConfigText("modules.spawn.messages.not-found"));
            return true;
        }

        player.teleport(spawn);
        return true;
    }
}
