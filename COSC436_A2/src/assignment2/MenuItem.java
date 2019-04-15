package assignment2;

import java.util.LinkedList;

public class MenuItem {

    private String name;
    private int item_type;
    private boolean heart_healthy;
    private double price;


    public MenuItem(String name, int item_type, boolean heart_healthy, double price) {
        this.name = name;
        this.item_type = item_type;
        this.heart_healthy = heart_healthy;
        this.price = price;
    }

    //getter methods
    public double getPrice(){
        return price;
    }

    public String getName(){
        return name;
    }

    public int getItemType(){
        return item_type;
    }

    public boolean isHeartHealthy(){
        return heart_healthy;
    }


}
