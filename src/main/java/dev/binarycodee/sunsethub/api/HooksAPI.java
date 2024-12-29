package dev.binarycodee.sunsethub.api;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.context.ContextManager;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HooksAPI {

    private static LuckPerms luckPerms;

    static {
        try {
            luckPerms = LuckPermsProvider.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public static String applyPlaceholders(Player player, String text) {
        if (text == null) return "";
        return ChatColor.translateAlternateColorCodes('&', text)
                .replace("%date%", new SimpleDateFormat("dd/MM/yyyy").format(new Date()))
                .replace("%player%", player.getName())
                .replace("%rank%", getRank(player))
                .replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%ping%", String.valueOf(getPing(player)))
                .replace("%ram%", getRamUsage())
                .replace("%cpu%", getCpuUsage())
                .replace("%tps%", String.format("%.2f", getTPS()));
    }

    public static List<String> applyPlaceholders(Player player, List<String> lines) {
        return lines.stream().map(line -> applyPlaceholders(player, line)).collect(Collectors.toList());
    }

    public static String getRank(Player player) {
        if (luckPerms == null) return "No luckperms found";

        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user == null) {
            return "No rank found";
        }

        String groupName = user.getPrimaryGroup();
        if ("default".equalsIgnoreCase(groupName) || "Default".equalsIgnoreCase(groupName)) {
            return "Utente";
        }

        if (groupName == null || groupName.isEmpty()) {
            return "Utente";
        }

        Optional<Group> groupOptional = Optional.ofNullable(luckPerms.getGroupManager().getGroup(groupName));
        if (!groupOptional.isPresent()) {
            return "Group not found";
        }

        Group group = groupOptional.get();
        String prefix = group.getDisplayName();

        String displayName = prefix != null ? ChatColor.translateAlternateColorCodes('&', prefix) : group.getName();
        return capitalizeFirstLetter(displayName);
    }

    private static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    private static int getPing(Player player) {
        try {
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            return (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static String getRamUsage() {
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        long usedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
        return usedMemory + "MB / " + maxMemory + "MB";
    }

    private static String getCpuUsage() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        return String.format("%.2f%%", osBean.getSystemLoadAverage() * 100);
    }

    private static double getTPS() {
        try {
            Object minecraftServer = Bukkit.getServer().getClass().getMethod("getServer").invoke(Bukkit.getServer());
            Field tpsField = minecraftServer.getClass().getField("recentTps");
            double[] tps = (double[]) tpsField.get(minecraftServer);
            return tps[0];
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}