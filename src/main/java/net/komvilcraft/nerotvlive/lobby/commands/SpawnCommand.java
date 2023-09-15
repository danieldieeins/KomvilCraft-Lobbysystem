package net.komvilcraft.nerotvlive.lobby.commands;

import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            if(s instanceof Player p) {
                p.teleport(LobbyAPI.spawn);
                p.playSound(p.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,100,100);
            } else {
                LobbyAPI.sendErrorMessage(s, LobbyAPI.needPlayer);
            }
        } else {
            if(args[0].equalsIgnoreCase("set")) {
                if(s.hasPermission("komvilcraft.lobbysystem.spawn.set")) {
                    if(s instanceof Player p) {
                        LobbyAPI.setSpawn(p.getLocation());
                        LobbyAPI.sendMessage(p,"§7Du hast erfolgreich den §eSpawnpunkt§7 gesetzt§8!");
                    } else {
                        LobbyAPI.sendErrorMessage(s, LobbyAPI.needPlayer);
                    }
                } else {
                    Bukkit.dispatchCommand(s,"spawn");
                }
            } else {
                Bukkit.dispatchCommand(s,"spawn");
            }
        }
        return false;
    }
}
