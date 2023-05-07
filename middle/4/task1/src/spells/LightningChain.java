package spells;

//
//Цепная молния - наносит урон, всем персонажам на сцене, кроме мага, который произносит заклинание.
//

import character.GameCharacter;
import constants.GameConstants;

import java.util.concurrent.ThreadLocalRandom;

public class LightningChain extends Spell {

    public LightningChain() {
        name = "Цепная молния";
    }

    @Override
    public void cast(GameCharacter[] characters, GameCharacter wizard) {
        int damage = ThreadLocalRandom.current().nextInt(GameConstants.MIN_DAMAGE_POINT, GameConstants.MAX_DAMAGE_POINT);

        StringBuilder sb = new StringBuilder();
        for (GameCharacter character : characters) {
            if(character != null) {
                if (character.equals(wizard)) {
                    continue;
                }
                character.damaged(damage);
                sb.append(" <").append(character.getName()).append(">,");
            }
        }
        System.out.printf("Цепная молния ударяет по %s. Каждый получает %d урона.\n",
                sb, damage);
    }
}
