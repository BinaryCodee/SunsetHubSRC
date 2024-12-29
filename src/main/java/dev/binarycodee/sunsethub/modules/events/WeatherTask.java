package dev.binarycodee.sunsethub.modules.events;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class WeatherTask extends BukkitRunnable {

    @Override
    public void run() {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "weather clear 0");
    }
}
