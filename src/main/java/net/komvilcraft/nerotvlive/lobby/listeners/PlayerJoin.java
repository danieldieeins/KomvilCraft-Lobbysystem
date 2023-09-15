package net.komvilcraft.nerotvlive.lobby.listeners;

import net.komvilcraft.nerotvlive.core.paper.utils.user.rank.RankManager;
import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.managers.ItemManager;
import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Bukkit.getWorlds().get(0).setDifficulty(Difficulty.PEACEFUL);
        Player p = e.getPlayer();
        p.teleport(LobbyAPI.spawn);
        p.setLastDeathLocation(LobbyAPI.spawn);
        LobbyUser lP = LobbyAPI.getLobbyUser(p.getUniqueId());
        LobbyAPI.renewScoreboard(p);
        for(Player all : Bukkit.getOnlinePlayers()) {
            RankManager.setRank(all);
        }
        ItemManager.giveItems(p);
        p.setGameMode(GameMode.ADVENTURE);
        e.setJoinMessage("§8» §a"+lP.getName());
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Player p = e.getPlayer();
        if(LobbyAPI.maintenance) {
            if(!p.hasPermission("komvilcraft.bypass.maintenance")&&!LobbyAPI.maintenanceList.contains(p.getUniqueId())) {
                e.setKickMessage("§cKomvilCraft.net ist in Wartungsarbeiten.");
                e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            }
        }
    }
}