package shop.fabric.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import shop.fabric.AppleGarden;
import shop.fabric.FoodFactory;
import shop.item.food.FoodItem;
import shop.item.food.impl.Bread;

public class FoodFactoryImpl extends FoodFactory {

  private final static String[] appleNames =
      new String[]{"Red Prince", "Nicoter", "Granny Smith", "Labo", "Mutsu", "Golden Delishios"};

  private final static String[] breadName =
      new String[]{"White bread", "Black bread", "Borodinskiy", "Rzhanoy", "Postniy", "French bread"};


  private final static String[] appleColors =
      new String[]{"Red", "White", "Black", "Green", "Pink", "Yellow"};

  @Override
  public void fillShopWithFood(Collection<? super FoodItem> shop) {
    List<Bread> breadList = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
        Bread bread = makeBread(getRandomBreadName(), getRandInt(), getRandInt(), getRandInt());
        shop.add(bread);
        breadList.add(bread);
    }

    int sumWeight = breadList.stream().map(Bread::getWeightInGrams).reduce(0, Integer::sum);

    AppleGarden appleGarden = new AppleGardenImpl();
    appleGarden.fillShopWithApples(shop);

    System.out.println("В магазин добавлено хлеба, общим весом: " + sumWeight);
  }

  Bread makeBread(String name, int cost, int calories, int weight) {
    return new Bread(name,
        BigDecimal.valueOf(cost),
        calories,
        "12-14-2003",
        weight);
  }

  private String getRandomBreadName() {
    return breadName[ThreadLocalRandom.current().nextInt(0, breadName.length)];
  }

  private int getRandInt() {
    return ThreadLocalRandom.current().nextInt(0, 50000);
  }
}
