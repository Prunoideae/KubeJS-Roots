# KubeJS Roots

Adds support for Roots Classic in 1.21 including recipes, and custom rituals, spells, and mutations.

## Recipes

```javascript
ServerEvents.recipes(event => {
    let { rootsclassic } = event.recipes

    // Creates a ritual recipe for the grow ritual
    rootsclassic.ritual('rootsclassic:grow',
        ['bone_meal', 'apple'], // Input ingredients
        ['bone', 'porkchop'],   // Incense items to burn
        0, '#aabbcc'              // Altar level and color
    )

    // Creates a mortar recipe
    rootsclassic.component(
        'minecraft:tnt',        // Output item
        ['gunpowder', 'gunpowder', 
        'gunpowder', 'gunpowder'],// Input ingredients
    )

    // and a spell powder recipe
    rootsclassic.component(
        'rootsclassic:spell_powder',  // Spell powder item to record effect data
        ["oak_button", "oak_button", 
        "oak_button", "oak_button"],// Input ingredients
        'kubejs:boom',                  // The spell to apply to the powder
        true                            // Needs to be true for spell recipes
    )
})
```

## Custom Ritual

```javascript
StartupEvents.registry('rootsclassic:ritual', event => {
    event.create('big_boom')
        // The effect that will be run when the ritual is completed
        .ritualEffect(context => {
            let { level, pos } = context;
            level.createExplosion(pos.x, pos.y, pos.z)
                .strength(8)
                .explosionMode('tnt')
                .causesFire(true)
                .explode()
        })
})
```

When you perform a ritual in the altar, it runs with the following steps:

1. Started count down for the progress time (Think it as some kind of CD or process time)
2. Run the ritual effect

## Custom Spell

```javascript
StartupEvents.registry('rootsclassic:component', event => {
    event.create('boom')
        .sourceItem('acacia_boat')
        .manaCost(8)                // 2 leaves in the bar
        .spellEffect(context => {   // The effect that will be run when the spell is cast
            let { caster, level } = context
            level.createExplosion(caster.x, caster.y, caster.z)
                .strength(4)
                .causesFire(true)
                .explode()
        })
})
```

## Custom Mutation

```javascript
RootsEvents.mutagen(event => {
    // Mutating a dandelion into a TNT block
    event.add('kubejs:test', "minecraft:tnt", "minecraft:dandelion", builder => {
        // Extra items to be thrown near the dandelion
        builder.inputs('acacia_button')
        // The effect that will be run if the mutation is successful
        builder.onCrafted((block, player) => player.potionEffects.add('regeneration', 1000, 0))
        // Additional conditions to be met for the mutation to be successful
        builder.matches((items, block, player) => player.xpLevel > 10)
    })
})
```
