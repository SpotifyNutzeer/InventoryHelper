package dev.spotifynutzer.inventory;

import java.util.HashMap;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Inventory implements Listener {

  private final String name;
  private final InventoryType inventoryType;
  private final int slots;
  private final org.bukkit.inventory.Inventory inventory;
  private final HashMap<Integer, Consumer<Player>> actions = new HashMap<>();
  private final boolean cancelClick;


  public Inventory(String name, InventoryType inventoryType, int slots,
      boolean cancelClick) {
    this.name = name;
    this.inventoryType = inventoryType;
    this.slots = slots;
    this.cancelClick = cancelClick;
    this.inventory = Bukkit.createInventory(null, inventoryType, name);
  }

  public Inventory(String name, InventoryType inventoryType, int slots) {
    this.name = name;
    this.inventoryType = inventoryType;
    this.slots = slots;
    this.cancelClick = true;
    this.inventory = Bukkit.createInventory(null, inventoryType, name);
  }

  public void addItemStack(Material material, String name, int amount, int slot, Consumer<Player> action) {
    addItemStack(material, name, amount, slot);

    actions.put(slot, action);
  }

  public void addItemStack(Material material, String name, int amount, int slot) {
    ItemStack itemStack = new ItemStack(material);
    ItemMeta itemMeta = itemStack.getItemMeta();

    assert itemMeta != null;

    itemMeta.setDisplayName(name);
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

    if (!event.getView().getTitle().equals(name)) {
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

  public org.bukkit.inventory.Inventory getInventory() {
    return inventory;
  }

  public HashMap<Integer, Consumer<Player>> getActions() {
    return actions;
  }
}
