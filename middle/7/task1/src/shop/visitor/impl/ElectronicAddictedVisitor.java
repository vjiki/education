package shop.visitor.impl;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import shop.item.electronic.ElectronicItem;
import shop.item.ShopItem;
import shop.visitor.ShopVisitor;

public class ElectronicAddictedVisitor implements ShopVisitor {

  @Override
  public void visitShop(Collection<? extends ShopItem> shop) {

    List<ElectronicItem> electronicItems = shop
        .stream()
        .filter(item -> item instanceof ElectronicItem)
        .map(item -> (ElectronicItem) item)
        .toList();

    electronicItems
        .forEach(item ->
            System.out.println(item.getName() + " - " + item.getCost()));

    Optional<ElectronicItem> maxPriceItem = buy(electronicItems);
    maxPriceItem.ifPresent(shop::remove);
  }

  public Optional<ElectronicItem> buy(Collection<ElectronicItem> items) {
    Optional<ElectronicItem> item = items.stream()
        .max(Comparator.comparing(ElectronicItem::getPowerConsumption));
    item.ifPresent(
        electronicItem ->
            System.out.println(electronicItem.getName() + " куплен по " + electronicItem.getCost()));
    return item;
  }
}
