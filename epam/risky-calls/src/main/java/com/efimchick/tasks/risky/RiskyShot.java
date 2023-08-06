package com.efimchick.tasks.risky;

import com.efimchick.tasks.risky.util.RussianRoulette;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RiskyShot {

    final int input;
    final RussianRoulette roulette;


    public RiskyShot(final int input,
                     final RussianRoulette roulette) {
        this.input = input;
        this.roulette = roulette;
    }

    public void handleShot() /*You may not add "throws" here*/ {
        // handle method call
        try {
            roulette.shot(input);
        }
        catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File is missing", e);
        }
        catch (IOException e) {
            throw new IllegalArgumentException("File error", e);
        }
        catch (ArithmeticException e) {
            try {
                new RiskyShot(input + 1, roulette).handleShot();
            } catch (Exception exception) {
                //exception.printStackTrace();
            }
        }
        catch (NumberFormatException e) {
            try {
                new RiskyShot(input + 2, roulette).handleShot();
            } catch (Exception exception) {
                //exception.printStackTrace();
            }
        }
        catch (UnsupportedOperationException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
