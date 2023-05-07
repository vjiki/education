package character;

//
//Маги. Могут колдовать заклинания. Заклинания могут делать что угодно со сценой и персонажами на ней.
//У каждого мага есть список заклинаний (книга заклинаний), которые он может использовать, но не больше 3.
//
//Если маг произносит любое заклинание то экран обязательно должен выводится общий текст для всех заклинаний:
//"Маг <имя> читает заклинание <название заклинания>"
//
//+ дополнительно должен быть выведен текст, зависящий от заклинания.
//Например
//"Маг Merlin читает заклинание Исцеление."
//"Маг Merlin исцелен на 12. Теперь у него 20 здоровья"
//
//"Маг Merlin читает заклинание Цепная Молния."
//"Цепная молния ударяет по Трус, Бывалый, Балбес. Каждый получает 5 урона."
//

import spells.Spell;
import spells.SpellsFabric;
import spells.RandomSpellsFabric;

import java.util.concurrent.ThreadLocalRandom;

public class WizardFabric extends CharacterFabric {

    public static class Wizard extends GameCharacter {
        Spell[] spells;

        protected Wizard(String name, int position) {
            super(name, position);
            SpellsFabric spellsFabric = new RandomSpellsFabric();
            spells = spellsFabric.makeSpells();
        }

        @Override
        public void nextMove(GameCharacter[] gameCharacters) {
            doMagic(gameCharacters);
        }

        public void doMagic(GameCharacter[] gameCharacters) {
            Spell spell = getRandomSpell();
            System.out.printf("Маг <%s> читает заклинание <%s> \n", getName(), spell.getName());
            spell.cast(gameCharacters,this);
        }

        private Spell getRandomSpell() {
            return spells[ThreadLocalRandom.current().nextInt(0, spells.length)];
        }
    }

    @Override
    public GameCharacter makeCharacter(String name, int position) {
        return new Wizard(name, position);
    }
}
