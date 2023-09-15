package net.komvilcraft.nerotvlive.lobby.listeners;

import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.jumper.JumperAPI;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getLocation().getY() < 0) {
            p.teleport(LobbyAPI.getSpawn());
            p.sendMessage("§cOh§8...§cdu bist aus der Welt gefallen§8!");
            p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
        }
        if (!JumperAPI.Jumpers.containsKey(p)) {
            if ((e.getPlayer().getGameMode() == GameMode.ADVENTURE)
                    && (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
                p.setAllowFlight(true);
            }
        }
    }

    @EventHandler
    public void onPlayerFly(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if (!JumperAPI.Jumpers.containsKey(p)) {
            if (p.getGameMode() == GameMode.ADVENTURE) {
                e.setCancelled(true);
                p.setFlying(false);
                p.setAllowFlight(false);
                p.setVelocity(p.getLocation().getDirection().multiply(2.0D).setY(0.9D));
                p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 15);
            }
        }
    }
}