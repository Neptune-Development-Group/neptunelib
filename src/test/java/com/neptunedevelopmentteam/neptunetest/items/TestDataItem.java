package com.neptunedevelopmentteam.neptunetest.items;

import com.neptunedevelopmentteam.neptunelib.interfaces.NeptuneItemDataImpl;
import com.neptunedevelopmentteam.neptunetest.registration.TestDataTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class TestDataItem extends Item implements NeptuneItemDataImpl {
    public TestDataItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        addClickedOnCount(user.getStackInHand(hand), 1);
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        Text text = Text.literal("Clicked on count: " + getClickedOnCount(stack));
        tooltip.add(text);
    }

    public void addClickedOnCount(ItemStack stack, int count) {
        setClickedOnCount(stack, getClickedOnCount(stack) + count);
    }

    public void setClickedOnCount(ItemStack stack, int count) {
        this.setStackData(stack, TestDataTypes.INTEGER_DATA_TYPE, count);
    }

    public int getClickedOnCount(ItemStack stack) {
        return this.getOrCreateStackData(stack, TestDataTypes.INTEGER_DATA_TYPE); // Default is set on registration, but you can also define a default with one of the available functions
    }
}
