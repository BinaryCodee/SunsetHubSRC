package dev.binarycodee.sunsethub.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class CustomSkull {

    public static ItemStack getSkull(String url, String displayName) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();

        if (headMeta == null) {
            return head;
        }

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        String texture = String.format("{textures:{SKIN:{url:\"%s\"}}}", url);
        byte[] encodedData = Base64.encodeBase64(texture.getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        headMeta.setDisplayName(displayName);
        head.setItemMeta(headMeta);
        return head;
    }
}
