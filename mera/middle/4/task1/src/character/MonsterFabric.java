package character;

//
//Монстры. Могут атаковать любого персонажа, напрямую нанося урон здоровью.
//Количество урона для каждого конкретного монстра одинаковое, но разные экземпляры монстров могут наносить разное количество урона.
//
//Если монстр атакует любого персонажа, - на экран должен выводится текст "Монстр <имя> атакует <имя, цели> на <количество> единиц урона урона "
//

import constants.GameConstants;
import java.util.concurrent.ThreadLocalRandom;

public class MonsterFabric extends CharacterFabric {

    public static class Monster extends GameCharacter {

        private final int damage;

        protected Monster(String name, int position) {
            super(name, position);
            this.damage = ThreadLocalRandom.current().nextInt(GameConstants.MIN_DAMAGE_POINT, GameConstants.MAX_DAMAGE_POINT);
        }

        @Override
        public void nextMove(GameCharacter[] gameCharacters) {
            attack(gameCharacters);
        }

        public void attack(GameCharacter[] characters) {
            GameCharacter targetCharacter = getTargetCharacter(characters);
            targetCharacter.damaged(damage);
            System.out.printf("Монстр <%s> атакует <%s> на %d единиц урона урона \n", super.getName(), targetCharacter.getName(), damage);
        }

        private GameCharacter getTargetCharacter(GameCharacter[] characters) {
            GameCharacter gameCharacter = getRandomCharacter(characters);
            if (gameCharacter != null && gameCharacter.isAlive() && !gameCharacter.equals(this)) {
                return gameCharacter;
            } else{
                return getTargetCharacter(characters);
            }
        }

        private GameCharacter getRandomCharacter(GameCharacter[] characters) {
            return characters[ThreadLocalRandom.current().nextInt(0, characters.length)];
        }

    }

    @Override
    public GameCharacter makeCharacter(String name, int position) {
        return new Monster(name, position);
    }
}
