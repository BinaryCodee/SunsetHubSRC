package dev.binarycodee.sunsethub.modules.parkour;

import dev.binarycodee.sunsethub.Core;
import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class ParkourCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatUtils.getConfigText("modules.parkour.help"));
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getConfigText("messages.player-only"));
            return true;
        }

        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("set")) {
            if (!player.hasPermission(Core.getFileManager().getConfig().getString("modules.parkour.permission"))) {
                player.sendMessage(ChatUtils.getConfigText("messages.no-permission"));
                return true;
            }

            Location location = player.getLocation();
            FileConfiguration config = Core.getFileManager().getConfig();
            File file = new File(Core.getInstance().getDataFolder(), "config.yml");

            config.set("modules.parkour.location", location.getWorld().getName() + "," +
                    location.getBlockX() + "," +
                    location.getBlockY() + "," +
                    location.getBlockZ() + "," +
                    location.getYaw() + "," +
                    location.getPitch());
            Core.getFileManager().saveFile(config, file);

            player.sendMessage(ChatUtils.getConfigText("modules.parkour.setted"));
            return true;
        }

        sender.sendMessage(ChatUtils.getConfigText("modules.parkour.usage"));
        return true;
    }
}
