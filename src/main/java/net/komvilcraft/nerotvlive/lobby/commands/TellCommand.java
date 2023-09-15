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
import org.jetbrains.annotations.NotNull;

public class TellCommand implements CommandExecutor {

    private void sendSyntax(CommandSender s) {
        if(s instanceof Player p){
            LobbyUser u = LobbyAPI.getLobbyUser(p.getUniqueId());
            u.sendErrorMessage("§4Fehler: §c/tell §c[Spieler] [Nachricht]");
        } else {
            s.sendMessage("§4Fehler: §c/tell §c[Spieler] [Nachricht]");
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender s, Command cmd, @NotNull String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Tell")) {
            if(!(s instanceof Player p)) {
                if(args.length >= 2) {
                    if(Bukkit.getPlayer(args[0])!=null) {
                        StringBuilder msg= new StringBuilder();
                        for(int i=1;i<args.length;i++) {
                            msg.append(args[i]).append(" ");
                        }
                        Player t = Bukkit.getPlayer(args[0]);
                        msg = new StringBuilder(msg.toString().replace("&", "§"));
                        assert t != null;
                        s.sendMessage("§8[§7MSG§8] §6Du §f-> §e" + t.getName() + "§8: §7"+msg);
                        LobbyUser u = LobbyAPI.getLobbyUser(t.getUniqueId());
                        u.sendRawMessage("§8[§7MSG§8] §cKONSOLE§f -> §6Dir §8: §7" + msg);
                        t.playSound(t.getLocation(), Sound.ENTITY_CAT_PURREOW, 100, 100);
                    } else {
                        s.sendMessage(Strings.playerNotFound());
                    }
                } else {
                    sendSyntax(s);
                }
            } else {
                LobbyUser u = LobbyAPI.getLobbyUser(p.getUniqueId());
                String PN = p.getName();
                if(args.length >= 2) {
                    StringBuilder msg= new StringBuilder();
                    for(int i=1;i<args.length;i++) {
                        msg.append(args[i]).append(" ");
                    }
                    msg = new StringBuilder(msg.toString().replace("&", "§"));
                    if(Bukkit.getPlayer(args[0]) != null) {
                        Player p2 = Bukkit.getPlayer(args[0]);
                        assert p2 != null;
                        String P2N = p2.getName();
                        LobbyUser u2 = LobbyAPI.getLobbyUser(p2.getUniqueId());
                        if(PN.equals(P2N)) {
                            u.sendErrorMessage("§cDu solltest eventuell darüber nachdenken, mit wem anders zu schreiben, statt mit dir selber.");
                        } else {
                            u.sendRawMessage("§8[§7MSG§8] §6Du §f-> §e" + P2N + "§8: §7" + msg);
                            u2.sendRawMessage("§8[§7MSG§8] §e" + PN + "§f -> §6Dir §8: §7" + msg);
                            p2.playSound(p2.getLocation(), Sound.ENTITY_CAT_PURREOW, 100, 100);
                        }
                    } else {
                        u.sendErrorMessage(Strings.playerNotFound());
                    }
                } else {
                    sendSyntax(p);
                }
            }
        }
        return false;
    }
}