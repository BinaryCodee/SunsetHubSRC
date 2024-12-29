package dev.binarycodee.sunsethub.modules.selector;

import org.bukkit.entity.*;
import dev.binarycodee.sunsethub.utils.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class SelectorGUI {
    public static void openSelectorGUI(final Player player) {
        final Inventory selectorGUI = Bukkit.createInventory((InventoryHolder)player, 45, ChatUtils.getColoredText("&7Selettore Modalita'"));

        final ItemStack kitpvp = new ItemStack(Material.DIAMOND_SWORD);
        final ItemMeta kitpvpMeta = kitpvp.getItemMeta();
        kitpvpMeta.setDisplayName(ChatUtils.getColoredText("&6&lKITPVP OP"));
        final List<String> kitpvpLore = new ArrayList<String>();
        kitpvpLore.add(ChatUtils.getColoredText("&7"));
        kitpvpLore.add(ChatUtils.getColoredText("&7Affronta una nuova avventura nel"));
        kitpvpLore.add(ChatUtils.getColoredText("&7nostro fantastico &eKITPVP&6! &7Uccidi"));
        kitpvpLore.add(ChatUtils.getColoredText("&7i &cnemici&7 e diventa il più forte"));
        kitpvpLore.add(ChatUtils.getColoredText("&7creando la tua &cgang&6!"));
        kitpvpLore.add(ChatUtils.getColoredText("&7"));
        kitpvpLore.add(ChatUtils.getColoredText("&eClicca per Entrare!"));
        kitpvpLore.add(ChatUtils.getColoredText(""));
        kitpvpMeta.setLore((List)kitpvpLore);
        kitpvp.setItemMeta(kitpvpMeta);

        final ItemStack rdm = new ItemStack(Material.COAL);
        final ItemMeta rdmMeta = rdm.getItemMeta();
        rdmMeta.setDisplayName(ChatUtils.getColoredText("&e&lRDM"));
        final List<String> rdmLore = new ArrayList<String>();
        rdmLore.add(ChatUtils.getColoredText("&7"));
        rdmLore.add(ChatUtils.getColoredText("&c&lSOON"));
        rdmLore.add(ChatUtils.getColoredText("&7"));
        rdmLore.add(ChatUtils.getColoredText("&eClicca per Entrare!"));
        rdmMeta.setLore((List)rdmLore);
        rdm.setItemMeta(rdmMeta);

        final ItemStack galaxybox = new ItemStack(Material.DIAMOND_CHESTPLATE);
        final ItemMeta galaxyboxMeta = galaxybox.getItemMeta();
        galaxyboxMeta.setDisplayName(ChatUtils.getColoredText("&6&lGALAXY BOX"));
        final List<String> galaxyboxLore = new ArrayList<String>();
        galaxyboxLore.add(ChatUtils.getColoredText("&7"));
        galaxyboxLore.add(ChatUtils.getColoredText("&c&lSOON"));
        galaxyboxLore.add(ChatUtils.getColoredText("&7"));
        galaxyboxLore.add(ChatUtils.getColoredText("&eClicca per Entrare!"));
        galaxyboxMeta.setLore((List)galaxyboxLore);
        galaxybox.setItemMeta(galaxyboxMeta);

        final ItemStack link = new ItemStack(Material.FIREWORK);
        final ItemMeta linkMeta = link.getItemMeta();
        linkMeta.setDisplayName(ChatUtils.getColoredText("&6&lLINK UTILI &7&l& &e&lSUPPORTO"));
        final List<String> linkLore = new ArrayList<String>();
        linkLore.add(ChatUtils.getColoredText("&e"));
        linkLore.add(ChatUtils.getColoredText("&eDiscord: &fdiscord.sunsetpvp.it"));
        linkLore.add(ChatUtils.getColoredText("&eWebsite: &fwww.sunsetpvp.it"));
        linkLore.add(ChatUtils.getColoredText("&eStore: &fstore.sunsetpvp.it"));
        linkLore.add(ChatUtils.getColoredText("&e"));
        linkLore.add(ChatUtils.getColoredText("&7Se hai bisogno di &c&lSUPPORTO&7 apri un ticket"));
        linkLore.add(ChatUtils.getColoredText("&7sul nostro server &6&lDISCORD&e!"));
        linkMeta.setLore((List)linkLore);
        link.setItemMeta(linkMeta);

        final ItemStack eventi = new ItemStack(Material.TNT);
        final ItemMeta EventiMeta = eventi.getItemMeta();
        EventiMeta.setDisplayName(ChatUtils.getColoredText("&c&lEVENTI &7&l& &5&lPARTNER"));
        final List<String> eventiLore = new ArrayList<String>();
        eventiLore.add(ChatUtils.getColoredText("&e"));
        eventiLore.add(ChatUtils.getColoredText("&7Questo è il server dedicato per "));
        eventiLore.add(ChatUtils.getColoredText("&7gli &6&lEVENTI &7del server"));
        eventiLore.add(ChatUtils.getColoredText("&7creati dai vari &5&lPARTNER &7/ &d&lCREATOR&e!"));
        eventiLore.add(ChatUtils.getColoredText("&7"));
        eventiLore.add(ChatUtils.getColoredText("&7Divertiti provando i nostri vari eventi, tra"));
        eventiLore.add(ChatUtils.getColoredText("&c&lSUMO&7, &e&lTNT TAG&7, &b&lDUELS &7e molto altro..."));
        eventiLore.add(ChatUtils.getColoredText("&e"));
        eventiLore.add(ChatUtils.getColoredText("&cAttualmente non disponibile!"));
        EventiMeta.setLore((List)eventiLore);
        eventi.setItemMeta(EventiMeta);

        final ItemStack amici = new ItemStack(Material.BOOK_AND_QUILL);
        final ItemMeta amiciMeta = amici.getItemMeta();
        amiciMeta.setDisplayName(ChatUtils.getColoredText("&b&lAMICI"));
        final List<String> amiciLore = new ArrayList<String>();
        amiciLore.add(ChatUtils.getColoredText("&7"));
        amiciLore.add(ChatUtils.getColoredText("&7Arricchisci la tua esperienza nel server,"));
        amiciLore.add(ChatUtils.getColoredText("&7facendo nuove &d&lAMICIZIE&e!"));
        amiciLore.add(ChatUtils.getColoredText("&7"));
        amiciLore.add(ChatUtils.getColoredText("&8(&6/friend&8) &bPer saperne di più&e!"));
        amiciMeta.setLore((List)amiciLore);
        amici.setItemMeta(amiciMeta);


        selectorGUI.setItem(10, link);
        selectorGUI.setItem(19, eventi);
        selectorGUI.setItem(28, amici);
        selectorGUI.setItem(24, galaxybox);
        selectorGUI.setItem(23, rdm);
        selectorGUI.setItem(22, kitpvp);
        player.openInventory(selectorGUI);
    }
}
