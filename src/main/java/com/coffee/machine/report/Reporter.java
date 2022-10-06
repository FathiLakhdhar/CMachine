package com.coffee.machine.report;

import com.coffee.machine.Drink;
import com.coffee.machine.listener.IDrinkListener;

import java.util.HashMap;
import java.util.Map;

public class Reporter implements IReporter, IDrinkListener {

  Map<Drink.TYPE, Integer> nb = new HashMap<>();

  @Override
  public void report() {
    Double totalAmount = 0D;
    for (Map.Entry<Drink.TYPE, Integer> entry : nb.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
      totalAmount += entry.getKey().getPrice() * entry.getValue();
    }
    System.out.println("Total amount: " + totalAmount);
  }

  @Override
  public void drinkReceived(Drink drink, double price) {}

  @Override
  public void drinkMade(Drink drink) {
    Integer n = nb.computeIfAbsent(drink.getType(), type -> 0);
    nb.put(drink.getType(), ++n);
  }
}
