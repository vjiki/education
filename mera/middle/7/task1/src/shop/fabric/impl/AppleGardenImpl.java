package shop.fabric.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import shop.fabric.AppleGarden;
import shop.item.food.impl.Apple;

public class AppleGardenImpl extends AppleGarden {

  private final static String[] appleNames =
      new String[]{"Red Prince", "Nicoter", "Granny Smith", "Labo", "Mutsu", "Golden Delishios"};

  private final static String[] appleColors =
      new String[]{"Red", "White", "Black", "Green", "Pink", "Yellow"};

  @Override
  public void fillShopWithApples(Collection<? super Apple> shop) {
    List<String> colors = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Apple apple = makeApple(getRandomAppleName(), getRandInt(), getRandInt(), getRandomColorName());
      shop.add(apple);
      colors.add(apple.getColor());
    }

    System.out.println("В магазин добавлены яблоки: " + colors);
  }


  Apple makeApple(String name, int cost, int calories, String color) {
    return new Apple(name,
        BigDecimal.valueOf(cost),
        calories,
        "10.09.2023",
        color);
  }

  private String getRandomAppleName() {
    return appleNames[ThreadLocalRandom.current().nextInt(0, appleNames.length)];
  }

  private String getRandomColorName() {
    return appleColors[ThreadLocalRandom.current().nextInt(0, appleColors.length)];
  }

  private int getRandInt() {
    return ThreadLocalRandom.current().nextInt(0, 50000);
  }
}
