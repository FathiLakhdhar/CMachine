package com.coffee.machine;

import com.coffee.machine.listener.IDrinkListener;
import com.coffee.machine.report.Reporter;

import java.util.ArrayList;
import java.util.List;

public class DrinkMaker {

  private List<IDrinkListener> listeners = new ArrayList<>();

  public String receive(Drink drink, double price) {
    fireDrinkReceived(drink, price);
    if (drink.getType().getPrice() > price) {
      return "M: Drink " + drink.getType() + ": money missing " + String.format("%.3f", (drink.getType().getPrice() - price));
    }

    fireDrinkMade(drink);
    return null;
  }

  private void fireDrinkReceived(Drink drink, double price) {
    for (IDrinkListener listener : listeners) {
      listener.drinkReceived(drink, price);
    }
  }

  private void fireDrinkMade(Drink drink) {
    for (IDrinkListener listener : listeners) {
      listener.drinkMade(drink);
    }
  }

  public void addListener(IDrinkListener listener) {
    this.listeners.add(listener);
  }

  public static void main(String[] args) {

    /*
    T::;0.4
    C:1:0;0.6
    Ch:2:0;0.8
    Hh:3:0;1
         */

    DrinkMaker drinkMaker = new DrinkMaker();
    Reporter reporter = new Reporter();
    drinkMaker.addListener(reporter);

    for (String arg : args) {
      String[] cmd_price = arg.split(";");
      String[] cmds = cmd_price[0].split(":");
      String type = cmds[0];
      int sugar = cmds.length > 1 && !cmds[1].isEmpty() ? Integer.parseInt(cmds[1]) : 0;

      Drink drink;
      switch (type) {
        case "T":
          drink = new Tea();
          drink.setSugar(sugar);
          break;
        case "Th":
          drink = new TeaHot();
          drink.setSugar(sugar);
          break;
        case "H":
          drink = new Chocolate();
          drink.setSugar(sugar);
          break;
        case "Hh":
          drink = new ChocolateHot();
          drink.setSugar(sugar);
          break;
        case "C":
          drink = new Coffee();
          drink.setSugar(sugar);
          break;
        case "Ch":
          drink = new CoffeeHot();
          drink.setSugar(sugar);
          break;
        case "O":
          drink = new Orange();
          break;
        default:
          throw new UnsupportedOperationException(type + "not supported");
      }
      String msg = drinkMaker.receive(drink, Double.parseDouble(cmd_price[1]));
      if (msg != null) System.out.println(msg);
    }

    reporter.report();
  }
}
