package assignment2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu {

    private static final int APPETIZERS = 1;
    private static final int MAIN_DISH = 2;
    private static final int DESSERT = 3;

    private static final boolean HEART_HEALTHY = true;
    private static final boolean NOT_HEART_HEALTHY = false;

    LinkedList<MenuItem> menu_items;

    public Menu(){
        menu_items = new LinkedList<MenuItem>();
    }

    //this private method adds the name, item type,
    // heart healthy boolean, and price to the linked list
    public void add(MenuItem item){
        menu_items.add(item);
    }

    public void removeItem(String name){
        for(int i = 0; i < menu_items.size(); i++){
            if (menu_items.get(i).getName().equals(name)){
                menu_items.remove(i);
            }
        }
    }

    //this method shows the options avaiable to the user
    private static void showOptions(){
        System.out.println("--- What would you like to do? ---\n" +
                "1) Display all menu items \n2) Display all appetizers\n" +
                "3) Display all main dishes \n4) Display all desserts \n" +
                "5) Display all hearty healthy items \n" +
                "6) Display all main dishes under a specified price \n" +
                "7) Add menu item \n8) Remove menu item \n0) quit\n");
    }

    public MenuIterator getAllItemsIterator(){
        return new AllItemsIterator(this, 0);
    }

    private class AllItemsIterator implements MenuIterator{
        private int current_index;
        private Menu list;

        public AllItemsIterator(Menu list, int current_index){
           this.current_index = current_index;
           this.list = list;

           if(!validValue()){
               advanceToNext();
           }
        }

        private boolean validValue(){
            return true;
        }
        //checks to see if we are at the end of the list
        // retruns true if that is the case, false if not
        private boolean offEnd(){
            return current_index == list.menu_items.size();
        }

        private void advanceToNext(){
            current_index++;

            while(!offEnd() && !validValue())
                current_index++;
        }

        //checks to see there is a next value
        public boolean hasNext(){
            return !offEnd();
        }

        public MenuItem next(){
            if(!hasNext())
                throw new RuntimeException();

            MenuItem return_val = list.menu_items.get(current_index);
            advanceToNext();
            return return_val;
        }
    }

    public MenuIterator getItemIterator(int item_type){
        return new ItemIterator(this, 0, item_type);
    }

    private class ItemIterator implements MenuIterator{
        private int current_index;
        private Menu list;
        private int item_type;

        public ItemIterator(Menu list, int current_index, int item_type){
            this.list = list;
            this.current_index = current_index;
            this.item_type = item_type;

            if(!validValue()){
                advanceToNext();
            }
        }

        private void advanceToNext(){
            current_index++;

            while(!offEnd() && !validValue())
                current_index++;
        }

        private boolean offEnd(){
            return current_index == list.menu_items.size();
        }

        private boolean validValue(){
            return (list.menu_items.get(current_index).getItemType() == item_type);
        }

        public boolean hasNext() {
            return !offEnd() && validValue();
        }

        public MenuItem next(){
            if(!hasNext())
                throw new RuntimeException();

            MenuItem return_val = list.menu_items.get(current_index);
            advanceToNext();
            return return_val;
        }
    }

    public MenuIterator getHeartHealthyIterator(){
        return new HeartHealthyIterator(this, 0);
    }

    private class HeartHealthyIterator implements MenuIterator{
        private int current_index;
        private Menu list;

        public HeartHealthyIterator(Menu list, int current_index){
            this.list = list;
            this.current_index = current_index;

            if(!validValue()){
                advanceToNext();
            }
        }

        private void advanceToNext(){
            current_index++;

            while(!offEnd() && !validValue())
                current_index++;
        }

        private boolean offEnd(){
            return current_index == list.menu_items.size();
        }

        private boolean validValue(){
            return (list.menu_items.get(current_index).isHeartHealthy());
        }

        @Override
        public boolean hasNext() {
            return !offEnd() && validValue();
        }

        @Override
        public MenuItem next() {
            if(!hasNext())
                throw new RuntimeException();

            MenuItem return_val = list.menu_items.get(current_index);
            advanceToNext();
            return return_val;
        }
    }

    public MenuIterator getPriceIterator(double price){
        return new PriceIterator(this, 0 , price);
    }

    private class PriceIterator implements MenuIterator{
        private int current_index;
        private Menu list;
        private double price;

        public PriceIterator(Menu list, int current_index, double price){
            this.list = list;
            this.current_index = current_index;
            this.price = price;

            if(!validValue()){
                advanceToNext();
            }
        }

        private void advanceToNext(){
            current_index++;

            while(!offEnd() && !validValue())
                current_index++;
        }

        private boolean offEnd(){
            return current_index == list.menu_items.size();
        }

        private boolean validValue(){
            return (list.menu_items.get(current_index).getPrice() < price);
        }

        @Override
        public boolean hasNext() {
            return !offEnd() && validValue();
        }

        @Override
        public MenuItem next() {
            if(!hasNext())
                throw new RuntimeException();

            MenuItem return_val = list.menu_items.get(current_index);
            advanceToNext();
            return return_val;
        }
    }

    public static void main( String args[] ){

        Menu myMenu = new Menu();

        Scanner scan = new Scanner(System.in);

        myMenu.add(new MenuItem("Chicken Parmeson", Menu.MAIN_DISH, Menu.NOT_HEART_HEALTHY, 11.99));
        myMenu.add(new MenuItem("Baked Salmon", Menu.MAIN_DISH, Menu.HEART_HEALTHY, 7.99));
        myMenu.add(new MenuItem("Greek Salad", Menu.MAIN_DISH, Menu.HEART_HEALTHY, 7.99));
        myMenu.add(new MenuItem("Steak", Menu.MAIN_DISH, Menu.HEART_HEALTHY, 12.99));

        myMenu.add(new MenuItem("Mozzarella", Menu.APPETIZERS, Menu.NOT_HEART_HEALTHY, 5.99));
        myMenu.add(new MenuItem("Crab Pretzel", Menu.APPETIZERS, Menu.NOT_HEART_HEALTHY, 5.99));
        myMenu.add(new MenuItem("Fried Cheese Curds", Menu.APPETIZERS, Menu.NOT_HEART_HEALTHY, 4.99));
        myMenu.add(new MenuItem("Chips w/ Five Layer Mexican Dip", Menu.APPETIZERS, Menu.HEART_HEALTHY, 3.99));

        myMenu.add(new MenuItem("Raspberry Cheesecake", Menu.DESSERT, Menu.NOT_HEART_HEALTHY, 9.99));
        myMenu.add(new MenuItem("Key Lime Pie", Menu.DESSERT, Menu.NOT_HEART_HEALTHY, 8.99));
        myMenu.add(new MenuItem("Yogurt Parfiat", Menu.DESSERT, Menu.HEART_HEALTHY, 3.99));
        myMenu.add(new MenuItem("Bannana Creme Pie", Menu.DESSERT, Menu.NOT_HEART_HEALTHY, 6.99));

        int user_choice;
        boolean heart_healthy;

        do{
            showOptions();
            user_choice = scan.nextInt();
            switch(user_choice){

                case 0:{
                    user_choice = 0;
                    System.out.println("Bye Bye...");
                    break;
                }
                case 1:{
                    System.out.println("\n --- All Menu Items ---");
                    MenuIterator itr = myMenu.getAllItemsIterator();
                    while(itr.hasNext()){
                        MenuItem item = itr.next();
                        System.out.println(item.getName()+" -----> $"+item.getPrice());
                    }
                    System.out.println();
                    break;
                }
                case 2:{
                    System.out.println("\n --- All Appetizer Items ---");
                    MenuIterator itr = myMenu.getItemIterator(Menu.APPETIZERS);
                    while(itr.hasNext()){
                        MenuItem item = itr.next();
                        System.out.println(item.getName()+" -----> $"+item.getPrice());

                    }
                    System.out.println();
                    break;
                }
                case 3:{
                    System.out.println("\n --- All Entree Items ---");
                    MenuIterator itr = myMenu.getItemIterator(Menu.MAIN_DISH);
                    while(itr.hasNext()){
                        MenuItem item = itr.next();
                        System.out.println(item.getName()+" -----> $"+item.getPrice());

                    }
                    System.out.println();
                    break;
                }
                case 4:{
                    System.out.println("\n --- All Dessert Items ---");
                    MenuIterator itr = myMenu.getItemIterator(Menu.DESSERT);
                    while(itr.hasNext()){
                        MenuItem item = itr.next();
                        System.out.println(item.getName()+" -----> $"+item.getPrice());

                    }
                    System.out.println();
                    break;
                }
                case 5:{
                    System.out.println("\n --- All Heart Healthy Items ---");
                    MenuIterator itr = myMenu.getHeartHealthyIterator();
                    while (itr.hasNext()){
                        MenuItem item = itr.next();
                        System.out.println(item.getName()+" -----> $"+item.getPrice());
                    }
                    System.out.println();
                    break;
                }
                case 6:{
                    System.out.println("\nWhat is most you are willing to spend?");
                    double price = scan.nextDouble();
                    System.out.println();
                    if(price == 0){
                        System.out.println("---- Invalid input price ----");
                    }else{
                        System.out.println("\n --- All Menu Items Under $"+price+" ---");
                        MenuIterator itr = myMenu.getPriceIterator(price);
                        while (itr.hasNext()){
                            MenuItem item = itr.next();
                            System.out.println(item.getName()+" -----> $"+item.getPrice());
                        }
                    }

                    System.out.println();
                    break;
                }
                case 7: {
                    System.out.println("\nEnter item name: ");
                    scan.nextLine();
                    String name = scan.nextLine();
                    System.out.println("What is the item?\n1 - Appetizer\n2 - Entree\n3 - Dessert ");
                    int category = scan.nextInt();
                    if(category < 0 || category > 3){
                        System.out.println("Invalid category input");
                    }
                    else{
                        System.out.println("Is item heart healthy?(y/n) ");
                        scan.nextLine();
                        String hearth_health = scan.nextLine();
                        heart_healthy = false;
                        if(hearth_health.equals("y")){
                            heart_healthy = true;
                        } else if(hearth_health.equals("n")){
                            heart_healthy = false;
                        }
                        else{
                            System.out.println("Invalid input");
                        }
                        System.out.println("Enter item price: ");
                        double price = scan.nextDouble();
                        myMenu.add(new MenuItem(name, category, heart_healthy, price));
                        System.out.println("---- Item added to the menu: " + name + " $"+ price +" ----");
                        System.out.println();
                        MenuIterator itr = myMenu.getAllItemsIterator();
                        while (itr.hasNext())
                        {
                            MenuItem item = itr.next();
                            System.out.println(item.getName()+" ----> $"+item.getPrice());
                        }
                    }
                    System.out.println();
                    break;
                }
                case 8: {
                    MenuIterator itr = myMenu.getAllItemsIterator();
                    while (itr.hasNext())
                    {
                        MenuItem item = itr.next();
                        System.out.println(item.getName() + " ----> $" + item.getPrice());
                    }
                    System.out.println();
                    System.out.println("Enter name of the item to remove: ");
                    scan.nextLine();
                    String name = scan.nextLine();
                    myMenu.removeItem(name);
                    System.out.println("---- " + name + " removed from the menu ----\n");
                    MenuIterator itr1 = myMenu.getAllItemsIterator();
                    while (itr1.hasNext())
                    {
                        MenuItem item = itr1.next();
                        System.out.println(item.getName() + " ----> $" + item.getPrice());
                    }
                    System.out.println();
                    break;
                }
                default:{
                    System.out.println("\nPlease put in a valid input.");
                }
            }

        }while(user_choice != 0);

    }
}
