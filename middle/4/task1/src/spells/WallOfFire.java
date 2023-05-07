package spells;

//
//Стена огня - наносит урон всем персонажам на четных позициях.
//

import character.GameCharacter;
import constants.GameConstants;

import java.util.concurrent.ThreadLocalRandom;

public class WallOfFire extends Spell {

    public WallOfFire() {
        name = "Стена огня";
    }

    @Override
    public void cast(GameCharacter[] characters, GameCharacter wizard) {
        int damage = ThreadLocalRandom.current().nextInt(GameConstants.MIN_DAMAGE_POINT, GameConstants.MAX_DAMAGE_POINT);

        StringBuilder sb = new StringBuilder();
        for (GameCharacter character : characters) {
            if(character != null) {
                if (character.getPosition() != 0 && (character.getPosition() % 2) == 0) {
                    character.damaged(damage);
                    sb.append(" ").append(character.getName()).append(",");
                }
            }
        }
        System.out.printf("Стена огня ударяет по персонажам %s на чётных позиция. Урон: %d\n",
                sb, damage);

    }
}
