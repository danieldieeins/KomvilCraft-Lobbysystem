package net.komvilcraft.nerotvlive.lobby.managers;

import net.komvilcraft.nerotvlive.core.paper.utils.user.rank.Rank;
import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatManager implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        LobbyUser l = LobbyAPI.getLobbyUser(p.getUniqueId());
        Rank r = l.getRank();
        String m = e.getMessage();
        e.setCancelled(true);
        if(p.hasPermission("komvilcraft.user.friend")||p.hasPermission("komvilcraft.user.komvil")) {
            m = m.replace("&&","%and%").replace("&","§").replace("%and%","&");
        }
        Bukkit.broadcastMessage(r.getPrefix()+l.getName()+"§8 » §7"+m);
    }
}