package shop.fabric;

import java.util.Collection;
import shop.item.food.impl.Apple;

public abstract class AppleGarden {

  public abstract void fillShopWithApples(Collection<? super Apple> shop);

}
