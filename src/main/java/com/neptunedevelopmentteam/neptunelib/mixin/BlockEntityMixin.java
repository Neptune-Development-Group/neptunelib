package com.neptunedevelopmentteam.neptunelib.mixin;

import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataSource;
import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin implements NeptuneDataSource {


    @Shadow protected abstract void addComponents(ComponentMap.Builder builder);

    @Unique
    @Override
    public <A> A neptunelib$getData(NeptuneDataType<A> type) {
        ComponentMap components = ((BlockEntity) (Object) this).getComponents();
        if (components.contains(type.getDataComponentType())) {
            return (A) components.get(type.getDataComponentType());
        }
        return NeptuneDataSource.super.neptunelib$getData(type);
    }

    @Unique
    @Override
    public <A> void neptunelib$setData(NeptuneDataType<A> type, A data) {
        ComponentMap components = ((BlockEntity) (Object) this).getComponents();
        ComponentMap.Builder builder = ComponentMap.builder();
        builder.add(type.getDataComponentType(), data);
        builder.addAll(components);
        this.addComponents(builder);
    }
}
