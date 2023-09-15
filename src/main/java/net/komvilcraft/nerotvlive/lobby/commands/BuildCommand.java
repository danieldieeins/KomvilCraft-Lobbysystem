package net.komvilcraft.nerotvlive.lobby.commands;

import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Build")) {
            if(s instanceof Player p) {
                LobbyUser l = LobbyAPI.getLobbyUser(p.getUniqueId());
                if(p.hasPermission("komvilcraft.lobbysystem.build")) {
                    l.toggleBuild();
                    l.sendMessage("§7Dein §eBuild§8-§eMode§7 steht nun auf§8: §a"+l.hasBuild());
                } else {
                    l.sendErrorMessage(LobbyAPI.noPerms);
                }
            } else {
                LobbyAPI.sendErrorMessage(s,LobbyAPI.needPlayer);
            }
        }
        return false;
    }
}