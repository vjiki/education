package spells;

//
//Огненное касание - наносит урон персонажу, стоящему на соседней с магом позиции.
//Если на соседних позициях персонажей нет - никому урон не наносится.
//

import character.GameCharacter;
import constants.GameConstants;

import java.util.concurrent.ThreadLocalRandom;

public class FireTouch extends Spell {

    public FireTouch() {
        name = "Огненное касание";
    }

    @Override
    public void cast(GameCharacter[] characters, GameCharacter wizard) {
        int damage = ThreadLocalRandom.current().nextInt(GameConstants.MIN_DAMAGE_POINT, GameConstants.MAX_DAMAGE_POINT);

        for (GameCharacter character : characters) {
            // TODO: do some logic about position here
            if (character.getPosition() == wizard.getPosition() - 1 || character.getPosition() == wizard.getPosition() + 1) {
                character.damaged(damage);
                System.out.printf("Маг на позиции %d сделал огненное касание %s на %d. Теперь у %s здоровье %d. \n",
                        wizard.getPosition(), character.getName(), damage, character.getName(), character.getHealth());
                break;
            }
        }
    }
}
