package com.neptunedevelopmentteam.neptunelib.core.datagen.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.RuleTestType;
import net.minecraft.util.math.random.Random;

public class NeptuneBlockMatchRuleTest extends RuleTest {
    private Block block;

    public NeptuneBlockMatchRuleTest(Block block) {
        this.block = block;
    }
    @Override
    public boolean test(BlockState state, Random random) {
        return this.block.equals(state.getBlock());
    }

    @Override
    protected RuleTestType<?> getType() {
        return RuleTestType.BLOCK_MATCH;
    }
}
