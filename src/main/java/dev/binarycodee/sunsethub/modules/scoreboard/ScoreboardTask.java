package dev.binarycodee.sunsethub.modules.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import dev.binarycodee.sunsethub.Core;

public class ScoreboardTask extends BukkitRunnable {

    private final Core plugin;

    public ScoreboardTask(Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ScoreboardManager.setupScoreboard(player);
        }
    }

    public void startTask() {
        int updateInterval = Core.getFileManager().getConfig().getInt("modules.scoreboard.update-interval", 10);
        this.runTaskTimer(plugin, 1, updateInterval);
    }
}