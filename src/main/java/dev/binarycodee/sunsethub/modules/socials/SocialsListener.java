package dev.binarycodee.sunsethub.modules.socials;

import dev.binarycodee.sunsethub.Core;
import dev.binarycodee.sunsethub.modules.selector.SelectorGUI;
import dev.binarycodee.sunsethub.modules.selector.SelectorItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public class SocialsListener implements Listener {

    private final SocialsItem socialsItem;

    public SocialsListener() {
        this.socialsItem = new SocialsItem();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInHand().isSimilar(socialsItem.getItem())) {
                SocialsGUI.openSocialsGUI(player);
            }
        }
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem().getItemStack();
        if (item.isSimilar(SocialsItem.getItem())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();
        if (item.isSimilar(SocialsItem.getItem())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack socials = SocialsItem.getItem();
        int inventorySlot = Core.getFileManager().getConfig().getInt("modules.socials.item.inventory-slot");
        player.getInventory().setItem(inventorySlot, socials);
    }


    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item.isSimilar(SocialsItem.getItem())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {
        ItemStack item = event.getItem();
        if (item.isSimilar(SocialsItem.getItem())) {
            event.setCancelled(true);
        }
    }
}
