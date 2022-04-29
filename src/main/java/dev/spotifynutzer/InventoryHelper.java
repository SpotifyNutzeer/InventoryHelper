package dev.spotifynutzer;

import dev.spotifynutzer.inventory.Inventory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class InventoryHelper extends JavaPlugin {

  // Declaring the instance
  private static InventoryHelper instance;

  private final List<Inventory> inventories = new ArrayList<>();

  @Override
  public void onLoad() {
    // Setting the instance
    instance = this;
  }

  @Override
  public void onDisable() {

  }

  /**
   * @return The main instance of this class
   */
  public static InventoryHelper getInstance() {
    return instance;
  }

  public List<Inventory> getInventories() {
    return inventories;
  }

  public void addInventory(Inventory inventory) {
    inventories.add(inventory);

    PluginManager pluginManager = Bukkit.getPluginManager();
    pluginManager.registerEvents(inventory, this);
  }
}
