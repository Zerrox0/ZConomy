package de.zeroxtv.zconomy.ZObjects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by ZeroxTV
 */
public class ZItem {
    private static ArrayList<ZItem> zItems = new ArrayList<>();
    public Double value;
    public short damage;
    private Material material;

    public Double offerValueMin;
    public Double offerValueMax;
    public Double requestValueMin;
    public Double requestValueMax;

    public ZItem(Material material, short damage, Double value, Double offerValueMin, Double offerValueMax, Double requestValueMin, Double requestValueMax) {
        this.value = value;
        this.material = material;
        this.damage = damage;
        this.offerValueMin = offerValueMin;
        this.offerValueMax = offerValueMax;
        this.requestValueMin = requestValueMin;
        this.requestValueMax = requestValueMax;
        zItems.add(this);
    }

    public ItemStack getItem() {
        ItemStack item;
        if (damage == -1) {
            item = new ItemStack(material, 1);
        } else {
            item = new ItemStack(material, 1, damage);
        }
        return item;
    }

    public static ArrayList<ZItem> getItems() {
        return zItems;
    }

    public boolean hasOffer() {
        return (offerValueMax != 0);
    }

    public boolean hasRequest() {
        return (requestValueMax != 0);
    }
}
