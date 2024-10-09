package moe.wolfgirl.kubejs_roots;

import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeComponentFactoryRegistry;
import dev.latvian.mods.kubejs.registry.BuilderTypeRegistry;
import dev.latvian.mods.kubejs.script.ScriptManager;
import dev.latvian.mods.kubejs.script.ScriptType;
import elucent.rootsclassic.component.ComponentBaseRegistry;
import elucent.rootsclassic.mutation.MutagenManager;
import elucent.rootsclassic.ritual.RitualBaseRegistry;
import moe.wolfgirl.kubejs_roots.custom.ComponentJS;
import moe.wolfgirl.kubejs_roots.custom.RitualJS;
import moe.wolfgirl.kubejs_roots.events.MutagenEvent;
import moe.wolfgirl.kubejs_roots.events.RootsEvents;
import moe.wolfgirl.kubejs_roots.recipes.ComponentComponent;
import moe.wolfgirl.kubejs_roots.recipes.RitualComponent;
import moe.wolfgirl.kubejs_roots.recipes.TagCompoundComponent;

public class KubeJSRootsPlugin implements KubeJSPlugin {
    @Override
    public void registerBuilderTypes(BuilderTypeRegistry registry) {
        registry.of(RitualBaseRegistry.RITUAL_KEY, reg -> {
            reg.addDefault(RitualJS.Builder.class, RitualJS.Builder::new);
        });

        registry.of(ComponentBaseRegistry.COMPONENTS_KEY, reg -> {
            reg.addDefault(ComponentJS.Builder.class, ComponentJS.Builder::new);
        });
    }

    @Override
    public void registerRecipeComponents(RecipeComponentFactoryRegistry registry) {
        registry.register(RitualComponent.RITUAL);
        registry.register(ComponentComponent.COMPONENT);
        registry.register(TagCompoundComponent.COMPOUND_TAG);
    }

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(RootsEvents.GROUP);
    }

    @Override
    public void afterScriptsLoaded(ScriptManager manager) {
        if (manager.scriptType != ScriptType.SERVER) return;

        MutagenManager.recipes.clear();
        MutagenManager.reload();

        RootsEvents.MUTAGEN.post(new MutagenEvent());
    }
}
