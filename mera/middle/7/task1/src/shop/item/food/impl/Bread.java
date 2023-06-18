package shop.item.food.impl;

import java.math.BigDecimal;
import java.util.Objects;
import shop.item.food.FoodItem;

public class Bread extends FoodItem {
  private Integer weightInGrams;

  public Bread(String name, BigDecimal cost, Integer calories, String expirationDate, Integer weightInGrams) {
    super(name, cost, calories, expirationDate);
    this.weightInGrams = weightInGrams;
  }
  public Integer getWeightInGrams() {
    return weightInGrams;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Bread bread)) {
      return false;
    }
    return weightInGrams == bread.weightInGrams;
  }

  @Override
  public int hashCode() {
    return Objects.hash(weightInGrams);
  }

  @Override
  public String toString() {
    return "Bread{" +
        "weightInGrams=" + weightInGrams +
        "} " + super.toString();
  }
}
