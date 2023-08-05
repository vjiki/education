package com.epam.rd.autocode.factory.plot.impl;

import com.epam.rd.autocode.Named;
import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.EpicCrisis;
import com.epam.rd.autocode.factory.plot.Plot;
import com.epam.rd.autocode.factory.plot.PlotFactory;

public class MarvelPlotFactory implements PlotFactory {
    MarvelPlot plot;

    public MarvelPlotFactory(Named[] heroes, Named epicCrisis, Named villain) {
        plot = new MarvelPlot(heroes, epicCrisis, villain);
    }

    @Override
    public Plot plot() {
        return this.plot;
    }
}
