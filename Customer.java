import java.util.ArrayList;
import java.util.List;

import user.User;


public class Customer extends User {

    private  Basket myBasket = new Basket();

    

    public Customer(String id){
        super(id);
    }

    public static void main(String[] args) {
        
        Customer joe = new Customer("101");

        joe.addItemToBasket("22334455");
        joe.addItemToBasket("11224455");
        System.out.println(joe.myBasket.items.get(1).title);
        System.out.println(joe.myBasket.items.get(0).title);
        

        

        
    }


    public void addItemToBasket(String id){
        List<String> item= findOne(id, "Stock.txt");//uses the find one function to get book via id
        
        Books newItem = new Books(item);//creates a class instance of the book found
        myBasket.addToBasket(newItem);//calls the add to basket function

    
    }



    public void removeItemFromBasket(String id){

        Books book;

        for (Books remove : myBasket.items) {//foreach loop through basket.items

            if (remove.isbn.equals(id)){//checks if ids match

                book=remove;
                myBasket.removeFromBasket(book);
                break;

            }
            
        }
        

    }



    public boolean payForItems(String payMethod,String cardNumber,String securityCode,String email){

        

        int totalPrice=0;
        

        for (Books book : myBasket.items) {//foreach loop through basket.items

            totalPrice+=book.retailPrice;
        }

        if (payMethod.equals("Credit Card")){
            CreditCard payment =new CreditCard("Credit Card", totalPrice, cardNumber, securityCode);
            payment.validKey();//add validation
            if (payment.paid){
                return true;
            }
        }

        

        return false;

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


    public List<Books> items = new ArrayList<>();//basket is a list of the books class

    public void addToBasket(Books newItem){
        
        items.add(newItem);//adds book instance to a list of books
    }

    public void removeFromBasket(Books book){

        items.remove(book);

        
    }




}

abstract class Pay{

    int amount;
    String payMethod;
    boolean paid = false;

    Pay(String payMethod,int amount){

        this.amount=amount;
        this.payMethod=payMethod;

    }

     public abstract void validKey();

    

}


class CreditCard extends Pay{
    String cardNumber,securityCode;

    public CreditCard(String payMethod,int amount,String cardNumber,String securityCode){
        super(payMethod, amount);
        this.cardNumber=cardNumber;
        this.securityCode=securityCode;
    }

    public void validKey(){

        System.out.println("hello");

        paid =true;

    }
    
}