package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.datagen.worldgen.NeptuneOre;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneOreRegistrationType;
import net.minecraft.util.Identifier;

public class TestOres implements NeptuneOreRegistrationType {
    public static final NeptuneOre TEST_ORE = NeptuneOre.createStoneOverworldOre(
            Identifier.of("neptunetest", "test_ore"), // Ore Identifier
            TestBlocks.TEST_ORE_BLOCK, // Ore Block
            50, // The highest height the ore can generate at
            -50, // The lowest height the ore can generate at
            3, // The vein size
            2, // Veins per chunk
            0.5f // Chance that on generation that if the block is exposed to air that it'll not generate
    );

    public static final NeptuneOre DEEPSLATE_TEST_ORE = NeptuneOre.createDeepslateOverworldOre(
            Identifier.of("neptunetest", "deepslate_test_ore"),
            TestBlocks.TEST_ORE_BLOCK,
            50,
            -50,
            3,
            2,
            0.5f
    );
}
