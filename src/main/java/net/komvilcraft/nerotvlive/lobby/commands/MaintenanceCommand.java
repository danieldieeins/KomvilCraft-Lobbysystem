package net.komvilcraft.nerotvlive.lobby.commands;

import net.komvilcraft.nerotvlive.core.utils.Strings;
import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;

import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MaintenanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(s instanceof Player p) {
            LobbyUser u = LobbyAPI.getLobbyUser(p.getUniqueId());
            if(p.hasPermission("komvilcraft.lobby.commands.maintenance")) {
                if(args.length == 0) {
                    LobbyAPI.maintenance = !LobbyAPI.maintenance;
                    u.sendMessage("§7Du hast den §aWartungsmodus§7 auf §e" + LobbyAPI.maintenance + "§7 gesetzt§8!");
                } else if(args.length == 2) {
                    if(p.hasPermission("komvilcraft.lobby.commands.maintenance.whitelist")) {
                        if(args[0].equalsIgnoreCase("add")) {
                            if(!LobbyAPI.maintenanceList.contains(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString())) {
                                LobbyAPI.maintenanceList.add(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString());
                                u.sendMessage("§7Du hast erfolgreich §a"+Bukkit.getOfflinePlayer(args[1]).getName()+"§7 gewhitelisted§8.");
                                LobbyAPI.config.set("Core.Settings.Maintenance.BypassList",LobbyAPI.maintenanceList);
                                LobbyAPI.config.saveConfig();
                                LobbyAPI.config.reloadConfig();
                            } else {
                                u.sendErrorMessage("§cDieser Spieler ist bereits gewhitelisted§8!");
                            }
                        } else if(args[0].equalsIgnoreCase("remove")) {
                            if(LobbyAPI.maintenanceList.contains(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString())) {
                                LobbyAPI.maintenanceList.remove(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString());
                                u.sendMessage("§7Du hast erfolgreich §a"+Bukkit.getOfflinePlayer(args[1]).getName()+"§7 von der Whitelist entfernt§8.");
                                LobbyAPI.config.set("Core.Settings.Maintenance.BypassList",LobbyAPI.maintenanceList);
                                LobbyAPI.config.saveConfig();
                                LobbyAPI.config.reloadConfig();
                            } else {
                                u.sendErrorMessage("§cDieser Spieler ist nicht gewhitelisted§8!");
                            }
                        }
                    } else {
                        u.sendErrorMessage(Strings.noPerms());
                    }
                } else {
                    p.performCommand("maintenance");
                }
            } else {
                u.sendErrorMessage(Strings.noPerms());
            }
        } else {
            if(s.hasPermission("komvilcraft.lobby.commands.maintenance")) {
                LobbyAPI.maintenance = !LobbyAPI.maintenance;
                LobbyAPI.sendMessage(s,"§7Du hast den §aWartungsmodus§7 auf §e"+LobbyAPI.maintenance+"§7 gesetzt§8!");
            } else {
                LobbyAPI.sendErrorMessage(s,Strings.noPerms());
            }
        }
        return false;
    }
}
