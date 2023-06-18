package spells;

//
//Молния - наносит урон любому персонажу.
//

import character.GameCharacter;
import constants.GameConstants;

import java.util.concurrent.ThreadLocalRandom;

public class Lightning extends Spell {

    public Lightning() {
        name = "Молния";
    }

    @Override
    public void cast(GameCharacter[] characters, GameCharacter wizard) {
        int damage = ThreadLocalRandom.current().nextInt(GameConstants.MIN_DAMAGE_POINT, GameConstants.MAX_DAMAGE_POINT);

        GameCharacter character = getRandomCharacter(characters, wizard);
        System.out.printf("Персонаж получил %s урон молнией на %d. Теперь у него %d здоровья. \n",
                character.getName(), damage, character.getHealth());
    }

    private GameCharacter getRandomCharacter(GameCharacter[] characters, GameCharacter wizard) {
        GameCharacter gameCharacter = getRandomCharacter(characters);
        if(gameCharacter != null && gameCharacter.isAlive() && !gameCharacter.equals(wizard)) {
            return gameCharacter;
        } else{
            return getRandomCharacter(characters, wizard);
        }
    }

    private GameCharacter getRandomCharacter(GameCharacter[] characters) {
        return characters[ThreadLocalRandom.current().nextInt(0, characters.length)];
    }
}
