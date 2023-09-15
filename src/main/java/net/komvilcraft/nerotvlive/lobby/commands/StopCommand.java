package net.komvilcraft.nerotvlive.lobby.commands;

import net.komvilcraft.nerotvlive.core.paper.Core;
import net.komvilcraft.nerotvlive.core.utils.Strings;
import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("SRL")) {
            if(s.hasPermission("komvilcraft.lobby.commands.stop")) {
                if(s instanceof Player p) {
                    LobbyUser u = LobbyAPI.getLobbyUser(p.getUniqueId());
                    u.sendMessage("§7Du hast den §aStopvorgang §7gestartet§8...");
                } else {
                    s.sendMessage(Strings.prefix()+"§7Du hast den §aStopvorgang §7gestartet§8...");
                }
                Core.getCoreServer().stopServer();
            } else {
                if(s instanceof Player p) {
                    LobbyUser u = LobbyAPI.getLobbyUser(p.getUniqueId());
                    u.sendErrorMessage(Strings.noPerms());
                } else {
                    s.sendMessage(Strings.noPerms());
                }
            }
        }
        return false;
    }
}
