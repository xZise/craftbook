package com.sk89q.craftbook.ic;

import org.bukkit.block.Sign;

import com.sk89q.craftbook.util.BlockWorldVector;

public abstract class AbstractConstantChipState extends AbstractChipState {

    private final int inputCount;
    private final int outputCount;
    
    public AbstractConstantChipState(BlockWorldVector source, Sign sign, int inputCount, int outputCount) {
        super(source, sign);
        this.inputCount = inputCount;
        this.outputCount = outputCount;
    }

    @Override
    public boolean getInput(int inputIndex) {
        return get(inputIndex);
    }

    @Override
    public boolean getOutput(int outputIndex) {
        return get(outputIndex + this.inputCount);
    }

    @Override
    public void setOutput(int outputIndex, boolean value) {
        set(outputIndex + this.inputCount, value);
    }

    @Override
    public int getInputCount() {
        return this.inputCount;
    }

    @Override
    public int getOutputCount() {
        return this.outputCount;
    }
    
}
