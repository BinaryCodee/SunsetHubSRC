package dev.binarycodee.sunsethub.modules.socials;

import dev.binarycodee.sunsethub.Core;
import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class SocialsItem {

    private static ItemStack item;
    private final boolean mainHand;
    private final int inventorySlot;

    public SocialsItem() {
        FileConfiguration config = Core.getFileManager().getConfig();

        String materialName = config.getString("modules.socials.item.material", "NETHER_STAR");
        Material material = Material.getMaterial(materialName);
        if (material == null) material = Material.NETHER_STAR;

        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            List<String> lores = config.getStringList("modules.socials.item.lore");
            if (lores.isEmpty()) {
                lores = Collections.singletonList("");
            }
            meta.setDisplayName(ChatUtils.getConfigText("modules.socials.item.name"));
            meta.setLore(ChatUtils.getColoredTextList(lores.toArray(new String[0])));
            item.setItemMeta(meta);
        }

        this.mainHand = config.getBoolean("modules.socials.item.main-hand", true);
        this.inventorySlot = config.getInt("modules.socials.item.inventory-slot", 8);
    }

    public static ItemStack getItem() {
        return item;
    }

    public boolean isMainHand() {
        return mainHand;
    }

    public int getInventorySlot() {
        return inventorySlot;
    }
}
