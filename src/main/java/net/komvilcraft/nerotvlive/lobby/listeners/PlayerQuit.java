package net.komvilcraft.nerotvlive.lobby.listeners;

import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.jumper.Jumper;
import net.komvilcraft.nerotvlive.lobby.jumper.JumperAPI;
import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        LobbyUser lP = LobbyAPI.getLobbyUser(p.getUniqueId());
        if(lP.isPVPMode()) {
            lP.setPVPMode(false);
        }
        if(JumperAPI.Jumpers.containsKey(p)) {
            Jumper j = JumperAPI.Jumpers.get(p);
            j.fail();
        }
        LobbyAPI.onlinePlayers.remove(p.getUniqueId());
        e.setQuitMessage("§8« §c"+lP.getName());
    }
}