package spells;

//
//Исцеление - добавляет очков здоровья магу, произнесшему заклинания.
//

import character.GameCharacter;
import constants.GameConstants;

import java.util.concurrent.ThreadLocalRandom;

public class Healthy extends Spell {

    public Healthy() {
        name = "Исцеление";
    }

    @Override
    public void cast(GameCharacter[] characters, GameCharacter wizard) {
        int healthPoints = ThreadLocalRandom.current().nextInt(GameConstants.MIN_HEALTHY_POINT, GameConstants.MAX_HEALTHY_POINT);

        wizard.healthy(healthPoints);
        System.out.printf("Маг %s исцелен на %d. Теперь у него %d здоровья. \n", wizard.getName(), healthPoints, wizard.getHealth());
    }
}
