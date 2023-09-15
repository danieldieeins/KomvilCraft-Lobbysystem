package net.komvilcraft.nerotvlive.lobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ThunderCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Thunder")) {
            if(!(s instanceof Player)) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"weather thunder");
            } else {
                Player p = (Player)s;
                p.performCommand("weather thunder");
            }
        }
        return false;
    }
}