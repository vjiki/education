package shop.fabric;

import java.util.Collection;
import shop.item.food.FoodItem;

public abstract class FoodFactory {
  public abstract void  fillShopWithFood(Collection<? super FoodItem> shop);
}
