package com.epam.rd.autocode.factory.plot.impl;

import com.epam.rd.autocode.Named;
import com.epam.rd.autocode.factory.plot.Plot;
import com.epam.rd.autocode.factory.plot.PlotFactory;

public class ContemporaryDisneyPlotFactory implements PlotFactory {
    ContemporaryDisneyPlot plot;

    public ContemporaryDisneyPlotFactory(Named first, Named crisis, Named second) {
        plot = new ContemporaryDisneyPlot(first, crisis, second);
    }

    @Override
    public Plot plot() {
        return this.plot;
    }
}
