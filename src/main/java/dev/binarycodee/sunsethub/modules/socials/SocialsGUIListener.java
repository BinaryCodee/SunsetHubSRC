package dev.binarycodee.sunsethub.modules.socials;

import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class SocialsGUIListener implements Listener {

    private Plugin plugin;
    private FileConfiguration fc;

    public SocialsGUIListener(final Plugin plugin) {
        this.plugin = plugin;
        this.fc = plugin.getConfig();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null || event.getCurrentItem() == null) {
            return;
        }

        String inventoryName = event.getView().getTitle();
        String coloredText = ChatUtils.getColoredText("&7Socials");
        if (inventoryName != null && inventoryName.equals(coloredText)) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem.getType() == Material.SKULL_ITEM) {
                ItemMeta meta = clickedItem.getItemMeta();
                if (meta != null && meta.hasDisplayName()) {
                    String displayName = meta.getDisplayName();
                    Player player = (Player) event.getWhoClicked();

                    if (displayName.equalsIgnoreCase(ChatUtils.getColoredText("&9&lDISCORD"))) {
                        player.closeInventory();
                        player.sendMessage(ChatUtils.getColoredText("&9&lDISCORD &7https://discord.gg/sunsetpvp/"));
                    }
                    if (displayName.equalsIgnoreCase(ChatUtils.getColoredText("&6&lTELEGRAM"))) {
                        player.closeInventory();
                        player.sendMessage(ChatUtils.getColoredText("&6&lTELEGRAM &7https://t.me/@SunsetPvP"));
                    }
                    if (displayName.equalsIgnoreCase(ChatUtils.getColoredText("&c&lSTORE"))) {
                        player.closeInventory();
                        player.sendMessage(ChatUtils.getColoredText("&c&lSTORE &7https://store.sunsetpvp.it/"));
                    }
                    if (displayName.equalsIgnoreCase(ChatUtils.getColoredText("&b&lSITO"))) {
                        player.closeInventory();
                        player.sendMessage(ChatUtils.getColoredText("&b&lSITO &7https://www.sunsetpvp.it/"));
                    }
                    if (displayName.equalsIgnoreCase(ChatUtils.getColoredText("&5&lTIKTOK"))) {
                        player.closeInventory();
                        player.sendMessage(ChatUtils.getColoredText("&5&lTIKTOK &7https://tiktok.com/@SunsetPvP"));
                    }
                }
            }
        }
    }
}
