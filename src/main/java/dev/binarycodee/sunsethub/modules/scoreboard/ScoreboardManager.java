package dev.binarycodee.sunsethub.modules.scoreboard;

import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ScoreboardManager {

    private final Map<Player, LobbyScoreboard> lobbyScoreboards = new HashMap<>();
    private final Map<Player, StaffScoreboard> staffScoreboards = new HashMap<>();
    private final FileConfiguration config;

    public ScoreboardManager(FileConfiguration config) {
        this.config = config;
    }

    public void createLobbyScoreboard(Player player) {
        LobbyScoreboard lobbyScoreboard = new LobbyScoreboard(player);
        lobbyScoreboards.put(player, lobbyScoreboard);
        lobbyScoreboard.update();
    }

    public void createStaffScoreboard(Player player) {
        StaffScoreboard staffScoreboard = new StaffScoreboard(player);
        staffScoreboards.put(player, staffScoreboard);
        staffScoreboard.update();
    }

    public void removeScoreboard(Player player) {
        lobbyScoreboards.remove(player);
        staffScoreboards.remove(player);
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    public void updateAllScoreboards() {
        lobbyScoreboards.values().forEach(LobbyScoreboard::update);
        staffScoreboards.values().forEach(StaffScoreboard::update);
    }

    public static void setupScoreboard(Player player) {
        if (player.hasPermission(ChatUtils.getConfigText("modules.scoreboard.staff.permission"))) {
            setupStaffScoreboard(player);
        } else {
            setupLobbyScoreboard(player);
        }
    }

    public static void setupLobbyScoreboard(Player player) {
        LobbyScoreboard scoreboard = new LobbyScoreboard(player);
        scoreboard.update();
    }

    public static void setupStaffScoreboard(Player player) {
        StaffScoreboard scoreboard = new StaffScoreboard(player);
        scoreboard.update();
    }
}
