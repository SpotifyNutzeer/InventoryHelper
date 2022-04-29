package dev.spotifynutzer.inventory;

import dev.spotifynutzer.InventoryHelper;
import dev.spotifynutzer.manager.ColorManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.function.Consumer;

public class Inventory implements Listener {

  private final String name;
  private final InventoryType inventoryType;
  private final int slots;
  private final ColorManager colorManager;
  private final org.bukkit.inventory.Inventory inventory;
  private final HashMap<Integer, Consumer<Player>> actions = new HashMap<>();
  private final boolean cancelClick;

  public Inventory(String name, InventoryType inventoryType, int slots, boolean cancelClick) {
    this.name = name;
    this.inventoryType = inventoryType;
    this.slots = slots;
    this.colorManager = InventoryHelper.getInstance().getColorManager();
    this.cancelClick = cancelClick;
    this.inventory = Bukkit.createInventory(null, inventoryType, colorManager.getPrimaryColor() + name);
  }

  public Inventory(String name, InventoryType inventoryType, int slots, ColorManager colorManager,
      boolean cancelClick) {
    this.name = name;
    this.inventoryType = inventoryType;
    this.slots = slots;
    this.colorManager = colorManager;
    this.cancelClick = cancelClick;
    this.inventory = Bukkit.createInventory(null, inventoryType, colorManager.getPrimaryColor() + name);
  }

  public Inventory(String name, InventoryType inventoryType, int slots) {
    this.name = name;
    this.inventoryType = inventoryType;
    this.slots = slots;
    this.colorManager = InventoryHelper.getInstance().getColorManager();
    this.cancelClick = true;
    this.inventory = Bukkit.createInventory(null, inventoryType, colorManager.getPrimaryColor() + name);
  }

  public Inventory(String name, InventoryType inventoryType, int slots, ColorManager colorManager) {
    this.name = name;
    this.inventoryType = inventoryType;
    this.slots = slots;
    this.colorManager = colorManager;
    this.cancelClick = true;
    this.inventory = Bukkit.createInventory(null, inventoryType, colorManager.getPrimaryColor() + name);
  }

  public void addItemStack(Material material, String name, int amount, int slot) {
    addItemStack(material, name, colorManager.getPrimaryColor(), amount, slot);
  }

  public void addItemStack(Material material, String name, Color textColor, int amount, int slot,
      Consumer<Player> action) {
    addItemStack(material, name, textColor, amount, slot);

    actions.put(slot, action);
  }

  public void addItemStack(Material material, String name, int amount, int slot, Consumer<Player> action) {
    addItemStack(material, name, amount, slot);

    actions.put(slot, action);
  }

  public void addItemStack(Material material, String name, Color textColor, int amount, int slot) {
    ItemStack itemStack = new ItemStack(material);
    ItemMeta itemMeta = itemStack.getItemMeta();

    assert itemMeta != null;

    itemMeta.setDisplayName(textColor + name);
    itemStack.setItemMeta(itemMeta);
    itemStack.setAmount(amount);

    inventory.setItem(slot, itemStack);
  }

  @EventHandler
  public void onClick(InventoryClickEvent event) {

      if (event.getCurrentItem() == null) {
          return;
      }
      if (event.getCurrentItem().getItemMeta() == null) {
          return;
      }
      if (!actions.containsKey(event.getSlot())) {
          return;
      }
      if (!(event.getWhoClicked() instanceof Player)) {
          return;
      }

    actions.get(event.getSlot()).accept((Player) event.getWhoClicked());

    event.setCancelled(true);
  }

  public String getName() {
    return name;
  }

  public InventoryType getInventoryType() {
    return inventoryType;
  }

  public int getSlots() {
    return slots;
  }

  public ColorManager getColorManager() {
    return colorManager;
  }

  public org.bukkit.inventory.Inventory getInventory() {
    return inventory;
  }

  public HashMap<Integer, Consumer<Player>> getActions() {
    return actions;
  }
}
