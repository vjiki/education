package com.epam.rd.autocode.factory.plot.impl;

import com.epam.rd.autocode.Named;
import com.epam.rd.autocode.factory.plot.Plot;

public class ClassicDisneyPlot implements Plot {
    private final Named first_character;
    private final Named second_character;
    private final Named third_character;

    @Override
    public String toString() {
        return String.format("%s is great. %s and %s love each other. %s interferes with their happiness but loses in the end.",
                first_character.name(), first_character.name(), second_character.name(), third_character.name());
    }

    public ClassicDisneyPlot(Named first_character, Named second_character, Named third_character) {
        this.first_character = first_character;
        this.second_character = second_character;
        this.third_character = third_character;
    }
}
