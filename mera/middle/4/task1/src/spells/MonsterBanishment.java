package spells;

//
//Изгнание монстров - наносит урон всем монстрам.
//

import character.GameCharacter;
import character.MonsterFabric;
import constants.GameConstants;

import java.util.concurrent.ThreadLocalRandom;

public class MonsterBanishment extends Spell {

    public MonsterBanishment() {
        name = "Изгнание монстров";
    }

    @Override
    public void cast(GameCharacter[] characters, GameCharacter wizard) {
        int damage = ThreadLocalRandom.current().nextInt(GameConstants.MIN_DAMAGE_POINT, GameConstants.MAX_DAMAGE_POINT);

        for (GameCharacter character : characters) {
            if (character instanceof MonsterFabric.Monster) {
                character.damaged(damage);
            }
        }
        System.out.println("Monster banishment");
    }
}
