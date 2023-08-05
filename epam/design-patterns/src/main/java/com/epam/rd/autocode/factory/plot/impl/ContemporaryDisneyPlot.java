package com.epam.rd.autocode.factory.plot.impl;

import com.epam.rd.autocode.Named;
import com.epam.rd.autocode.factory.plot.Plot;

public class ContemporaryDisneyPlot implements Plot {
    private final Named first_character;
    private final Named crisis;
    private final Named second_character;

    @Override
    public String toString() {
        return String.format("%s feels a bit awkward and uncomfortable. But personal issues fades, when a big trouble comes - %s. %s stands up against it, but with no success at first.But putting self together and help of friends, including spectacular funny %s restore the spirit and %s overcomes the crisis and gains gratitude and respect",
                first_character.name(), crisis.name(), first_character.name(), second_character.name(), first_character.name());
    }

    public ContemporaryDisneyPlot(Named first_character, Named crisis, Named second_character) {
        this.first_character = first_character;
        this.crisis = crisis;
        this.second_character = second_character;
    }
}
