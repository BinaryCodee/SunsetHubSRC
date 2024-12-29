package dev.binarycodee.sunsethub.modules.home;

import dev.binarycodee.sunsethub.Core;
import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class SetSpawnCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getConfigText("messages.player-only"));
            return true;
        }

        Player player = (Player) sender;

        if (!sender.hasPermission(Core.getFileManager().getConfig().getString("modules.spawn.permission"))) {
            sender.sendMessage(ChatUtils.getConfigText("messages.no-permission"));
            return true;
        }

        Location location = player.getLocation();
        FileConfiguration config = Core.getFileManager().getConfig();
        File file = new File(Core.getInstance().getDataFolder(), "config.yml");

        config.set("modules.spawn.location", location);
        Core.getFileManager().saveFile(config, file);

        player.sendMessage(ChatUtils.getConfigText("modules.spawn.messages.success"));
        return true;
    }
}
