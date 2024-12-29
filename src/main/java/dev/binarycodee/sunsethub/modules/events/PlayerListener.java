package dev.binarycodee.sunsethub.modules.events;

import dev.binarycodee.sunsethub.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {

    private final Core plugin;

    public PlayerListener(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("events.join.enabled")) {
            if (plugin.getConfig().getBoolean("events.join.teleport-to-spawn")) {
                Location spawnLocation = (Location) plugin.getFileManager().getConfig().get("modules.spawn.location");
                if (spawnLocation != null) {
                    event.getPlayer().teleport(spawnLocation);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (plugin.getConfig().getBoolean("events.respawn.enabled")) {
            if (plugin.getConfig().getBoolean("events.respawn.teleport-to-spawn")) {
                Location spawnLocation = (Location) plugin.getFileManager().getConfig().get("modules.spawn.location");
                if (spawnLocation != null) {
                    event.setRespawnLocation(spawnLocation);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (plugin.getConfig().getBoolean("events.hearts-and-hunger.enabled")) {
            event.getPlayer().setMaxHealth(plugin.getConfig().getInt("events.hearts-and-hunger.hearts"));
            event.getPlayer().setHealth(plugin.getConfig().getInt("events.hearts-and-hunger.hearts"));
            event.getPlayer().setFoodLevel(plugin.getConfig().getInt("events.hearts-and-hunger.hunger"));
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (plugin.getConfig().getBoolean("events.block-break-place.enabled") &&
                plugin.getConfig().getBoolean("events.block-break-place.prevent-break")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (plugin.getConfig().getBoolean("events.block-break-place.enabled") &&
                plugin.getConfig().getBoolean("events.block-break-place.prevent-place")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (plugin.getConfig().getBoolean("events.water-lava-fire.enabled")) {
            if (plugin.getConfig().getBoolean("events.water-lava-fire.prevent-drowning") &&
                    event.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                event.setCancelled(true);
            }
            if (plugin.getConfig().getBoolean("events.water-lava-fire.prevent-fire-damage") &&
                    (event.getCause() == EntityDamageEvent.DamageCause.FIRE ||
                            event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK ||
                            event.getCause() == EntityDamageEvent.DamageCause.LAVA)) {
                event.setCancelled(true);
            }
        }
        if (plugin.getConfig().getBoolean("events.fall-damage.enabled") &&
                event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (plugin.getConfig().getBoolean("events.inventory.enabled")) {
            if (plugin.getConfig().getBoolean("events.inventory.prevent-drop") && event.getClickedInventory() != null) {
                event.setCancelled(true);
            }
            if (plugin.getConfig().getBoolean("events.inventory.prevent-move") && event.getClickedInventory() != null) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (plugin.getConfig().getBoolean("events.inventory.enabled") &&
                plugin.getConfig().getBoolean("events.inventory.prevent-move")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryPickup(InventoryPickupItemEvent event) {
        if (plugin.getConfig().getBoolean("events.inventory.enabled") &&
                plugin.getConfig().getBoolean("events.inventory.prevent-drop")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (plugin.getConfig().getBoolean("events.interact.enabled")) {
            if (plugin.getConfig().getBoolean("events.interact.prevent-interact")) {
                if (event.getClickedBlock() != null &&
                        (event.getClickedBlock().getType() == Material.LEVER ||
                                event.getClickedBlock().getType() == Material.FENCE ||
                                event.getClickedBlock().getType() == Material.FENCE_GATE)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void WeatherChangeEvent(WeatherChangeEvent event) {
        if (plugin.getConfig().getBoolean("events.weather.enabled")) {
            if (!event.toWeatherState()) {
                return;
            }

            event.setCancelled(true);
            event.getWorld().setWeatherDuration(0);
            event.getWorld().setThundering(false);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (plugin.getConfig().getBoolean("events.death.enabled")) {
            if (plugin.getConfig().getBoolean("events.death.prevent-drop")) {
                event.getDrops().clear();
            }
        }
    }

    @EventHandler
    public void onPlayerAchievement(PlayerAchievementAwardedEvent event) {
        if (plugin.getConfig().getBoolean("events.achievements.enabled") &&
                !plugin.getConfig().getBoolean("events.achievements.allow-awards")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (plugin.getConfig().getBoolean("events.pvp.enabled")) {
            if (event.getEntity() instanceof org.bukkit.entity.Player) {
                if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK &&
                        ((org.bukkit.entity.Player) event.getEntity()).getKiller() instanceof org.bukkit.entity.Player) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
