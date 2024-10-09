package moe.wolfgirl.kubejs_roots.events;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.script.ScriptType;

public interface RootsEvents {
    EventGroup GROUP = EventGroup.of("RootsEvents");

    EventHandler MUTAGEN = GROUP.add("mutagen", ScriptType.SERVER, () -> MutagenEvent.class);
}
