package net.komvilcraft.nerotvlive.lobby.commands;

import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.apis.WarpAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private void sendSyntax(CommandSender s) {
        if(!(s instanceof Player)) {
            s.sendMessage("§cDieser Befehl wird nur als Spieler unterstützt.");
        } else {
            LobbyAPI.sendErrorMessage(s,"§4Fehler: §c/warp [set/remove/enable/disable/toggle/teleport/§7list§c] [Warp]");
        }
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(!(s instanceof Player)) {
            LobbyAPI.sendErrorMessage(s,"§cDazu musst du ein Spieler sein!");
        } else {
            Player p = (Player)s;
            if(p.hasPermission("komvilcraft.lobby.commands.warp")) {
                if(args.length == 0) {
                    sendSyntax(s);
                } else if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("list")) {
                        LobbyAPI.sendMessage(s,"Folgende Warps existieren:");
                        WarpAPI.getWarpList(s);
                    } else {
                        sendSyntax(s);
                    }
                } else {
                    String Warp = args[1];
                    if(args[0].equalsIgnoreCase("list")) {
                        LobbyAPI.sendMessage(s,"Folgende Warps existieren:");
                        WarpAPI.getWarpList(s);
                    } else if(args[0].equalsIgnoreCase("set")) {
                        if(WarpAPI.ifWarpExists(Warp)) {
                            LobbyAPI.sendErrorMessage(s,"§cDieser Warp existiert bereits!");
                        } else {
                            WarpAPI.setWarp(Warp,p,false);
                            LobbyAPI.sendMessage(s,"Du hast erfolgreich den Warp §e"+Warp+"§7 erstellt! Aktiviere ihn mit §f/warp enable "+Warp+"§7.");
                        }
                    } else if(args[0].equalsIgnoreCase("remove")) {
                        if(WarpAPI.ifWarpExists(Warp)) {
                            WarpAPI.removeWarp(Warp);
                            LobbyAPI.sendMessage(s,"Du hast erfolgreich den Warp §e"+Warp+"§7 gelöscht!");
                        } else {
                            LobbyAPI.sendErrorMessage(s,"§cDieser Warp existiert nicht!");
                        }
                    } else if(args[0].equalsIgnoreCase("enable")) {
                        if(WarpAPI.ifWarpExists(Warp)) {
                            if(WarpAPI.isWarpEnabled(Warp)) {
                                LobbyAPI.sendErrorMessage(s,"§cDieser Warp ist bereits aktiviert!");
                            } else {
                                WarpAPI.enableWarp(Warp);
                                LobbyAPI.sendMessage(s,"Du hast erfolgreich den Warp §e"+Warp+"§7 aktiviert!");
                            }
                        } else {
                            LobbyAPI.sendErrorMessage(s,"§cDieser Warp existiert nicht!");
                        }
                    } else if(args[0].equalsIgnoreCase("disable")) {
                        if(WarpAPI.ifWarpExists(Warp)) {
                            if(WarpAPI.isWarpEnabled(Warp)) {
                                WarpAPI.disableWarp(Warp);
                                LobbyAPI.sendMessage(s,"Du hast erfolgreich den Warp §e"+Warp+"§7 deaktiviert!");
                            } else {
                                LobbyAPI.sendErrorMessage(s,"§cDieser Warp ist bereits deaktiviert!");
                            }
                        } else {
                            LobbyAPI.sendErrorMessage(s,"§cDieser Warp existiert nicht!");
                        }
                    } else if(args[0].equalsIgnoreCase("toggle")) {
                        if(WarpAPI.ifWarpExists(Warp)) {
                            if(WarpAPI.isWarpEnabled(Warp)) {
                                p.performCommand("warp disable "+Warp);
                            } else {
                                p.performCommand("warp enable "+Warp);
                            }
                        } else {
                            LobbyAPI.sendErrorMessage(s,"§cDieser Warp existiert nicht!");
                        }
                    } else if(args[0].equalsIgnoreCase("teleport")) {
                        if(WarpAPI.ifWarpExists(Warp)) {
                            if(WarpAPI.isWarpEnabled(Warp)) {
                                p.teleport(WarpAPI.getWarp(Warp));
                                LobbyAPI.sendMessage(s,"Du bist nun bei §e"+Warp+"§7!");
                            } else {
                                LobbyAPI.sendErrorMessage(s,"§cDieser Warp ist nicht aktiviert!");
                            }
                        } else {
                            LobbyAPI.sendErrorMessage(s,"§cDieser Warp existiert nicht!");
                        }
                    }
                }
            } else {
                LobbyAPI.sendErrorMessage(s,LobbyAPI.noPerms);
            }
        }
        return false;
    }
}