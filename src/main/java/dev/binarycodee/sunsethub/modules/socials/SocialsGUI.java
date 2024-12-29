package dev.binarycodee.sunsethub.modules.socials;

import dev.binarycodee.sunsethub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import dev.binarycodee.sunsethub.utils.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class SocialsGUI {

    public static void openSocialsGUI(final Player player) {
        final Inventory socialsGUI = Bukkit.createInventory((InventoryHolder) player, 27, ChatUtils.getColoredText("&7Socials"));

        ItemStack ds = CustomSkull.getSkull("http://textures.minecraft.net/texture/7873c12bffb5251a0b88d5ae75c7247cb39a75ff1a81cbe4c8a39b311ddeda", ChatUtils.getColoredText("&9&lDISCORD"));
        ItemStack tg = CustomSkull.getSkull("http://textures.minecraft.net/texture/3eb3a539937f8e8e117034a7173bdded5a0323c9794aaedf1918731f4f8729bb", ChatUtils.getColoredText("&6&lTELEGRAM"));
        ItemStack store = CustomSkull.getSkull("http://textures.minecraft.net/texture/9fd108383dfa5b02e86635609541520e4e158952d68c1c8f8f200ec7e88642d", ChatUtils.getColoredText("&c&lSTORE"));
        ItemStack sito = CustomSkull.getSkull("http://textures.minecraft.net/texture/438cf3f8e54afc3b3f91d20a49f324dca1486007fe545399055524c17941f4dc", ChatUtils.getColoredText("&b&lSITO"));
        ItemStack tiktok = CustomSkull.getSkull("http://textures.minecraft.net/texture/58d02984a43e6c6910d0d908a57e041c3cfb1dd881b5b720c55563e681f59e0e", ChatUtils.getColoredText("&5&lTIKTOK"));

        socialsGUI.setItem(16, tiktok);
        socialsGUI.setItem(14, sito);
        socialsGUI.setItem(13, store);
        socialsGUI.setItem(12, tg);
        socialsGUI.setItem(10, ds);
        player.openInventory(socialsGUI);
    }
}
