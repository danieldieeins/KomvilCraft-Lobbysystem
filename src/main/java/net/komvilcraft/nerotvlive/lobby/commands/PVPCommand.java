package net.komvilcraft.nerotvlive.lobby.commands;

import net.komvilcraft.nerotvlive.core.utils.Strings;
import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;

import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PVPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(s instanceof Player p) {
            LobbyUser u = LobbyAPI.getLobbyUser(p);
            if(!u.hasBuild()) {
                u.togglePVPMode();
                u.sendMessage("§7Dein §aPVP§8-§aMode§7 steht nun auf§8: §e"+u.isPVPMode());
            } else {
                u.sendErrorMessage("§cDu bist im Build-Mode§8.");
            }
        } else {
            LobbyAPI.sendErrorMessage(s, Strings.needPlayer());
        }
        return false;
    }
}