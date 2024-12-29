package dev.binarycodee.sunsethub.modules.fly;

import dev.binarycodee.sunsethub.Core;
import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FlyCMD implements CommandExecutor {

    private final FileConfiguration config;

    public FlyCMD() {
        this.config = Core.getFileManager().getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getConfigText("modules.fly.messages.player-only"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(config.getString("modules.fly.permission"))) {
            player.sendMessage(ChatUtils.getConfigText("modules.fly.messages.no-permission"));
            return true;
        }

        boolean fly = !player.getAllowFlight();
        player.setAllowFlight(fly);
        player.setFlying(fly);

        if (fly) {
            player.sendMessage(ChatUtils.getConfigText("modules.fly.messages.fly-enabled"));
        } else {
            player.sendMessage(ChatUtils.getConfigText("modules.fly.messages.fly-disabled"));
        }

        return true;
    }
}
