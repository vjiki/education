import static constants.GameConstants.MAX_CHARACTERS_SIZE;

import character.*;

import java.util.*;
import java.util.stream.Collectors;

//
//Есть сцена (Scene).
//На сцену можно добавить до 10 Персонажей (Character)
//у каждого персонажа есть позиция (от 0 до 9). На которой он стоит. На позиции с одинаковым номером может стоять только один персонаж.
//В позициях допускаются пропуски, например, на сцене могут быть всего два персонажа: на позиции 1 и на позиции 7.
//
//Если текущее здоровье у персонажа стало отрицательным - он удаляется со сцены и на экран выводится текст "<имя персонажа> убит"
//
public class Scene {
    private final GameCharacter[] characters = new GameCharacter[MAX_CHARACTERS_SIZE];

    public Scene() {
        //System.out.println("init scene");
        CharacterGenerator characterGenerator = new CharacterGenerator();
        characterGenerator.generateRandomCharacters(characters);
    }

    public GameCharacter[] getAliveCharacters() {
        return Arrays.stream(characters).filter(Objects::nonNull).filter(GameCharacter::isAlive).toArray(GameCharacter[]::new);
    }

    public void removeDeadCharacters() {
        for (GameCharacter character : characters) {
            if (character != null) {
                if (character.getHealth() <= 0) {
                    System.out.println("Персонаж убит: " + character.getName());
                    characters[character.getPosition()] = null;
                }
            }
        }
    }

    public long countAliveCharacters() {
        return Arrays.stream(characters).filter(Objects::nonNull).filter(GameCharacter::isAlive).count();
    }

    public List<GameCharacter> getWinners() {
        return Arrays.stream(characters).filter(Objects::nonNull).filter(GameCharacter::isAlive).collect(Collectors.toList());
    }
}
