package net.komvilcraft.nerotvlive.lobby.managers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryManager {

    public static void fillInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i,ItemManager.placeholder());
        }
    }

    public static void clearInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i,null);
        }
    }

    public static void profileOptions(Player p) {
        Inventory profileOptionsInventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§8Profil und Optionen§8");
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        fillInventory(profileOptionsInventory);
        p.openInventory(profileOptionsInventory);
        profileOptionsInventory.setItem(1, ItemManager.profile(p));
        profileOptionsInventory.setItem(3, ItemManager.playerHider(p));
    }

    public static Inventory navigator(Player player) {
        Inventory inv = Bukkit.createInventory(null,InventoryType.ENDER_CHEST,"§8Navigator");
        fillInventory(inv);

        inv.setItem(10,ItemManager.bedwars());
        inv.setItem(11,ItemManager.survivalGames());
        inv.setItem(13,ItemManager.spawn());
        inv.setItem(15,ItemManager.freebuild());
        inv.setItem(16,ItemManager.komvilJump());

        player.playSound(player.getLocation(),Sound.ENTITY_CHICKEN_EGG,100,100);
        return inv;
    }

    public static Inventory extras(Player player) {
        Inventory inv = Bukkit.createInventory(null,InventoryType.HOPPER,"§8Extras");
        fillInventory(inv);
        inv.setItem(1,ItemManager.cosmetics());
        inv.setItem(3,ItemManager.pvpMode(player));
        player.playSound(player.getLocation(),Sound.ENTITY_CHICKEN_EGG,100,100);
        return inv;
    }
}