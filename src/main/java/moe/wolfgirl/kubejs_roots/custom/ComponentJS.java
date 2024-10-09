package moe.wolfgirl.kubejs_roots.custom;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import elucent.rootsclassic.component.ComponentBase;
import elucent.rootsclassic.component.EnumCastType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class ComponentJS extends ComponentBase {
    private final Builder builder;

    protected ComponentJS(Builder builder) {
        super(builder.sourceItem, builder.manaCost);
        this.builder = builder;
    }

    @Override
    public void doEffect(Level level, Entity caster, EnumCastType type, double x, double y, double z, double potency, double duration, double size) {
        this.builder.spellEffect.accept(new EffectContext(level, caster, type, x, y, z, potency, duration, size));
    }

    public static class EffectContext {
        public final Level level;
        public final Entity caster;
        public final EnumCastType type;
        public final double x;
        public final double y;
        public final double z;
        public final double potency;
        public final double duration;
        public final double size;

        private EffectContext(Level level, Entity caster, EnumCastType type, double x, double y, double z, double potency, double duration, double size) {
            this.level = level;
            this.caster = caster;
            this.type = type;
            this.x = x;
            this.y = y;
            this.z = z;
            this.potency = potency;
            this.duration = duration;
            this.size = size;
        }
    }

    public static class Builder extends BuilderBase<ComponentBase> {
        private ItemStack sourceItem = ItemStack.EMPTY;
        private int manaCost = 8;
        private Consumer<EffectContext> spellEffect = ctx -> {
        };

        public Builder(ResourceLocation id) {
            super(id);
        }

        public Builder sourceItem(ItemStack sourceItem) {
            this.sourceItem = sourceItem;
            return this;
        }

        public Builder manaCost(int manaCost) {
            this.manaCost = manaCost;
            return this;
        }

        public Builder spellEffect(Consumer<EffectContext> spellEffect) {
            this.spellEffect = spellEffect;
            return this;
        }

        @Override
        public ComponentBase createObject() {
            return new ComponentJS(this);
        }
    }
}
