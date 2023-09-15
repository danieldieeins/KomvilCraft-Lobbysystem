package net.komvilcraft.nerotvlive.lobby.cosmetics;

import net.komvilcraft.nerotvlive.lobby.managers.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CosmeticInventoryManager {

    private static ItemStack i28;
    private static ItemStack i29;
    private static ItemStack i30;
    private static ItemStack i31;
    private static ItemStack i32;
    private static ItemStack i33;
    private static ItemStack i34;
    private static ItemStack i10;
    private static ItemStack i11;
    private static ItemStack i12;
    private static ItemStack i13;
    private static ItemStack i14;
    private static ItemStack i19;
    private static ItemStack i20;
    private static ItemStack i21;
    private static ItemStack i22;
    private static ItemStack i23;
    private static ItemStack i37;
    private static ItemStack i38;
    private static ItemStack i39;
    private static ItemStack i40;
    private static ItemStack i41;
    private static ItemStack i42;
    private static ItemStack i43;

    public static void fillInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i,ItemManager.placeholder());
        }
    }

    public static Inventory skullInventory = Bukkit.createInventory(null, 54, "§8Hüte");
    public static void initSkullInventory() {
        fillInventory(skullInventory);
        i28 = CosmeticItemManager.glassHat(false);
        i29 = CosmeticItemManager.spongeHat(false);
        i30 = CosmeticItemManager.bookHat(false);
        i31 = CosmeticItemManager.melonHat(false);
        i32 = CosmeticItemManager.musicHat(false);
        i33 = CosmeticItemManager.explosiveHat(false);
        i34 = CosmeticItemManager.pumpkinHat(false);
        i10 = CosmeticItemManager.nerotvliveSkull(false);
        i11 = CosmeticItemManager.attisanderSkull(false);
        i12 = CosmeticItemManager.pfaexSkull(false);
        i13 = CosmeticItemManager.nyuunSkull(false);
        i14 = CosmeticItemManager.schleimerSkull(false);
        i19 = CosmeticItemManager.Maxi15122Skull(false);
        i20 = CosmeticItemManager.add_WestSkull(false);
        i21 = CosmeticItemManager.herrbergmannSkull(false);
        i22 = CosmeticItemManager.fantasticotvSkull(false);
        i23 = CosmeticItemManager.ungeSkull(false);
        i37 = CosmeticItemManager.turtleHelmet(false);
        i38 = CosmeticItemManager.leatherHelmet(false);
        i39 = CosmeticItemManager.chainHelmet(false);
        i40 = CosmeticItemManager.ironHelmet(false);
        i41 = CosmeticItemManager.goldHelmet(false);
        i42 = CosmeticItemManager.diamondHelmet(false);
        i43 = CosmeticItemManager.netheriteHelmet(false);
    }

    public static void openCosmeticsInventory(Player p) {
        Inventory cosmeticsInventory = Bukkit.createInventory(null, 9, "§8Cosmetics");
        fillInventory(cosmeticsInventory);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        cosmeticsInventory.setItem(1, CosmeticItemManager.hats());
        cosmeticsInventory.setItem(2, CosmeticItemManager.chests());
        cosmeticsInventory.setItem(4, CosmeticItemManager.leggings());
        cosmeticsInventory.setItem(5, CosmeticItemManager.boots());
        cosmeticsInventory.setItem(7, ItemManager.backEX());
        p.openInventory(cosmeticsInventory);
    }

    public static void openChestsInventory(Player p) {
        Inventory chestsInventory = Bukkit.createInventory(null, 9, "§8Oberteile");
        fillInventory(chestsInventory);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        chestsInventory.setItem(4, CosmeticItemManager.goldChest(false));
        chestsInventory.setItem(3, CosmeticItemManager.ironChest(false));
        chestsInventory.setItem(5, CosmeticItemManager.diamondChest(false));
        chestsInventory.setItem(2, CosmeticItemManager.chainChest(false));
        chestsInventory.setItem(6, CosmeticItemManager.netheriteChest(false));
        chestsInventory.setItem(1, CosmeticItemManager.leatherChest(false));
        chestsInventory.setItem(7, ItemManager.backCS());
        p.openInventory(chestsInventory);
    }

    public static void openLeggingsInventory(Player p) {
        Inventory chestsInventory = Bukkit.createInventory(null, 9, "§8Oberteile");
        fillInventory(chestsInventory);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        chestsInventory.setItem(4, CosmeticItemManager.goldLeggings(false));
        chestsInventory.setItem(3, CosmeticItemManager.ironLeggings(false));
        chestsInventory.setItem(5, CosmeticItemManager.diamondLeggings(false));
        chestsInventory.setItem(2, CosmeticItemManager.chainLeggings(false));
        chestsInventory.setItem(6, CosmeticItemManager.netheriteLeggings(false));
        chestsInventory.setItem(1, CosmeticItemManager.leatherLeggings(false));
        chestsInventory.setItem(7, ItemManager.backCS());
        p.openInventory(chestsInventory);
    }

    public static void openBootsInventory(Player p) {
        Inventory chestsInventory = Bukkit.createInventory(null, 9, "§8Oberteile");
        fillInventory(chestsInventory);
        chestsInventory.setItem(4, CosmeticItemManager.goldBoots(false));
        chestsInventory.setItem(3, CosmeticItemManager.ironBoots(false));
        chestsInventory.setItem(5, CosmeticItemManager.diamondBoots(false));
        chestsInventory.setItem(2, CosmeticItemManager.chainBoots(false));
        chestsInventory.setItem(6, CosmeticItemManager.netheriteBoots(false));
        chestsInventory.setItem(1, CosmeticItemManager.leatherBoots(false));
        chestsInventory.setItem(7, ItemManager.backCS());
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        p.openInventory(chestsInventory);
    }

    public static void openSkullInventory(Player p) {
        fillInventory(skullInventory);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        skullInventory.setItem(10, i10);
        skullInventory.setItem(11, i11);
        skullInventory.setItem(12, i12);
        skullInventory.setItem(13, i13);
        skullInventory.setItem(14, i14);
        skullInventory.setItem(16, ItemManager.backCS());
        skullInventory.setItem(19, i19);
        skullInventory.setItem(20, i20);
        skullInventory.setItem(21, i21);
        skullInventory.setItem(22, i22);
        skullInventory.setItem(23, i23);
        skullInventory.setItem(28, i28);
        skullInventory.setItem(29, i29);
        skullInventory.setItem(30, i30);
        skullInventory.setItem(31, i31);
        skullInventory.setItem(32, i32);
        skullInventory.setItem(33, i33);
        skullInventory.setItem(34, i34);
        skullInventory.setItem(37, i37);
        skullInventory.setItem(38, i38);
        skullInventory.setItem(39, i39);
        skullInventory.setItem(40, i40);
        skullInventory.setItem(41, i41);
        skullInventory.setItem(42, i42);
        skullInventory.setItem(43, i43);
        p.openInventory(skullInventory);
    }
}