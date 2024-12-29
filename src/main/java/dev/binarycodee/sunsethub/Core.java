package dev.binarycodee.sunsethub;

import dev.binarycodee.sunsethub.api.BungeeMessageListener;
import dev.binarycodee.sunsethub.modules.events.LPCEvent;
import dev.binarycodee.sunsethub.modules.events.PlayerListener;
import dev.binarycodee.sunsethub.modules.events.WeatherTask;
import dev.binarycodee.sunsethub.modules.fly.FlyCMD;
import dev.binarycodee.sunsethub.modules.gamemodes.*;
import dev.binarycodee.sunsethub.modules.home.SetSpawnCMD;
import dev.binarycodee.sunsethub.modules.home.SpawnCMD;
import dev.binarycodee.sunsethub.modules.main.MainCMD;
import dev.binarycodee.sunsethub.modules.parkour.ParkourCMD;
import dev.binarycodee.sunsethub.modules.parkour.ParkourListener;
import dev.binarycodee.sunsethub.modules.scoreboard.ScoreboardListener;
import dev.binarycodee.sunsethub.modules.scoreboard.ScoreboardTask;
import dev.binarycodee.sunsethub.modules.selector.SelectorGUIListener;
import dev.binarycodee.sunsethub.modules.selector.SelectorListener;
import dev.binarycodee.sunsethub.modules.socials.SocialsGUIListener;
import dev.binarycodee.sunsethub.modules.socials.SocialsListener;
import dev.binarycodee.sunsethub.modules.teleports.TpAllCMD;
import dev.binarycodee.sunsethub.modules.teleports.TpCMD;
import dev.binarycodee.sunsethub.modules.teleports.TpHereCMD;
import dev.binarycodee.sunsethub.utils.ChatUtils;
import dev.binarycodee.sunsethub.utils.FileManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.config.ServerInfo;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private static Core instance;
    private static FileManager fileManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        fileManager = new FileManager(this);

        getCommand("setspawn").setExecutor(new SetSpawnCMD());
        getCommand("spawn").setExecutor(new SpawnCMD());
        getCommand("parkour").setExecutor(new ParkourCMD());
        getCommand("sunsethub").setExecutor(new MainCMD(this));
        getCommand("fly").setExecutor(new FlyCMD());
        getCommand("gmc").setExecutor(new CreativeCMD());
        getCommand("gms").setExecutor(new SurvivalCMD());
        getCommand("gmsp").setExecutor(new SpectatorCMD());
        getCommand("gma").setExecutor(new AdventureCMD());
        getCommand("tp").setExecutor(new TpCMD());
        getCommand("tpall").setExecutor(new TpAllCMD());
        getCommand("tphere").setExecutor(new TpHereCMD());

        getServer().getPluginManager().registerEvents(new SelectorListener(), this);
        getServer().getPluginManager().registerEvents(new SelectorGUIListener(this), this);
        getServer().getPluginManager().registerEvents(new SocialsListener(), this);
        getServer().getPluginManager().registerEvents(new SocialsGUIListener(this), this);
        getServer().getPluginManager().registerEvents(new ParkourListener(), this);
        getServer().getPluginManager().registerEvents(new ScoreboardListener(), this);
        getServer().getPluginManager().registerEvents(new LPCEvent(), this);

        new ScoreboardTask(this).startTask();
        new WeatherTask().runTask(this);

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeMessageListener());
    }

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }

    public static Core getInstance() {
        return instance;
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public ProxiedPlayer getBungeePlayer(String playerName) {
        return ProxyServer.getInstance().getPlayer(playerName);
    }

    public static void connectToServer(Player player, String serverName) {
        ProxiedPlayer bungeePlayer = ProxyServer.getInstance().getPlayer(player.getName());
        if (bungeePlayer != null) {
            ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(serverName);
            if (serverInfo != null) {
                bungeePlayer.connect(serverInfo);
            } else {
                player.sendMessage(ChatUtils.getConfigText("modules.selector.messages.server-offline")
                        .replace("%server%", serverName));
            }
        } else {
            player.sendMessage(ChatUtils.getConfigText("modules.selector.messages.server-not-found"));
        }
    }

    public ServerInfo getBungeeServerInfo(String serverName) {
        return ProxyServer.getInstance().getServerInfo(serverName);
    }
}
