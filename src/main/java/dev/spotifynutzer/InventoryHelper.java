package dev.spotifynutzer;

import dev.spotifynutzer.manager.ColorManager;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryHelper extends JavaPlugin {

    // Declaring the instance
    private static InventoryHelper instance;

    // The main color manager
    private ColorManager colorManager;

    @Override
    public void onLoad() {
        // Setting the instance
        instance = this;


    }

    @Override
    public void onEnable() {


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

    public ColorManager getColorManager() {
        return colorManager;
    }

    public void setColorManager(ColorManager colorManager) {
        this.colorManager = colorManager;
    }
}
