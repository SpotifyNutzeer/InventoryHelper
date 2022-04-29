package dev.spotifynutzer.manager;

import org.bukkit.Color;

/**
 * Contains all default colors required for inventories and texts.
 */
public class ColorManager {

  private final Color primaryColor;
  private final Color secondaryColor;
  private final Color warningColor;
  private final Color okColor;
  private final Color errorColor;

  /**
   * @param primaryColor   Color for all texts
   * @param secondaryColor Color for all decorative symbols
   * @param warningColor   Color for warning messages
   * @param okColor        Color for successful messages
   * @param errorColor     Color for error messages
   */
  public ColorManager(int primaryColor, int secondaryColor,
      int warningColor, int okColor, int errorColor) {

    this.primaryColor = Color.fromRGB(primaryColor);
    this.secondaryColor = Color.fromRGB(secondaryColor);
    this.warningColor = Color.fromRGB(warningColor);
    this.okColor = Color.fromRGB(okColor);
    this.errorColor = Color.fromRGB(errorColor);

  }

  /**
   * @param primaryColor   Color for all texts
   * @param secondaryColor Color for all decorative symbols
   * @param warningColor   Color for warning messages
   * @param okColor        Color for successful messages
   * @param errorColor     Color for error messages
   */
  public ColorManager(Color primaryColor, Color secondaryColor,
      Color warningColor, Color okColor, Color errorColor) {

    this.primaryColor = primaryColor;
    this.secondaryColor = secondaryColor;
    this.warningColor = warningColor;
    this.okColor = okColor;
    this.errorColor = errorColor;

  }

  /**
   * Color Manager with default values
   */
  public ColorManager() {

    this.primaryColor = Color.fromRGB(0x88C0D0);
    this.secondaryColor = Color.fromRGB(0xD8DEE9);
    this.warningColor = Color.fromRGB(0xD08770);
    this.okColor = Color.fromRGB(0xA3BE8C);
    this.errorColor = Color.fromRGB(0xBF616A);

  }

  public Color getPrimaryColor() {
    return primaryColor;
  }

  public Color getSecondaryColor() {
    return secondaryColor;
  }

  public Color getErrorColor() {
    return errorColor;
  }

  public Color getOkColor() {
    return okColor;
  }

  public Color getWarningColor() {
    return warningColor;
  }

  /**
   * @return The ColorManager Object deserialized to a String
   */
  @Override
  public String toString() {
    return "ColorManager{" +
        "primaryColor=" + primaryColor.asRGB() +
        ", secondaryColor=" + secondaryColor.asRGB() +
        ", warningColor=" + warningColor.asRGB() +
        ", okColor=" + okColor.asRGB() +
        ", errorColor=" + errorColor.asRGB() +
        '}';
  }
}
