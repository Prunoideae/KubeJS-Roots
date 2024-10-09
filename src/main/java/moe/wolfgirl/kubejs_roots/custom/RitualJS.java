package moe.wolfgirl.kubejs_roots.custom;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import elucent.rootsclassic.ritual.RitualEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;

public class RitualJS extends RitualEffect {
    private final Builder builder;

    public RitualJS(Builder builder) {
        this.builder = builder;
    }

    @Override
    public void doEffect(Level level, BlockPos blockPos, Container container, List<ItemStack> list, CompoundTag compoundTag) {
        this.builder.ritualEffect.accept(new RitualContext(level, blockPos, container, list, compoundTag));
    }

    public static class RitualContext {
        public final Level level;
        public final BlockPos pos;
        public final Container inventory;
        public final List<ItemStack> incenses;
        public final CompoundTag config;

        public RitualContext(Level level, BlockPos pos, Container inventory, List<ItemStack> incenses, CompoundTag config) {
            this.level = level;
            this.pos = pos;
            this.inventory = inventory;
            this.incenses = incenses;
            this.config = config;
        }
    }

    public static class Builder extends BuilderBase<RitualEffect> {
        private Consumer<RitualContext> ritualEffect = ctx -> {
        };

        public Builder(ResourceLocation id) {
            super(id);
        }

        public Builder ritualEffect(Consumer<RitualContext> ritualEffect) {
            this.ritualEffect = ritualEffect;
            return this;
        }

        @Override
        public RitualEffect createObject() {
            return new RitualJS(this);
        }
    }
}
