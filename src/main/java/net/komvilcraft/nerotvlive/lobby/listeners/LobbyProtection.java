package net.komvilcraft.nerotvlive.lobby.listeners;

import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.managers.ItemManager;
import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.*;

public class LobbyProtection implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        LobbyUser lP = LobbyAPI.getLobbyUser(p.getUniqueId());
        if (!lP.hasBuild()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
        LobbyUser lP = LobbyAPI.getLobbyUser(p.getUniqueId());
        if (!lP.hasBuild()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        LobbyUser lP = LobbyAPI.getLobbyUser(p.getUniqueId());
        if (!lP.hasBuild()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        LobbyUser lP = LobbyAPI.getLobbyUser(p.getUniqueId());
        if (!lP.hasBuild()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Player p) {
            if(e.getDamager() instanceof Player d) {
                LobbyUser u = LobbyAPI.getLobbyUser(p);
                LobbyUser dU = LobbyAPI.getLobbyUser(d);
                if(u.isPVPMode()&&dU.isPVPMode()) {
                    return;
                }
            }
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onHanging(HangingBreakByEntityEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onExtendedHanging(HangingBreakEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        LobbyUser lP = LobbyAPI.getLobbyUser(p.getUniqueId());
        if (!lP.hasBuild()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        LobbyUser lP = LobbyAPI.getLobbyUser(p.getUniqueId());
        if (!lP.hasBuild()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getPlayer();
        if(p.getKiller()!=null) {
            Player k = p.getKiller();
            LobbyAPI.sendMessage(k,"§7Du hast §a"+p.getName()+"§7 getötet§8!");
            LobbyAPI.sendMessage(p,"§7Du wurdest von §e"+k.getName()+"§7 getötet§8!");
            k.playSound(k.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,100,100);
            p.playSound(p.getLocation(),Sound.ENTITY_IRON_GOLEM_DEATH,100,100);
            p.setKiller(null);
        }
        e.setKeepInventory(true);
        e.getPlayer().getInventory().clear();
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player p) {
            LobbyUser u = LobbyAPI.getLobbyUser(p);
            if(!u.isPVPMode()) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        e.getPlayer().getInventory().clear();
        e.setRespawnLocation(LobbyAPI.getSpawn());
        ItemManager.giveItems(e.getPlayer());
    }
}