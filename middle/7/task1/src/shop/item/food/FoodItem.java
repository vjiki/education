package shop.item.food;

import java.math.BigDecimal;
import java.util.Objects;
import shop.item.ShopItem;

public class FoodItem extends ShopItem {
  private final Integer calories;
  private final String expirationDate;

  public FoodItem(String name, BigDecimal cost, Integer calories, String expirationDate) {
    super(name, cost);
    this.calories = calories;
    this.expirationDate = expirationDate;
  }

  public Integer getCalories() {
    return calories;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FoodItem foodItem)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(calories, foodItem.calories) && Objects.equals(expirationDate, foodItem.expirationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), calories, expirationDate);
  }

  @Override
  public String toString() {
    return "FoodItem{" +
        "calories=" + calories +
        ", expirationDate='" + expirationDate + '\'' +
        "} " + super.toString();
  }
}
