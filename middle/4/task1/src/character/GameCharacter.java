package character;

//
//У абстрактного персонажа есть характеристики:
//- Текущее здоровье: целое число
//- Имя: строка
//

import constants.GameConstants;
import java.util.concurrent.ThreadLocalRandom;

public abstract class GameCharacter {
    private int health;
    private final String name;
    private final int position;

    protected GameCharacter(String name, int position) {
        this.name = name;
        this.position = position;
        this.health = ThreadLocalRandom.current().nextInt(GameConstants.MIN_INITIAL_HEALTH_POINTS, GameConstants.MAX_INITIAL_HEALTH_POINTS+1);
    }

    public abstract void nextMove(GameCharacter[] gameCharacters);
    public boolean isAlive() {
        return  health > 0;
    }

    public int getPosition() {
        return position;
    }
    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void damaged(int damage) {
        health -= damage;
    }

    public void healthy(int healthPoints) {
        health += healthPoints;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCharacter that = (GameCharacter) o;
        return position == that.position && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "GameCharacter{" +
                "health=" + health +
                ", name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}