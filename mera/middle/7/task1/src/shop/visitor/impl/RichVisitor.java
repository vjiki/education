package shop.visitor.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import shop.item.ShopItem;
import shop.visitor.ShopVisitor;

public class RichVisitor implements ShopVisitor {

  @Override
  public void visitShop(Collection<? extends ShopItem> shop) {
    List<ShopItem> everySecondItems = buy(shop);
    shop.removeAll(everySecondItems);
  }

  public List<ShopItem> buy(Collection<? extends ShopItem> shop) {
    List<ShopItem> everySecondItems = IntStream
        .range(0, shop.size())
        .filter(i -> i % 2 == 0)
        .mapToObj(i -> (ShopItem) shop.toArray()[i])
        .toList();

    everySecondItems.forEach(item ->
        System.out.println(item.getName() + " куплен по " + item.getCost()));

    return everySecondItems;
  }
}
