package net.komvilcraft.nerotvlive.lobby.managers;

import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemManager {

    public static void giveItems(Player player) {
        LobbyUser u = LobbyAPI.getLobbyUser(player);
        if(u.isPVPMode()) {
            player.getInventory().clear();
            InventoryManager.clearInventory(player.getInventory());
            ItemStack helmet = new ItemStack(Material.IRON_HELMET);
            ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
            ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
            ItemStack boots = new ItemStack(Material.IRON_BOOTS);
            ItemMeta helmetMeta = helmet.getItemMeta();
            ItemMeta chestplateMeta = chestplate.getItemMeta();
            ItemMeta leggingsMeta = leggings.getItemMeta();
            ItemMeta bootsMeta = boots.getItemMeta();
            helmetMeta.setUnbreakable(true);
            helmetMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            chestplateMeta.setUnbreakable(true);
            chestplateMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            leggingsMeta.setUnbreakable(true);
            leggingsMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            bootsMeta.setUnbreakable(true);
            bootsMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);
            player.getInventory().setHelmet(helmet);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(leggings);
            player.getInventory().setBoots(boots);
            ItemStack sword = new ItemStack(Material.IRON_SWORD);
            ItemStack shield = new ItemStack(Material.SHIELD);
            ItemMeta swordMeta = sword.getItemMeta();
            ItemMeta shieldMeta = shield.getItemMeta();
            swordMeta.setUnbreakable(true);
            swordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            shieldMeta.setUnbreakable(true);
            shieldMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            sword.setItemMeta(swordMeta);
            shield.setItemMeta(shieldMeta);
            player.getInventory().setItem(0,sword);
            //player.getInventory().setItemInOffHand(shield);
        } else {
            ItemStack helmet = null;
            ItemStack chestplate = null;
            ItemStack leggings = null;
            ItemStack boots = null;
            if (player.getInventory().getHelmet() != null) {
                helmet = player.getInventory().getHelmet();
            }
            if (player.getInventory().getChestplate() != null) {
                chestplate = player.getInventory().getChestplate();
            }
            if (player.getInventory().getLeggings() != null) {
                leggings = player.getInventory().getLeggings();
            }
            if (player.getInventory().getBoots() != null) {
                boots = player.getInventory().getBoots();
            }
            InventoryManager.clearInventory(player.getInventory());
            player.getInventory().setItemInOffHand(null);
            player.getInventory().setItem(2, profileOptions(player));
            player.getInventory().setItem(4, navigator());
            player.getInventory().setItem(6, extras());
            player.getInventory().setHelmet(helmet);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(leggings);
            player.getInventory().setBoots(boots);
        }
    }

    public static ItemStack navigator() {
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName("§aNavigator");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack placeholder() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName("§0");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack spawn() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName("§aSpawn");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack komvilJump() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta leatherMeta = (LeatherArmorMeta)item.getItemMeta();
        leatherMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        leatherMeta.setDisplayName("§aFightJump");
        leatherMeta.setColor(Color.fromRGB(75,214,34));
        item.setItemMeta(leatherMeta);
        return item;
    }

    public static ItemStack cosmetics() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta leatherMeta = (LeatherArmorMeta)item.getItemMeta();
        leatherMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        leatherMeta.setDisplayName("§aCosmetics");
        leatherMeta.setColor(Color.fromRGB(75,214,34));
        item.setItemMeta(leatherMeta);
        return item;
    }

    public static ItemStack bedwars() {
        ItemStack item = new ItemStack(Material.GREEN_BED);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName("§aBedwars");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack survivalGames() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName("§aSurvivalGames");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack freebuild() {
        ItemStack item = new ItemStack(Material.COARSE_DIRT);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName("§aFreebuild");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack extras() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName("§aExtras");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack backEX() {
        ItemStack item = new ItemStack(Material.DARK_OAK_DOOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName("§e<- Zurück §3§2§0§4§9");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack backCS() {
        ItemStack item = new ItemStack(Material.DARK_OAK_DOOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName("§e<- Zurück§1 §2§6§8§7");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack backOP() {
        ItemStack item = new ItemStack(Material.DARK_OAK_DOOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName("§e<- Zurück§1 §6§8§7§1");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack profile(Player p) {
        ItemStack Return = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta ProfileMeta = (SkullMeta) Return.getItemMeta();
        ProfileMeta.setOwningPlayer(p);
        ProfileMeta.setDisplayName("§aFreunde§8-§a und Partysystem");
        Return.setItemMeta(ProfileMeta);
        return Return;
    }

    public static ItemStack profileOptions(Player p) {
        ItemStack Return = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta ProfileMeta = (SkullMeta) Return.getItemMeta();
        ProfileMeta.setOwningPlayer(p);
        ProfileMeta.setDisplayName("§aProfil und Optionen§8");
        Return.setItemMeta(ProfileMeta);
        return Return;
    }

    public static ItemStack playerHider(Player p) {
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§aSpieler verstecken");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack pvpMode(Player p) {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§aKampfmodus");
        item.setItemMeta(itemMeta);
        return item;
    }
}