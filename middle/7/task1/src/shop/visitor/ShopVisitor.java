package shop.visitor;

import java.util.Collection;
import shop.item.ShopItem;

public interface ShopVisitor {
  void visitShop(Collection<? extends ShopItem> shop);
}
