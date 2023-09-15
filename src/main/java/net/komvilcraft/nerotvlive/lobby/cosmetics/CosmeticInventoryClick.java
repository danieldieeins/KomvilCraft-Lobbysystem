package net.komvilcraft.nerotvlive.lobby.cosmetics;

import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.managers.ItemManager;
import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CosmeticInventoryClick implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        LobbyUser lP = LobbyAPI.getLobbyUser(p.getUniqueId());
        if(e.getCurrentItem()!=null) {
            ItemStack item = e.getCurrentItem();
            if(item.getItemMeta()!=null) {
                ItemMeta itemMeta = item.getItemMeta();
                String itemName = itemMeta.getDisplayName();
                if(itemName.equals(ItemManager.cosmetics().getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    p.closeInventory();
                    CosmeticInventoryManager.openCosmeticsInventory(p);
                } else if(itemName.equals(CosmeticItemManager.hats().getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    p.closeInventory();
                    CosmeticInventoryManager.openSkullInventory(p);
                } else if(itemName.equals("§anerotvlive§8'§as §7Kopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.skull.nerotvlive")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.nerotvliveSkull(true));
                        lP.sendMessage("Du trägst nun den Kopf von §anerotvlive§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aFantasticoTV§8'§as §7Kopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.skull.fantasticotv")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.fantasticotvSkull(true));
                        lP.sendMessage("Du trägst nun den Kopf von §aFantasticoTV§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aPfaex§8'§as §7Kopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.skull.pfaex")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.pfaexSkull(true));
                        lP.sendMessage("Du trägst nun den Kopf von §aPfaex§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aNyuun§8'§as §7Kopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.skull.nyuun")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.nyuunSkull(true));
                        lP.sendMessage("Du trägst nun den Kopf von §anyuun§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aSchleimerHD§8'§as §7Kopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.skull.schleimerhd")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.schleimerSkull(true));
                        lP.sendMessage("Du trägst nun den Kopf von §aSchleimerHD§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aAttisander§8'§as §7Kopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.skull.attisander")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.attisanderSkull(true));
                        lP.sendMessage("Du trägst nun den Kopf von §aAttisander§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aungespielt§8'§as §7Kopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.skull.ungespielt")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.ungeSkull(true));
                        lP.sendMessage("Du trägst nun den Kopf von §aungespielt§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aadd_West§8'§as §7Kopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.skull.add_west")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.add_WestSkull(true));
                        lP.sendMessage("Du trägst nun den Kopf von §aadd_West§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aHerrBergmann§8'§as §7Kopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.skull.herrbergmann")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.herrbergmannSkull(true));
                        lP.sendMessage("Du trägst nun den Kopf von §aHerrBergmann§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aMaxi15122§8'§as §7Kopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.skull.Maxi15122")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.Maxi15122Skull(true));
                        lP.sendMessage("Du trägst nun den Kopf von §aMaxi15122§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aSchwammkopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.hat.sponge")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.spongeHat(true));
                        lP.sendMessage("Du trägst nun den §aSchwammkopf§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aGlaskopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.hat.glass")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.glassHat(true));
                        lP.sendMessage("Du trägst nun den §aGlaskopf§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aMusikbox")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.hat.music")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.musicHat(true));
                        lP.sendMessage("Du trägst nun den §aMusikbox-Kopf§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aTNT")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.hat.tnt")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.explosiveHat(true));
                        lP.sendMessage("Du trägst nun den §aTNT-Kopf§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aKürbislaterne")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.hat.pumpkin")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.pumpkinHat(true));
                        lP.sendMessage("Du trägst nun den §aKürbislaternen-Kopf§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aBücherkopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.hat.book")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.bookHat(true));
                        lP.sendMessage("Du trägst nun den §aBücherkopf§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aMelonenkopf")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.hat.melon")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.melonHat(true));
                        lP.sendMessage("Du trägst nun den §aMelonenkopf§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aSchildkrötenhelm")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.helmet.turtle")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.turtleHelmet(true));
                        lP.sendMessage("Du trägst nun den §aSchildkrötenhelm§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aLederhelm")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.helmet.leather")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.leatherHelmet(true));
                        lP.sendMessage("Du trägst nun den §aLederhelm§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aKettenhelm")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.helmet.chain")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.chainHelmet(true));
                        lP.sendMessage("Du trägst nun den §aKettenhelm§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aEisenhelm")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.helmet.iron")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.ironHelmet(true));
                        lP.sendMessage("Du trägst nun den §aEisenhelm§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aGoldhelm")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.helmet.gold")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.goldHelmet(true));
                        lP.sendMessage("Du trägst nun den §aGoldhelm§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aDiamanthelm")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.helmet.diamond")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.diamondHelmet(true));
                        lP.sendMessage("Du trägst nun den §aDiamanthelm§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aNetherithelm")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.helmet.netherite")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setHelmet(CosmeticItemManager.netheriteHelmet(true));
                        lP.sendMessage("Du trägst nun den §aNetherithelm§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aLederharnisch")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.chests.leather")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setChestplate(CosmeticItemManager.leatherChest(true));
                        lP.sendMessage("Du trägst nun den §aLederharnisch§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aKettenharnisch")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.chests.chain")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setChestplate(CosmeticItemManager.chainChest(true));
                        lP.sendMessage("Du trägst nun den §aKettenharnisch§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aEisenharnisch")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.chests.iron")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setChestplate(CosmeticItemManager.ironChest(true));
                        lP.sendMessage("Du trägst nun den §aEisenharnisch§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aGoldharnisch")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.chests.gold")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setChestplate(CosmeticItemManager.goldChest(true));
                        lP.sendMessage("Du trägst nun den §aGoldharnisch§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aDiamantharnisch")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.chests.diamond")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setChestplate(CosmeticItemManager.diamondChest(true));
                        lP.sendMessage("Du trägst nun den §aDiamantharnisch§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aNetheritharnisch")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.chests.netherite")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setChestplate(CosmeticItemManager.netheriteChest(true));
                        lP.sendMessage("Du trägst nun den §aNetheritharnisch§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aLederhose")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.leggings.leather")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setLeggings(CosmeticItemManager.leatherLeggings(true));
                        lP.sendMessage("Du trägst nun den §aLederhose§8.");
                        p.closeInventory();
                    }
                } else if (itemMeta.getDisplayName().toLowerCase().contains("oberteile")) {
                    e.setCancelled(true);
                    CosmeticInventoryManager.openChestsInventory(p);
                } else if (itemMeta.getDisplayName().toLowerCase().contains("hosen")) {
                    e.setCancelled(true);
                    CosmeticInventoryManager.openLeggingsInventory(p);
                } else if (itemMeta.getDisplayName().contains("Schuhe")) {
                    e.setCancelled(true);
                    CosmeticInventoryManager.openBootsInventory(p);
                } else if(itemName.equals("§aKettenhose")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.leggings.chain")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setLeggings(CosmeticItemManager.chainLeggings(true));
                        lP.sendMessage("Du trägst nun den §aKettenhose§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aEisenhose")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.leggings.iron")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setLeggings(CosmeticItemManager.ironLeggings(true));
                        lP.sendMessage("Du trägst nun den §aEisenhose§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aGoldhose")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.leggings.gold")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setLeggings(CosmeticItemManager.goldLeggings(true));
                        lP.sendMessage("Du trägst nun den §aGoldhose§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aDiamanthose")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.leggings.diamond")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setLeggings(CosmeticItemManager.diamondLeggings(true));
                        lP.sendMessage("Du trägst nun den §aDiamanthose§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals("§aNetherithose")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.leggings.netherite")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setLeggings(CosmeticItemManager.netheriteLeggings(true));
                        lP.sendMessage("Du trägst nun den §aNetherithose§8.");
                        p.closeInventory();
                    }
                } else if(itemName.toLowerCase().contains("lederschuhe")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.boots.leather")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setBoots(CosmeticItemManager.leatherBoots(true));
                        lP.sendMessage("Du trägst nun den §aLederschuhe§8.");
                        p.closeInventory();
                    }
                } else if(itemName.toLowerCase().contains("kettenschuhe")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.boots.chain")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setBoots(CosmeticItemManager.chainBoots(true));
                        lP.sendMessage("Du trägst nun den §aKettenschuhe§8.");
                        p.closeInventory();
                    }
                } else if(itemName.toLowerCase().contains("eisenschuhe")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.boots.iron")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setBoots(CosmeticItemManager.ironBoots(true));
                        lP.sendMessage("Du trägst nun den §aEisenschuhe§8.");
                        p.closeInventory();
                    }
                } else if(itemName.toLowerCase().contains("goldschuhe")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.boots.gold")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setBoots(CosmeticItemManager.goldBoots(true));
                        lP.sendMessage("Du trägst nun den §aGoldschuhe§8.");
                        p.closeInventory();
                    }
                } else if(itemName.toLowerCase().contains("diamantschuhe")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.boots.diamond")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setBoots(CosmeticItemManager.diamondBoots(true));
                        lP.sendMessage("Du trägst nun den §aDiamantschuhe§8.");
                        p.closeInventory();
                    }
                } else if(itemName.toLowerCase().contains("netheritschuhe")) {
                    e.setCancelled(true);
                    if(!p.hasPermission("komvilcraft.cosmetics.boots.netherite")) {
                        lP.sendErrorMessage(LobbyAPI.noPerms);
                    } else {
                        p.getInventory().setBoots(CosmeticItemManager.netheriteBoots(true));
                        lP.sendMessage("Du trägst nun den §aNetheritschuhe§8.");
                        p.closeInventory();
                    }
                } else if(itemName.equals(CosmeticItemManager.removeHat.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    lP.sendMessage("Du trägst nun keinen Hut mehr§8.");
                    ItemStack AIR = new ItemStack(Material.AIR);
                    p.getInventory().setHelmet(AIR);
                } else if(itemName.equals(CosmeticItemManager.removeChest.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    lP.sendMessage("Du trägst nun kein Oberteil mehr§8.");
                    ItemStack AIR = new ItemStack(Material.AIR);
                    p.getInventory().setChestplate(AIR);
                } else if(itemName.equals(CosmeticItemManager.removeLeggings.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    lP.sendMessage("Du trägst nun keine Hose mehr§8.");
                    ItemStack AIR = new ItemStack(Material.AIR);
                    p.getInventory().setLeggings(AIR);
                } else if(itemName.equals(CosmeticItemManager.removeBoots.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    lP.sendMessage("Du trägst nun keine Schuhe mehr§8.");
                    ItemStack AIR = new ItemStack(Material.AIR);
                    p.getInventory().setBoots(AIR);
                }
            }
        }
    }
}