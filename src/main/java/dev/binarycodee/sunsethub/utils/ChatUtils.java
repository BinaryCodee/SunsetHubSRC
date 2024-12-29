package dev.binarycodee.sunsethub.utils;

import dev.binarycodee.sunsethub.Core;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ChatUtils {

    public static String getColoredText(String text) {
        if (text == null) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String getConfigText(String path) {
        String text = Core.getFileManager().getConfig().getString(path);
        return getColoredText(text);
    }

    public static List<String> getColoredTextList(String... texts) {
        List<String> coloredTexts = new ArrayList<>();
        for (String text : texts) {
            coloredTexts.add(getColoredText(text));
        }
        return coloredTexts;
    }
}
