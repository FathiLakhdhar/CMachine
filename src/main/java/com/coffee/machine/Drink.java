package com.coffee.machine;

public abstract class Drink {
  private final TYPE type;

  private int sugar;

  public enum TYPE {
    COFFEE(0.6),
    COFFEE_HOT(0.6),
    TEA(0.4),
    TEA_HOT(0.4),
    CHOCOLATE(0.5),
    CHOCOLATE_HOT(0.5),
    ORANGE(0.6);

    private final double price;

    TYPE(double price) {
      this.price = price;
    }

    public double getPrice() {
      return price;
    }
  }

  public Drink(TYPE type) {
    this.type = type;
  }

  public TYPE getType() {
    return type;
  }

  public int getSugar() {
    return sugar;
  }

  public void setSugar(int sugar) {
    this.sugar = sugar;
  }
}
