package dev.binarycodee.sunsethub.modules.selector;

import dev.binarycodee.sunsethub.api.BungeeAPI;
import org.bukkit.event.Listener;
import org.bukkit.plugin.messaging.*;
import org.bukkit.plugin.*;
import org.bukkit.configuration.file.*;
import org.bukkit.event.inventory.*;
import dev.binarycodee.sunsethub.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import dev.binarycodee.sunsethub.*;
import com.google.common.io.*;
import org.bukkit.event.*;

public class SelectorGUIListener implements Listener {

    private Plugin plugin;
    private FileConfiguration fc;

    public SelectorGUIListener(final Plugin plugin) {
        this.plugin = plugin;
        this.fc = plugin.getConfig();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final String inventoryName = event.getInventory().getName();
        final String coloredText = ChatUtils.getColoredText("&7Selettore Modalita'");
        if (inventoryName != null && coloredText != null && inventoryName.equals(coloredText)) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                Player player = (Player)event.getWhoClicked();
                BungeeAPI.connect(player, "kitpvp");
            }
        }
    }

}
