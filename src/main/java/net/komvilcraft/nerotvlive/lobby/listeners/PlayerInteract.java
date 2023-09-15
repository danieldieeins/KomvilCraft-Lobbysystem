package net.komvilcraft.nerotvlive.lobby.listeners;

import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.managers.InventoryManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!p.isSneaking()) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (e.getItem() != null) {
                    if (e.getItem().getItemMeta() != null) {
                        ItemStack item = e.getItem();
                        ItemMeta itemMeta = item.getItemMeta();
                        if (itemMeta.getDisplayName().toLowerCase().contains("spawn")) {
                            p.teleport(LobbyAPI.getSpawn());
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
                            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 100, 100);
                        } else if (itemMeta.getDisplayName().contains("Profil")) {
                            InventoryManager.profileOptions(p);
                        } else if (itemMeta.getDisplayName().toLowerCase().contains("navigator")) {
                            p.openInventory(InventoryManager.navigator(p));
                        } else if (itemMeta.getDisplayName().toLowerCase().contains("extras")) {
                            p.openInventory(InventoryManager.extras(p));
                        }
                    }
                }
            }
        }
    }
}