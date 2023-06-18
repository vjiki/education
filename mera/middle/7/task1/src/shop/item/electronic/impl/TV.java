package shop.item.electronic.impl;

import java.math.BigDecimal;
import java.util.Objects;
import shop.item.electronic.ElectronicItem;

public class TV extends ElectronicItem {
  private final Integer volume;

  public TV(String name, BigDecimal cost, Integer powerConsumption, Integer volume) {
    super(name, cost, powerConsumption);
    this.volume = volume;
  }


  public int getVolume() {
    return volume;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TV tv)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(volume, tv.volume);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), volume);
  }

  @Override
  public String toString() {
    return "TV{" +
        "volume=" + volume +
        "} " + super.toString();
  }
}
