import java.util.ArrayList;
import java.util.List;
import shop.fabric.AppleGarden;
import shop.fabric.impl.AppleGardenImpl;
import shop.fabric.ElectronicFabric;
import shop.fabric.impl.ElectronicFabricImpl;
import shop.fabric.FoodFactory;
import shop.fabric.impl.FoodFactoryImpl;
import shop.item.ShopItem;
import shop.shops.impl.ElectronicShop;
import shop.shops.impl.FoodShop;
import shop.shops.impl.Hypermarket;
import shop.shops.impl.Kiosk;
import shop.shops.Shop;
import shop.visitor.ShopVisitor;
import shop.visitor.impl.ElectronicAddictedVisitor;
import shop.visitor.impl.ImJustLookingVisitor;
import shop.visitor.impl.RichVisitor;

public class Main {
  public static void main(String[] args) {

    Hypermarket hypermarket = new Hypermarket();
    ElectronicShop electronicShop = new ElectronicShop();
    FoodShop foodShop = new FoodShop();
    Kiosk kiosk = new Kiosk();

    ElectronicFabric electronicFabric = new ElectronicFabricImpl();
    electronicFabric.fillShopWithElectronicGoods(electronicShop);
    electronicFabric.fillShopWithElectronicGoods(hypermarket);

    AppleGarden appleGarden = new AppleGardenImpl();
    appleGarden.fillShopWithApples(kiosk);
    appleGarden.fillShopWithApples(foodShop);
    appleGarden.fillShopWithApples(hypermarket);

    FoodFactory foodFactory = new FoodFactoryImpl();
    foodFactory.fillShopWithFood(foodShop);
    foodFactory.fillShopWithFood(hypermarket);

    ShopVisitor imJustLookingVisitor = new ImJustLookingVisitor();
    ShopVisitor electronicAddictedVisitor = new ElectronicAddictedVisitor();
    ShopVisitor richVisitor = new RichVisitor();

    List<ShopVisitor> shopVisitorList = new ArrayList<>();

    List<Shop<? extends ShopItem>> shops = new ArrayList<>();
    shops.add(hypermarket);
    shops.add(foodShop);
    shops.add(kiosk);
    shops.add(electronicShop);

    shopVisitorList.add(imJustLookingVisitor);
    shopVisitorList.add(electronicAddictedVisitor);
    shopVisitorList.add(richVisitor);
    shopVisitorList.add(imJustLookingVisitor);

    for (ShopVisitor visitor : shopVisitorList) {
      System.out.printf("%s пошел в магазины \n", visitor.getClass().getSimpleName());
      for (Shop<? extends ShopItem> shop : shops) {
        visitor.visitShop(shop);
      }
      System.out.printf("%s вышел из магазинов \n", visitor.getClass().getSimpleName());
    }
  }
}
