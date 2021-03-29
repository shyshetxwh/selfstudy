package cn.shyshetxwh.chapter02.pizza;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * FileName: Pizza
 * Author:   admin+shyshetxwh
 * Date:     2021/03/12 19:51
 * <p>
 * Builder模式也适用于类层次结构
 */
public abstract class Pizza {
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    ;
    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}
