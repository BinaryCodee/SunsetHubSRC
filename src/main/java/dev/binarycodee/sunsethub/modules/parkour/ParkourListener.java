package dev.binarycodee.sunsethub.modules.parkour;

import dev.binarycodee.sunsethub.Core;
import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ParkourListener implements Listener {

    private final ParkourItem parkourItem;

    public ParkourListener() {
        this.parkourItem = new ParkourItem();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInHand().isSimilar(parkourItem.getItem())) {
                Location parkourLocation = getParkourLocation();
                if (parkourLocation != null) {
                    player.teleport(parkourLocation);
                    player.sendMessage(ChatUtils.getConfigText("modules.parkour.teleported"));
                } else {
                    player.sendMessage(ChatUtils.getConfigText("modules.parkour.not-found"));
                }
            }
        }
    }

    private Location getParkourLocation() {
        FileConfiguration config = Core.getFileManager().getConfig();
        String locationStr = config.getString("modules.parkour.location");
        if (locationStr != null) {
            String[] parts = locationStr.split(",");
            if (parts.length == 6) {
                World world = Bukkit.getWorld(parts[0]);
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                int z = Integer.parseInt(parts[3]);
                float yaw = Float.parseFloat(parts[4]);
                float pitch = Float.parseFloat(parts[5]);
                return new Location(world, x, y, z, yaw, pitch);
            }
        }
        return null;
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem().getItemStack();
        if (item.isSimilar(ParkourItem.getItem())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();
        if (item.isSimilar(ParkourItem.getItem())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack parkour = ParkourItem.getItem();
        int inventorySlot = Core.getFileManager().getConfig().getInt("modules.parkour.item.inventory-slot");
        player.getInventory().setItem(inventorySlot, parkour);
    }


    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item.isSimilar(ParkourItem.getItem())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {
        ItemStack item = event.getItem();
        if (item.isSimilar(ParkourItem.getItem())) {
            event.setCancelled(true);
        }
    }
}
