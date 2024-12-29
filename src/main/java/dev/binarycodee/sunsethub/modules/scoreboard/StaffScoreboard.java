package dev.binarycodee.sunsethub.modules.scoreboard;

import dev.binarycodee.sunsethub.Core;
import dev.binarycodee.sunsethub.api.HooksAPI;
import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;

public class StaffScoreboard {

    private final Player player;
    private final Scoreboard scoreboard;
    private final Objective objective;

    public StaffScoreboard(Player player) {
        this.player = player;
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        this.scoreboard = manager.getNewScoreboard();
        this.objective = scoreboard.registerNewObjective(ChatUtils.getConfigText("modules.scoreboard.staff.title"), "dummy");
        objective.setDisplaySlot(org.bukkit.scoreboard.DisplaySlot.SIDEBAR);
    }

    public void update() {
        List<String> lines = HooksAPI.applyPlaceholders(player, Core.getFileManager().getConfig().getStringList("modules.scoreboard.staff.lore"));
        int index = lines.size();
        for (String line : lines) {
            objective.getScore(line).setScore(index--);
        }
        player.setScoreboard(scoreboard);
    }
}