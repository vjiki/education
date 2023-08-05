package com.epam.rd.autocode.factory.plot.impl;

import com.epam.rd.autocode.Named;
import com.epam.rd.autocode.factory.plot.Plot;

import java.util.StringJoiner;

public class MarvelPlot implements Plot {
    private final Named[] heroes;
    private final Named crisis;
    private final Named villain;

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ");
        for (Named hero : heroes) {
            joiner.add("brave " + hero.name());
        }
        String all_heroes = joiner.toString();
        return String.format("%s threatens the world. But %s on guard. So, no way that intrigues of %s overcome the willpower of inflexible heroes",
                crisis.name(), all_heroes, villain.name());
    }

    public MarvelPlot(Named[] heroes, Named crisis, Named villain) {
        this.heroes = heroes;
        this.crisis = crisis;
        this.villain = villain;
    }
}
