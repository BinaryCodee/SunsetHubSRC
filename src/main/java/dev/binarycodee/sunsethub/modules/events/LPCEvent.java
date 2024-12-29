package dev.binarycodee.sunsethub.modules.events;

import dev.binarycodee.sunsethub.Core;
import me.clip.placeholderapi.PlaceholderAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LPCEvent implements Listener {


    private static final Pattern HEX_PATTERN;
    private final LuckPerms luckPerms;

    public LPCEvent() {
        this.luckPerms = Core.getInstance().getServer().getServicesManager().load(LuckPerms.class);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(final AsyncPlayerChatEvent event) {
        final String message = event.getMessage();
        final Player player = event.getPlayer();
        final CachedMetaData metaData = luckPerms.getPlayerAdapter((Class)Player.class).getMetaData((Object)player);
        final String group = metaData.getPrimaryGroup();
        String format = Core.getFileManager().getConfig().getString((Core.getFileManager().getConfig().getString("lpc.group-formats." + group) != null) ? ("lpc.group-formats." + group) : "lpc.chat-format").replace("{prefix}", (metaData.getPrefix() != null) ? metaData.getPrefix() : "").replace("{suffix}", (metaData.getSuffix() != null) ? metaData.getSuffix() : "").replace("{prefixes}", (CharSequence)metaData.getPrefixes().keySet().stream().map(key -> (String)metaData.getPrefixes().get(key)).collect(Collectors.joining())).replace("{suffixes}", (CharSequence)metaData.getSuffixes().keySet().stream().map(key -> (String)metaData.getSuffixes().get(key)).collect(Collectors.joining())).replace("{world}", player.getWorld().getName()).replace("{name}", player.getName()).replace("{displayname}", player.getDisplayName()).replace("{username-color}", (metaData.getMetaValue("username-color") != null) ? metaData.getMetaValue("username-color") : "").replace("{message-color}", (metaData.getMetaValue("message-color") != null) ? metaData.getMetaValue("message-color") : "");
        format = this.colorize(this.translateHexColorCodes(Core.getInstance().getServer().getPluginManager().isPluginEnabled("PlaceholderAPI") ? PlaceholderAPI.setPlaceholders(player, format) : format));
        event.setFormat(format.replace("{message}", (player.hasPermission("sunsethub.lpc.colorcodes") && player.hasPermission("sunsethub.lpc.rgbcodes")) ? this.colorize(this.translateHexColorCodes(message)) : (player.hasPermission("sunsethub.lpc.colorcodes") ? this.colorize(message) : (player.hasPermission("sunsethub.lpc.rgbcodes") ? this.translateHexColorCodes(message) : message))).replace("%", "%%"));
    }


    private String colorize(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    private String translateHexColorCodes(final String message) {
        final char colorChar = '§';
        final Matcher matcher = HEX_PATTERN.matcher(message);
        final StringBuffer buffer = new StringBuffer(message.length() + 32);
        while (matcher.find()) {
            final String group = matcher.group(1);
            matcher.appendReplacement(buffer, "§x§" + group.charAt(0) + '§' + group.charAt(1) + '§' + group.charAt(2) + '§' + group.charAt(3) + '§' + group.charAt(4) + '§' + group.charAt(5));
        }
        return matcher.appendTail(buffer).toString();
    }

    static {
        HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
    }

}