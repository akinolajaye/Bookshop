import java.util.ArrayList;
import java.util.List;

import user.User;


public class Customer extends User {

    public  Basket myBasket = new Basket();

    public Customer(String id, String filename){
        super(id, filename);
    }

    public static void main(String[] args) {
        
        Customer joe = new Customer("101", "UserAccounts.txt");

        joe.addItemToBasket("22334455");
        System.out.println(joe.myBasket.items.get(0).title);

        

        
    }


    public void addItemToBasket(String id){
        List<String> item= findOne(id, "Stock.txt");//uses the find one function to get book via id
        
        Books newItem = new Books(item);//creates a class instance of the book found
        myBasket.addToBasket(newItem);//calls the add to basket function

        

        

       

    }

}


class Books{
    String isbn,type,title,lang,genre,releaseDate,add1,add2;
    int quantity;
    float retailPrice;

    public Books(List<String> newBook){

        isbn=newBook.get(0);
        type=newBook.get(1);
        title=newBook.get(2);
        lang=newBook.get(3);
        genre=newBook.get(4);
        releaseDate=newBook.get(5);
        retailPrice=Float.parseFloat(newBook.get(6));
        quantity = Integer.parseInt(newBook.get(7));
        add1=newBook.get(8);
        add2=newBook.get(9);


    }
}



class Basket{

    public List<Books> items = new ArrayList<>();

    public void addToBasket(Books newItem){
        
        items.add(newItem);//adds book instance to a list of books
    }

}