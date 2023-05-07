package shop.item.electronic.impl;

import java.math.BigDecimal;
import java.util.Objects;
import shop.item.electronic.ElectronicItem;

public class Refrigerator extends ElectronicItem {
  private final Integer freezerVolume;

  public Refrigerator(String name, BigDecimal cost, Integer powerConsumption, Integer freezerVolume) {
    super(name, cost, powerConsumption);
    this.freezerVolume = freezerVolume;
  }


  public Integer getFreezerVolume() {
    return freezerVolume;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Refrigerator that)) {
      return false;
    }
    return Objects.equals(freezerVolume, that.freezerVolume);
  }

  @Override
  public int hashCode() {
    return Objects.hash(freezerVolume);
  }

  @Override
  public String toString() {
    return "Refrigerator{" +
        "freezerVolume=" + freezerVolume +
        "} " + super.toString();
  }
}
