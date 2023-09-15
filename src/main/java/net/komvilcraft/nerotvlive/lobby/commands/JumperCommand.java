package net.komvilcraft.nerotvlive.lobby.commands;

import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.jumper.Jumper;
import net.komvilcraft.nerotvlive.lobby.jumper.JumperAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JumperCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Jumper")) {
            if(s instanceof Player p) {
                if(JumperAPI.Jumpers.containsKey(p)) {
                    LobbyAPI.sendErrorMessage(p,"§cDu spielst bereits einen Jumper§8!");
                } else {
                    LobbyAPI.sendMessage(p,"§7Viel Spaß beim Spielen von §aJumper§8!");
                    new Jumper(p);
                    LobbyAPI.renewScoreboard(p);
                }
            } else {
                LobbyAPI.sendErrorMessage(s,LobbyAPI.needPlayer);
            }
        }
        return false;
    }
}