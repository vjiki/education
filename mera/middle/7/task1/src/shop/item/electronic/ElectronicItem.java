package shop.item.electronic;

import java.math.BigDecimal;
import java.util.Objects;
import shop.item.ShopItem;

public class ElectronicItem extends ShopItem {
  private final Integer powerConsumption;

  public ElectronicItem(String name, BigDecimal cost, Integer powerConsumption) {
    super(name, cost);
    this.powerConsumption = powerConsumption;
  }

  public Integer getPowerConsumption() {
    return powerConsumption;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ElectronicItem that)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(powerConsumption, that.powerConsumption);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), powerConsumption);
  }

  @Override
  public String toString() {
    return "ElectronicItem{" +
        "powerConsumption=" + powerConsumption +
        "} " + super.toString();
  }
}
