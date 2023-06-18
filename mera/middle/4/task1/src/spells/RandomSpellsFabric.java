package spells;

import java.util.concurrent.ThreadLocalRandom;

import static constants.GameConstants.MAX_WIZARD_SPELLS_SIZE;
import static constants.GameConstants.MIN_WIZARD_SPELLS_SIZE;

public class RandomSpellsFabric extends SpellsFabric {

    @Override
    public Spell[] makeSpells() {
        Spell[] spells = new Spell[ThreadLocalRandom.current().nextInt(MIN_WIZARD_SPELLS_SIZE, MAX_WIZARD_SPELLS_SIZE+1)];
        for (int i = 0; i < spells.length; i++) {
            spells[i] = getRandomSpell();
        }
        return spells;
    }

    private Spell getRandomSpell () {
        return makeSpell(
                SpellType.values()[ThreadLocalRandom.current().nextInt(0, SpellType.values().length)]);
    }

    private Spell makeSpell(SpellType spellType) {
        return switch (spellType) {
            case FIRE_TOUCH -> new FireTouch();
            case HEADACHE -> new Headache();
            case HEALTHY -> new Healthy();
            case LIGHTNING -> new Lightning();
            case LIGHTNING_CHAIN -> new LightningChain();
            case MONSTER_BANISHMENT -> new MonsterBanishment();
            case WALL_OF_WIRE -> new WallOfFire();
        };
    }

}
