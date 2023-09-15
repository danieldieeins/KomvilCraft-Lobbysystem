package net.komvilcraft.nerotvlive.lobby.commands;

import net.komvilcraft.nerotvlive.core.utils.Strings;
import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;

import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportCommand implements CommandExecutor {

    void sendSyntax(Player p) {
        LobbyUser u = LobbyAPI.getLobbyUser(p.getUniqueId());
        u.sendErrorMessage("§4Fehler: §c/tp [X] [Y] [Z]§7, §c/tp [Spieler] §7[Spieler]§7 oder §c/tp [Spieler] [X] [Y] [Z]");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender s, Command cmd, @NotNull String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Teleport")) {
            if(!(s instanceof Player p)) {
                if(args.length == 2) {
                    if(Bukkit.getPlayer(args[0]) != null) {
                        if(Bukkit.getPlayer(args[1]) != null) {
                            Player p = Bukkit.getPlayer(args[0]);
                            Player t = Bukkit.getPlayer(args[1]);
                            assert p != null;
                            assert t != null;
                            p.teleport(t.getLocation());
                            s.sendMessage(Strings.prefix()+"Du hast den Spieler §e"+p.getName()+"§7 zu §e"+t.getName()+"§7 teleportiert!");
                        } else {
                            s.sendMessage("§cDer Spieler §4"+args[1]+"§c ist nicht online!");
                        }
                    } else {
                        s.sendMessage("§cDer Spieler §4"+args[0]+"§c ist nicht online!");
                    }
                } else if(args.length == 4) {
                    if(Bukkit.getPlayer(args[0]) != null) {
                        if(isNumericPart(args[1])) {
                            if(isNumericPart(args[2])) {
                                if(isNumericPart(args[3])) {
                                    Player p = Bukkit.getPlayer(args[0]);
                                    double x = Double.parseDouble(args[1]);
                                    double y = Double.parseDouble(args[2]);
                                    double z = Double.parseDouble(args[3]);
                                    assert p != null;
                                    p.teleport(new Location(p.getWorld(),x,y,z,p.getLocation().getYaw(),p.getLocation().getPitch()));
                                    s.sendMessage(Strings.prefix()+"Du hast den Spieler §e"+p.getName()+"§7 zu §aX"+x+" Y"+y+" Z"+z+"§7 teleportiert!");
                                } else {
                                    s.sendMessage("§4Fehler: §c/tp [Spieler] §7[Spieler]§7 oder §c/tp [Spieler] [X] [Y] [Z]");
                                }
                            } else {
                                s.sendMessage("§4Fehler: §c/tp [Spieler] §7[Spieler]§7 oder §c/tp [Spieler] [X] [Y] [Z]");
                            }
                        } else {
                            s.sendMessage("§4Fehler: §c/tp [Spieler] §7[Spieler]§7 oder §c/tp [Spieler] [X] [Y] [Z]");
                        }
                    } else {
                        s.sendMessage(Strings.playerNotFound());
                    }
                } else {
                    s.sendMessage("§4Fehler: §c/tp [Spieler] §7[Spieler]§7 oder §c/tp [Spieler] [X] [Y] [Z]");
                }
            } else {
                LobbyUser u = LobbyAPI.getLobbyUser(p.getUniqueId());
                if(p.hasPermission("komvilcraft.lobby.commands.teleport")) {
                    if(args.length == 0) {
                        sendSyntax(p);
                    } else if(args.length == 1) {
                        if(Bukkit.getPlayer(args[0]) != null) {
                            Player t = Bukkit.getPlayer(args[0]);
                            assert t != null;
                            p.teleport(t.getLocation());
                            u.sendMessage("Du hast dich zu §e"+t.getName()+"§7 teleportiert!");
                        } else {
                            u.sendErrorMessage(Strings.playerNotFound());
                        }
                    } else if(args.length == 2) {
                        if(Bukkit.getPlayer(args[0]) != null) {
                            if(Bukkit.getPlayer(args[1]) != null) {
                                Player p1 = Bukkit.getPlayer(args[0]);
                                Player p2 = Bukkit.getPlayer(args[1]);
                                assert p1 != null;
                                assert p2 != null;
                                p1.teleport(p2.getLocation());
                                u.sendMessage("Du hast §e"+p1.getName()+"§7 zu §e"+p2.getName()+"§7 teleportiert!");
                            } else {
                                u.sendErrorMessage(Strings.playerNotFound());
                            }
                        } else {
                            u.sendErrorMessage(Strings.playerNotFound());
                        }
                    } else if(args.length == 3){
                        if(isNumericPart(args[0])) {
                            if(isNumericPart(args[1])) {
                                if(isNumericPart(args[2])) {
                                    double x = Double.parseDouble(args[0]);
                                    double y = Double.parseDouble(args[1]);
                                    double z = Double.parseDouble(args[2]);
                                    Location loc = new Location(p.getWorld(),x,y,z,p.getLocation().getYaw(),p.getLocation().getPitch());
                                    p.teleport(loc);
                                    u.sendMessage("Du hast dich zu §eX"+x+" Y"+y+" Z"+z+"§7 teleportiert!");
                                } else {
                                    sendSyntax(p);
                                }
                            } else {
                                sendSyntax(p);
                            }
                        } else {
                            sendSyntax(p);
                        }
                    } else {
                        if(Bukkit.getPlayer(args[0]) != null) {
                            if(isNumericPart(args[1])) {
                                if(isNumericPart(args[2])) {
                                    if(isNumericPart(args[3])) {
                                        Player t = Bukkit.getPlayer(args[0]);
                                        assert t != null;
                                        t.teleport(new Location(t.getWorld(),Double.parseDouble(args[1]),Double.parseDouble(args[2]),Double.parseDouble(args[2]),t.getLocation().getYaw(),t.getLocation().getPitch()));
                                        u.sendMessage("Du hast den Spieler §e"+t.getName()+"§7 zu §a X"+args[1]+" Y"+args[2]+" Z"+args[3]+"§7 teleportiert!");
                                    } else {
                                        sendSyntax(p);
                                    }
                                } else {
                                    sendSyntax(p);
                                }
                            } else {
                                sendSyntax(p);
                            }
                        } else {
                            u.sendErrorMessage(Strings.playerNotFound());
                        }
                    }
                } else {
                    u.sendErrorMessage(Strings.noPerms());
                }
            }
        }
        return false;
    }

    private boolean isNumericPart(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}