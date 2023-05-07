package shop.item;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class ShopItem {
  private final String name;
  private final BigDecimal cost;

  public ShopItem(String name, BigDecimal cost) {
    this.name = name;
    this.cost = cost;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getCost() {
    return cost;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ShopItem shopItem)) {
      return false;
    }
    return Objects.equals(name, shopItem.name) && Objects.equals(cost, shopItem.cost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, cost);
  }

  @Override
  public String toString() {
    return "ShopItem{" +
        "name='" + name + '\'' +
        ", cost=" + cost +
        '}';
  }
}