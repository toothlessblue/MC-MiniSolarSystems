package net.fabricmc.MiniSolarSystems;

import net.minecraft.item.ItemStack;

import java.util.Stack;

public class Planet {
    private float temperature;
    private float radius;
    private float waterLevel;
    private Ecosystem ecosystem;

    private Stack<ItemStack> itemStackStack = new Stack<ItemStack>();

    public Planet() {
        this.ecosystem = new Ecosystem(this);
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
