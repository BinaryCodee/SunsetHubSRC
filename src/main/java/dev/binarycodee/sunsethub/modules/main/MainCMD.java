package dev.binarycodee.sunsethub.modules.main;

import dev.binarycodee.sunsethub.Core;
import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCMD implements CommandExecutor {

    private final Core plugin;

    public MainCMD(Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("sunsethub.reload")) {
                plugin.reloadConfig();
                sender.sendMessage(ChatUtils.getColoredText("&8[&6&lSUNSETHUB&8] &aConfigurazioni ricaricate!"));
            } else {
                sender.sendMessage(ChatUtils.getColoredText("&cNessun permesso!"));
            }
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatUtils.getColoredText("&fThis server is running &eSunsetHub v1.0-PRIVATE &fby &6BinaryCodee"));
            player.sendMessage(ChatUtils.getColoredText(""));
            player.sendMessage(ChatUtils.getColoredText("&cUsa: /sunsethub reload"));
        } else {
            sender.sendMessage(ChatUtils.getColoredText("&fThis server is running &eSunsetHub v1.0-PRIVATE &fby &6BinaryCodee"));
            sender.sendMessage(ChatUtils.getColoredText(""));
            sender.sendMessage(ChatUtils.getColoredText("&cUsa: /sunsethub reload"));
        }
        return true;
    }
}
