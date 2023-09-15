package net.komvilcraft.nerotvlive.lobby.commands;

import net.komvilcraft.nerotvlive.core.utils.Strings;
import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;

import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearchatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Clearchat")) {
            if(!(s instanceof Player p)) {
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if(all.hasPermission("komvilcraft.lobby.bypass.clearchat")) {
                        LobbyUser t = LobbyAPI.getLobbyUser(all.getUniqueId());
                        t.sendMessage("§7Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
                    } else {
                        for (int i = 0; i < 500; i++) {
                            all.sendMessage("");
                        }
                    }
                }
                Bukkit.getServer().getConsoleSender().sendMessage("§7Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
            } else {
                LobbyUser u = LobbyAPI.getLobbyUser(p.getUniqueId());
                if(p.hasPermission("komvilcraft.lobby.commands.clearchat")) {
                    p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG,100,100);
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        if(all.hasPermission("komvilcraft.lobby.bypass.clearchat")) {
                            LobbyUser t = LobbyAPI.getLobbyUser(all.getUniqueId());
                            t.sendMessage("§7Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
                        } else {
                            for (int i = 0; i < 500; i++) {
                                all.sendMessage("");
                            }
                        }
                    }
                    Bukkit.getServer().getConsoleSender().sendMessage("§7Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
                } else {
                    u.sendErrorMessage(Strings.noPerms());
                }
            }
        }
        return false;
    }
}