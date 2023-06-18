package spells;

//
//Создайте класс spells.Spell с методом cast - произнесение заклинания и полем "название заклинания".
//

import character.GameCharacter;

public abstract class Spell {

    String name;
    public abstract void cast(GameCharacter[] characters, GameCharacter wizard);

    public String getName() {
        return name;
    }


}
