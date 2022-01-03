package net.fabricmc.MiniSolarSystems;

import net.minecraft.item.ItemStack;

import java.util.Stack;

public class Planet {
    private float temperature;
    private float radius;
    private float waterLevel;

    private Stack<ItemStack> itemStackStack = new Stack<ItemStack>();

    private StarBlockEntity star;

    public PlanetRenderer renderer;

    public Planet(StarBlockEntity star) {
        //this.renderer = new PlanetRenderer(); // TODO Only if client!
        this.star = star;
    }

    public void render() {

    }

    public ItemStack takeItem() {
        return this.itemStackStack.pop();
    }

    public void putItem(ItemStack stack) {
        this.itemStackStack.push(stack);
    }
}
