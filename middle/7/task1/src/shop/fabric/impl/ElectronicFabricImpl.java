package shop.fabric.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import shop.fabric.ElectronicFabric;
import shop.item.electronic.ElectronicItem;
import shop.item.electronic.impl.Refrigerator;
import shop.item.electronic.impl.TV;

public class ElectronicFabricImpl extends ElectronicFabric {

  private final static String[] refrigeratorNames =
      new String[]{"Haier", "Atlant", "Samsung", "LG", "Belogrus", "Bosh"};

  private final static String[] tvNames =
      new String[]{"Samsung", "LG", "TCL", "Xiaomi", "Philips", "Chaika"};


  Refrigerator makeRefrigerator(String name, int cost, int volume, int power) {
    return new Refrigerator(name,
        BigDecimal.valueOf(cost),
        power,
        volume);
  }

  TV makeTV(String name, int cost, int power, int volume) {
    return new TV(name, BigDecimal.valueOf(cost), power, volume);
  }

  private String getRandomRefrigeratorName() {
    return refrigeratorNames[ThreadLocalRandom.current().nextInt(0, refrigeratorNames.length)];
  }

  private String getRandomTvName() {
    return tvNames[ThreadLocalRandom.current().nextInt(0, tvNames.length)];
  }

  private int getRandInt() {
    return ThreadLocalRandom.current().nextInt(0, 50000);
  }

  @Override
  public void fillShopWithElectronicGoods(Collection<? super ElectronicItem> shop) {
    List<ElectronicItem> electronicItemList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      if (new Random().nextBoolean()) {
        Refrigerator refrigerator = makeRefrigerator(getRandomRefrigeratorName(), getRandInt(), getRandInt(), getRandInt());
        shop.add(refrigerator);
        electronicItemList.add(refrigerator);
      } else {
        TV tv = makeTV(getRandomTvName(), getRandInt(), getRandInt(), getRandInt());
        shop.add(tv);
        electronicItemList.add(tv);
      }
    }
    System.out.println("В магазин добавлена электроника: "
        + electronicItemList.stream().map(ElectronicItem::getName).toList());
  }
}
