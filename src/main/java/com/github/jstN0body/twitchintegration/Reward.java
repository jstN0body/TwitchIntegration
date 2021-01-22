package com.github.jstN0body.twitchintegration;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public enum Reward {
    GIVE_FOOD,
    SPAWN_CREEPER,
    HALF_HEALTH,
    SPAWN_TNT,
    GIVE_DIAMONDS,
    SPAWN_WITHER,
    CLEAR_INVENTORY,
    NOTHING;

    /**
     * Get a Reward enum based on an Object.
     * @param action object that should be an Integer or String
     * @return Action that correlates to the Integer or String based on the bit value and channel point names found in config.yml
     */
    public static Reward get(Object action) {
        if (action instanceof String) {

            String name = (String) action;
            if (name.equalsIgnoreCase(Plugin.config.getString("rewards.give_food.channel_points"))) {
                return Reward.GIVE_FOOD;
            } else if (name.equalsIgnoreCase(Plugin.config.getString("rewards.spawn_creeper.channel_points"))) {
                return Reward.SPAWN_CREEPER;
            } else if (name.equalsIgnoreCase(Plugin.config.getString("rewards.half_health.channel_points"))) {
                return Reward.HALF_HEALTH;
            } else if (name.equalsIgnoreCase(Plugin.config.getString("rewards.spawn_tnt.channel_points"))) {
                return Reward.SPAWN_TNT;
            } else if (name.equalsIgnoreCase(Plugin.config.getString("rewards.give_diamonds.channel_points"))) {
                return Reward.GIVE_DIAMONDS;
            } else if (name.equalsIgnoreCase(Plugin.config.getString("rewards.spawn_wither.channel_points"))) {
                return Reward.SPAWN_WITHER;
            } else if (name.equalsIgnoreCase(Plugin.config.getString("rewards.clear_inventory.channel_points"))) {
                return Reward.CLEAR_INVENTORY;
            }

        } else if (action instanceof Integer) {

            Integer amount = (Integer) action;
            if (amount.equals(Plugin.config.getInt("rewards.give_food.bits"))) {
                return Reward.GIVE_FOOD;
            } else if (amount.equals(Plugin.config.getInt("rewards.spawn_creeper.bits"))) {
                return Reward.SPAWN_CREEPER;
            } else if (amount.equals(Plugin.config.getInt("rewards.half_health.bits"))) {
                return Reward.HALF_HEALTH;
            } else if (amount.equals(Plugin.config.getInt("rewards.spawn_tnt.bits"))) {
                return Reward.SPAWN_TNT;
            } else if (amount.equals(Plugin.config.getInt("rewards.give_diamonds.bits"))) {
                return Reward.GIVE_DIAMONDS;
            } else if (amount.equals(Plugin.config.getInt("rewards.spawn_wither.bits"))) {
                return Reward.SPAWN_WITHER;
            } else if (amount.equals(Plugin.config.getInt("rewards.clear_inventory.bits"))) {
                return Reward.CLEAR_INVENTORY;
            }

        }
        return Reward.NOTHING;
    }

    public static void execute(Reward reward, Plugin plugin) {
        switch (reward) {
            case GIVE_FOOD:
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            ItemStack food = new ItemStack(Material.COOKED_BEEF);
                            food.setAmount(Plugin.config.getInt("rewards.give_food.amount"));
                            player.getInventory().addItem(food);
                        }
                    }
                }.runTask(plugin);
                break;
            case SPAWN_TNT:
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.PRIMED_TNT);
                        }
                    }
                }.runTask(plugin);
                break;
            case HALF_HEALTH:
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.damage(player.getHealth() / 2);
                        }
                    }
                }.runTask(plugin);
                break;
            case SPAWN_WITHER:
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        int index = new Random().nextInt(Bukkit.getOnlinePlayers().size());
                        Object[] players = Bukkit.getOnlinePlayers().toArray();
                        Player player = (Player) players[index];
                        player.getWorld().spawnEntity(player.getLocation(), EntityType.WITHER);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.teleport(player.getLocation());
                        }
                    }
                }.runTask(plugin);
                break;
            case GIVE_DIAMONDS:
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            ItemStack food = new ItemStack(Material.DIAMOND);
                            food.setAmount(Plugin.config.getInt("rewards.give_diamonds.amount"));
                            player.getInventory().addItem(food);
                        }
                    }
                }.runTask(plugin);
                break;
            case SPAWN_CREEPER:
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                        }
                    }
                }.runTask(plugin);
                break;
            case CLEAR_INVENTORY:
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.getInventory().clear();
                }
        }
    }
}
