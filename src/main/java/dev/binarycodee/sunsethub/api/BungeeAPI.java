package dev.binarycodee.sunsethub.api;

import dev.binarycodee.sunsethub.Core;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class BungeeAPI {

    public static void connect(Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(Core.getInstance(), "BungeeCord", b.toByteArray());
            out.close();
            b.close();
        } catch (Exception e) {
            System.out.println("[BungeeAPI] Errore durante il collegamento al server tramite BungeeCord: " + e.getMessage());
        }
    }
}
