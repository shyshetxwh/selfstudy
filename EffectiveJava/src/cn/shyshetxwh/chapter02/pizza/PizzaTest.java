package cn.shyshetxwh.chapter02.pizza;

/**
 * FileName: PizzaTest
 * Author:   admin+shyshetxwh
 * Date:     2021/03/12 20:08
 */
public class PizzaTest {
    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL).addTopping(Pizza.Topping.SAUSAGE).addTopping(Pizza.Topping.ONION).build();
        Calzone calzone = new Calzone.Builder().addTopping(Pizza.Topping.HAM).sauceInside().build();
    }
}
