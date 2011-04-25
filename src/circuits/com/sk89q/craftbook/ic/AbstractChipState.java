package com.sk89q.craftbook.ic;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import com.sk89q.craftbook.bukkit.BukkitUtil;
import com.sk89q.craftbook.util.BlockWorldVector;

public abstract class AbstractChipState implements ChipState {

    protected final Sign sign;
    protected final BlockWorldVector source;

    public AbstractChipState(BlockWorldVector source, Sign sign) {
        this.sign = sign;
        this.source = source;
    }
    
    protected abstract Block getBlock(int pin);
    
    @Override
    public boolean get(int pin) {
        Block block = getBlock(pin);
        if (block != null) {
            return block.isBlockIndirectlyPowered();
        } else {
            return false;
        }
    }

    @Override
    public boolean getInput(int inputIndex) {
        //TODO: Leave here?
        return get(inputIndex);
    }

    @Override
    public void set(int pin, boolean value) {
        Block block = getBlock(pin);
        if (block != null) {
            ICUtil.setState(block, value);
        } else {
            return;
        }
    }

    @Override
    public boolean isTriggered(int pin) {
        Block block = getBlock(pin);
        if (block != null) {
            return BukkitUtil.toWorldVector(block).equals(source);
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid(int pin) {
        Block block = getBlock(pin);
        if (block != null) {
            return block.getType() == Material.REDSTONE_WIRE;
        } else {
            return false;
        }
    }
}
