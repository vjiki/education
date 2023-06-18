package shop.item.food.impl;

import java.math.BigDecimal;
import java.util.Objects;
import shop.item.food.FoodItem;

public class Apple extends FoodItem {
  private final String color;

  public Apple(String name, BigDecimal cost, Integer calories, String expirationDate, String color) {
    super(name, cost, calories, expirationDate);
    this.color = color;
  }

  public String getColor() {
    return color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Apple apple)) {
      return false;
    }
    return Objects.equals(color, apple.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color);
  }

  @Override
  public String toString() {
    return "Apple{" +
        "color='" + color + '\'' +
        "} " + super.toString();
  }
}
