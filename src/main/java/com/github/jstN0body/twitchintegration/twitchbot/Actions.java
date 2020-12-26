package com.github.jstN0body.twitchintegration.twitchbot;

import com.github.jstN0body.twitchintegration.Main;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Actions {

    private final Main plugin;

    public Actions(Main plugin) {this.plugin = plugin;}

    public void spawnMob(EntityType e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    Player player = (Player) object;
                    player.getWorld().spawnEntity(player.getLocation(), e);
                }
            }
        }.runTask(plugin);
    }

    public void halfHealth() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    Player player = (Player) object;
                    player.damage(player.getHealth() / 2);
                }
            }
        }.runTask(plugin);
    }

    public void giveDiamonds(FileConfiguration config) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    Player player = (Player) object;
                    ItemStack itemStack = new ItemStack(Material.DIAMOND);
                    for (int i = 0 ; i < config.getInt("amountofdiamonds") ; i++) {
                        player.getInventory().addItem(itemStack);
                    }
                }
            }
        }.runTask(plugin);
    }

    public void randomTp(FileConfiguration config) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < Bukkit.getOnlinePlayers().toArray().length ; i++) {
                    Object[] players = Bukkit.getOnlinePlayers().toArray();
                    if (i == 0) {
                        Bukkit.dispatchCommand(((Player) players[i]), "function cavespread:spread");
                    } else {
                        ((Player) players[i]).teleport(((Player) players[0]).getLocation());
                    }
                }
            }
        }.runTask(plugin);
    }

    public void weather(FileConfiguration config) {
        for (Object object : Bukkit.getOnlinePlayers().toArray()) {
            Player player = (Player) object;
            player.getWorld().setStorm(true);
            player.getWorld().setWeatherDuration(config.getInt("weathertime"));
        }
    }

    public void switchDimensions(FileConfiguration config) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    World nether = Bukkit.getWorld(config.getString("netherworld"));
                    World overworld = Bukkit.getWorld(config.getString("world"));
                    if (player.getWorld() == overworld) {
                        player.teleport(new Location(nether, 0, -2, 0));
                        randomTp(config);
                    } else if (inNether(player, config)) {
                        try {
                            player.teleport(player.getBedSpawnLocation());
                        } catch (Exception ignored) {
                            player.teleport(overworld.getSpawnLocation());
                        }
                    }
                }
            }
        }.runTask(plugin);
    }

    public void clearInventory() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    ((Player) object).getInventory().clear();
                }
            }
        }.runTask(plugin);
    }

    public void giveChickenNugget() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    ItemStack chickenNugget = new ItemStack(Material.GOLD_NUGGET);
                    ItemMeta meta = chickenNugget.getItemMeta();
                    meta.setDisplayName(ChatColor.YELLOW + "Chicken Nugget");
                    chickenNugget.setItemMeta(meta);
                    ((Player) object).getInventory().addItem(chickenNugget);
                }
            }
        }.runTask(plugin);
    }

    public void spawnTnt() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    ((Player) object).getWorld().spawnEntity(((Player) object).getEyeLocation(), EntityType.PRIMED_TNT);
                }
            }
        }.runTask(plugin);
    }

    public void giveItem(FileConfiguration config) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    Player player = (Player) object;
                    ItemStack log = new ItemStack(Material.OAK_LOG);
                    ItemStack food = new ItemStack(Material.COOKED_BEEF);
                    log.setAmount(config.getInt("foodamount"));
                    food.setAmount(4);
                    player.getInventory().addItem(log, food);
                }
            }
        }.runTask(plugin);
    }

    public void spawnWither() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                int index = (int) (Math.random() * ((players.length-1) + 1) + 0);
                Player player = (Player) players[index];
                player.getWorld().spawnEntity(player.getLocation(), EntityType.WITHER);
                for (Object object : players) {
                    Player p = (Player) object;
                    p.teleport(player.getLocation());
                }
            }
        }.runTask(plugin);
    }

    private boolean inNether(Player player, FileConfiguration config) {
        return player.getWorld() == Bukkit.getWorld(config.getString("netherworld"));
    }
}
