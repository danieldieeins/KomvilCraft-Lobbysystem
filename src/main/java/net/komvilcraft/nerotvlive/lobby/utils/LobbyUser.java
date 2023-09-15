package net.komvilcraft.nerotvlive.lobby.utils;

import net.komvilcraft.nerotvlive.core.paper.utils.user.User;
import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.managers.ItemManager;
import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;
import java.util.UUID;

public class LobbyUser extends User {

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private boolean hasBuild;
    private boolean pvpMode;

    public LobbyUser(UUID uuid) {
        super(uuid);
        hasBuild = false;
        pvpMode = false;
        if(getPlayer()!=null) {
            if(getPlayer().getInventory().getHelmet()!=null) {
                helmet = getPlayer().getInventory().getHelmet();
            }
            if(getPlayer().getInventory().getChestplate()!=null) {
                chestplate = getPlayer().getInventory().getChestplate();
            }
            if(getPlayer().getInventory().getLeggings()!=null) {
                leggings = getPlayer().getInventory().getLeggings();
            }
            if(getPlayer().getInventory().getBoots()!=null) {
                boots = getPlayer().getInventory().getBoots();
            }
        }
    }

    public String getName() {
        if(getPlayer()!=null) {
            return getPlayer().getName();
        } else {
            return null;
        }
    }

    public boolean hasBuild() {
        return this.hasBuild;
    }

    public void toggleBuild() {
        if(hasBuild) {
            hasBuild = false;
            if(getPlayer()!=null) {
                getPlayer().setGameMode(GameMode.ADVENTURE);
                ItemManager.giveItems(getPlayer());
            }
        } else {
            hasBuild = true;
            if(getPlayer()!=null) {
                getPlayer().setGameMode(GameMode.CREATIVE);
                getPlayer().getInventory().clear();
            }
        }
    }

    public boolean isPVPMode() {
        return this.pvpMode;
    }

    public void setPVPMode(boolean bool) {
        this.pvpMode = bool;
        if (getPlayer() != null) {
            if (pvpMode) {
                if (getPlayer().getInventory().getHelmet() != null) {
                    helmet = getPlayer().getInventory().getHelmet();
                }
                if (getPlayer().getInventory().getChestplate() != null) {
                    chestplate = getPlayer().getInventory().getChestplate();
                }
                if (getPlayer().getInventory().getLeggings() != null) {
                    leggings = getPlayer().getInventory().getLeggings();
                }
                if (getPlayer().getInventory().getBoots() != null) {
                    boots = getPlayer().getInventory().getBoots();
                }
                sendMessage("§7Du bist nun im §aPVP§8-§aMode§8, §7um ihn zu verlassen mache §f/pvp§8!");
            } else {
                getPlayer().getInventory().setHelmet(helmet);
                getPlayer().getInventory().setChestplate(chestplate);
                getPlayer().getInventory().setLeggings(leggings);
                getPlayer().getInventory().setBoots(boots);
                helmet = null;
                chestplate = null;
                leggings = null;
                boots = null;
            }
            ItemManager.giveItems(getPlayer());
        }
    }

    public void togglePVPMode() {
        setPVPMode(!pvpMode);
    }

    public void destroy() {
        hasBuild = false;
        pvpMode = false;
        LobbyAPI.onlinePlayers.remove(getUUID());
        disconnect();
        System.gc();
    }

    public void destroy(String reason) {
        hasBuild = false;
        pvpMode = false;
        LobbyAPI.onlinePlayers.remove(getUUID());
        disconnect(reason);
        System.gc();
    }
}