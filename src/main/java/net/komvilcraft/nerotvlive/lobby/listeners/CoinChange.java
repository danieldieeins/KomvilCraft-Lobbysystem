package net.komvilcraft.nerotvlive.lobby.listeners;

import net.komvilcraft.nerotvlive.core.paper.events.PostCoinChangeEvent;
import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CoinChange implements Listener {

    @EventHandler
    public void onCoinChange(PostCoinChangeEvent e) {
        if(Bukkit.getPlayer(e.getUUID())!=null) {
            LobbyAPI.renewScoreboard(Bukkit.getPlayer(e.getUUID()));
        }
    }
}