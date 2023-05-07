package shop.fabric;

import java.util.Collection;
import shop.item.electronic.ElectronicItem;

public abstract class ElectronicFabric  {
  public abstract void fillShopWithElectronicGoods(Collection<? super ElectronicItem> shop);
}
