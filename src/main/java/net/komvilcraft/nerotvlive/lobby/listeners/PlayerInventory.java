package net.komvilcraft.nerotvlive.lobby.listeners;

import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.apis.WarpAPI;
import net.komvilcraft.nerotvlive.lobby.cosmetics.CosmeticInventoryManager;
import net.komvilcraft.nerotvlive.lobby.managers.InventoryManager;
import net.komvilcraft.nerotvlive.lobby.managers.ItemManager;
import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInventory implements Listener {

    @EventHandler
    public void onInventory(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p) {
            LobbyUser lP = LobbyAPI.getLobbyUser(p.getUniqueId());
            if(!lP.isPVPMode()) {
                if (e.getInventory().getType().equals(InventoryType.PLAYER) || e.getInventory().getType().equals(InventoryType.CRAFTING)) {
                    if (e.getSlot() <= 40 || e.getSlot() >= 36) {
                        if (e.getSlot() == 36) {
                            p.getInventory().setBoots(null);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 100, 100);
                            return;
                        } else if (e.getSlot() == 37) {
                            p.getInventory().setLeggings(null);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 100, 100);
                            return;
                        } else if (e.getSlot() == 38) {
                            p.getInventory().setChestplate(null);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 100, 100);
                            return;
                        } else if (e.getSlot() == 39) {
                            p.getInventory().setHelmet(null);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 100, 100);
                            return;
                        } else if (e.getSlot() == 39) {
                            if (!lP.hasBuild()) {
                                p.getInventory().setItemInOffHand(null);
                                p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 100, 100);
                            }
                            return;
                        }
                    }
                }
            }
            if (!lP.hasBuild()) {
                e.setCancelled(true);
                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getItemMeta() != null) {
                        ItemStack item = e.getCurrentItem();
                        ItemMeta itemMeta = item.getItemMeta();
                        if (itemMeta.getDisplayName().toLowerCase().contains("spawn")) {
                            p.teleport(LobbyAPI.getSpawn());
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
                            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 100, 100);
                            return;
                        } else if(itemMeta.getDisplayName().contains("Profil")) {
                            InventoryManager.profileOptions(p);
                            return;
                        } else if(itemMeta.getDisplayName().toLowerCase().contains("freunde")) {
                            p.performCommand("friendsgui");
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
                            return;
                        } else if(itemMeta.getDisplayName().toLowerCase().contains("verstecken")) {
                            p.performCommand("hide");
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
                            return;
                        } else if (itemMeta.getDisplayName().equalsIgnoreCase(ItemManager.extras().getItemMeta().getDisplayName())) {
                            p.openInventory(InventoryManager.extras(p));
                            return;
                        } else if (itemMeta.getDisplayName().equalsIgnoreCase(ItemManager.backOP().getItemMeta().getDisplayName())) {
                            InventoryManager.profileOptions(p);
                            return;
                        } else if (itemMeta.getDisplayName().equalsIgnoreCase(ItemManager.backEX().getItemMeta().getDisplayName())) {
                            p.openInventory(InventoryManager.extras(p));
                            return;
                        } else if (itemMeta.getDisplayName().equalsIgnoreCase(ItemManager.backCS().getItemMeta().getDisplayName())) {
                            CosmeticInventoryManager.openCosmeticsInventory(p);
                            return;
                        } else if (item.getType().equals(Material.SHIELD)) {
                            e.setCancelled(true);
                            p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
                            return;
                        } else if (itemMeta.getDisplayName().toLowerCase().contains("kampfmodus")) {
                            p.closeInventory();
                            p.performCommand("pvp");
                            return;
                        } else if (itemMeta.getDisplayName().toLowerCase().contains("navigator")) {
                            p.openInventory(InventoryManager.navigator(p));
                            return;
                        } else if (itemMeta.getDisplayName().toLowerCase().contains("cosmetics")) {
                            CosmeticInventoryManager.openCosmeticsInventory(p);
                            return;
                        } else if (itemMeta.getDisplayName().toLowerCase().contains("jump n")) {
                            p.closeInventory();
                            p.performCommand("jumper");
                            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 100, 100);
                            return;
                        } else if (e.getClickedInventory().contains(ItemManager.bedwars())) {
                            int i = e.getSlot()+1;
                            if(WarpAPI.isWarpEnabled("gui"+i)) {
                                p.closeInventory();
                                p.teleport(WarpAPI.getWarp("gui"+i));
                                p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
                                p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 100, 100);
                                return;
                            }
                        }
                        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 100, 100);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onItemSwamp(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        LobbyUser lP = LobbyAPI.getLobbyUser(p.getUniqueId());
        if (!lP.hasBuild()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSlotChange(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        LobbyUser lP = LobbyAPI.getLobbyUser(p.getUniqueId());
        if (!lP.hasBuild()) {
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 100, 100);
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        for (int i = 0; i < e.getInventory().getSize(); i++) {
            if(e.getInventory().getItem(i)!=null) {
                if(e.getInventory().getItem(i).getItemMeta().getDisplayName().toLowerCase().contains("freundschaftsanfragen")) {
                    if(e.getInventory().getItem(36)==null) {
                        e.getInventory().setItem(36, ItemManager.placeholder());
                    }
                    if(e.getInventory().getItem(37)==null) {
                        e.getInventory().setItem(37, ItemManager.backOP());
                    }
                    if(e.getInventory().getItem(42)==null) {
                        e.getInventory().setItem(42, ItemManager.placeholder());
                    }
                    if(e.getInventory().getItem(44)==null) {
                        e.getInventory().setItem(44, ItemManager.placeholder());
                    }
                    return;
                } else if(e.getInventory().getItem(i).getItemMeta().getDisplayName().toLowerCase().contains("zeige nur freunde")) {
                    e.getInventory().setItem(13,ItemManager.backOP());
                    return;
                }
            }
        }
    }
}