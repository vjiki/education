package com.epam.rd.autocode.factory.plot.impl;

import com.epam.rd.autocode.Named;
import com.epam.rd.autocode.factory.plot.Plot;
import com.epam.rd.autocode.factory.plot.PlotFactory;

public class ClassicDisneyPlotFactory implements PlotFactory {
    ClassicDisneyPlot plot;

    public ClassicDisneyPlotFactory(Named first, Named second, Named third) {
        plot = new ClassicDisneyPlot(first, second, third);
    }

    @Override
    public Plot plot() {
        return this.plot;
    }
}
