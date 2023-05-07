package character;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static constants.GameConstants.*;
import static constants.GameConstants.characterNames;

public class CharacterGenerator {

    public void generateRandomCharacters(GameCharacter[] characters) {
        int randomCharactersLimit = getRandomLimitForCharacters();

        while (countAliveCharacters(characters) != randomCharactersLimit) {
            CharacterFabric characterFabric = getSomeFabric();
            int randomPosition = getRandomCharacterPosition(characters);
            characters[randomPosition] = characterFabric
                    .makeCharacter(getRandomCharacterName(), randomPosition);
        }
    }

    private int getRandomCharacterPosition(GameCharacter[] characters) {
        return ThreadLocalRandom.current().nextInt(0, characters.length);
    }

    private int getRandomLimitForCharacters() {
        return ThreadLocalRandom.current().nextInt(MIN_CHARACTERS_SIZE, MAX_CHARACTERS_SIZE);
    }

    private String getRandomCharacterName() {
        return characterNames[ThreadLocalRandom.current().nextInt(0, characterNames.length)];
    }

    private CharacterFabric getSomeFabric() {
        if (new Random().nextBoolean()) {
            return new MonsterFabric();
        } else {
            return new WizardFabric();
        }
    }

    private long countAliveCharacters(GameCharacter[] characters) {
        return Arrays.stream(characters).filter(Objects::nonNull).filter(GameCharacter::isAlive).count();
    }
}
