package shop.visitor.impl;

import java.util.Collection;
import shop.item.ShopItem;
import shop.visitor.ShopVisitor;

public class ImJustLookingVisitor implements ShopVisitor {

  @Override
  public void visitShop(Collection<? extends ShopItem> shop) {
    shop.forEach(item ->
        System.out.println(item.getName() + " - " + item.getCost()));
  }
}
