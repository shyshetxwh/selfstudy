package v15.v15complex;

import cn.shyshetxwh.util.Generator;
import cn.shyshetxwh.util.Generators;

import java.util.ArrayList;
import java.util.Random;



class Product{
    private final int id;
    private String description;
    private double price;

    public Product(int id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return id+": "+description+", price: $"+price;
    }

    public void priceChange(double change)
    {
        price+=change;
    }

    public static Generator<Product> generator()
    {
        return new Generator<Product>() {
            private Random random=new Random(47);
            @Override
            public Product next() {
                return new Product(random.nextInt(1000),"Test",Math.round(random.nextDouble()*1000)+0.99);
            }
        };
    }

}

class Shelf extends ArrayList<Product>
{
    public Shelf(int n)
    {
        Generators.fill(this,Product.generator(),n);
    }
}

class Aisle extends ArrayList<Shelf>
{
    public Aisle(int nShelves,int nProduct)
    {
        for (int i = 0; i < nShelves; i++) {
            this.add(new Shelf(nProduct));
        }
    }
}

public class Store extends ArrayList<Aisle>{
    public Store(int nAisles,int nShelves,int nProducts)
    {
        for (int i = 0; i < nAisles; i++) {
            add(new Aisle(nShelves,nProducts));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Aisle a : this) {
            for (Shelf s : a) {
                for (Product p : s) {
                    result.append(p);
                    result.append("\n");
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Store(14,5,10));
    }
}