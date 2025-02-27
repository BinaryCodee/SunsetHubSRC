package dev.binarycodee.sunsethub.api;

import org.bukkit.command.CommandSender;

public abstract class SubCommandAPI {
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getSyntax();
    public abstract String getPermission();
    public abstract void perform(CommandSender sender, String[] args);
}
