package com.coffee.machine.listener;

import com.coffee.machine.Drink;

import java.util.EventListener;

public interface IDrinkListener extends EventListener {
  void drinkReceived(Drink drink, double price);

  void drinkMade(Drink drink);
}
