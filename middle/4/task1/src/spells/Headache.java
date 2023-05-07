package spells;

//
//Мигрень - наносит урон всем магам.
//

import character.GameCharacter;
import character.WizardFabric;
import constants.GameConstants;

import java.util.concurrent.ThreadLocalRandom;

public class Headache extends Spell {


    public Headache() {
        name = "Мигрень";
    }

    @Override
    public void cast(GameCharacter[] characters, GameCharacter wizard) {
        int damage = ThreadLocalRandom.current().nextInt(GameConstants.MIN_DAMAGE_POINT, GameConstants.MAX_DAMAGE_POINT);
        StringBuilder sb = new StringBuilder();

        for (GameCharacter character : characters) {
            if (character instanceof WizardFabric.Wizard) {
                character.damaged(damage);
                sb.append(" ").append(character.getName()).append(" здоровье: ").append(character.getHealth());
            }
        }
        System.out.println("У магов" + sb + " началась мигрень на " + damage + " урона.");
    }
}
