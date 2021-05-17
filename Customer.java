
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



import user.User;

// edit where and when items get removed from stock and when stock gets put back
 
public class Customer extends User {

    private  Basket myBasket = new Basket();//empty basket is created

    

    public Customer(String id){
        super(id);
    }


    public void addItemToBasket(String id){
        List<String> item= findOne(id, "Stock.txt");//uses the find one function to get book via id
        
        Books newItem = new Books(item);//creates a class instance of the book found
        myBasket.addToBasket(newItem);//calls the add to basket function which books the book in a list of type Books(the class)

    
    }

    public void removeItemFromBasket(String id){

        Books book;

        for (Books remove : myBasket.items) {//foreach loop through basket.items

            if (remove.isbn.equals(id)){//checks if ids match

                book=remove;//stores the book class found in the variable
                myBasket.removeFromBasket(book);//removes books from list
                break;//closes for loop

            }
            
        }
        

    }



    public boolean payForItems(String payMethod,String cardNumber,String securityCode,String email){

        
        float totalPrice=myBasket.getTotalPrice();// calls the basket function get total price


        if (payMethod.equals("Credit Card")){//if pay method chosen is a credit card
            CreditCard payment =new CreditCard("Credit Card", totalPrice, cardNumber, securityCode);//creates credit card
            payment.validKey();//validation to check credit card keys
            if (payment.paid){
                updateActivityLog("Credit Card", "Purchased");//logs that the the books have been bought
                myBasket.emptyBasket();//emptys basket
                return true;
            }

        }else if(payMethod.equals("PayPal")){
            PayPal payment = new PayPal("PayPal", totalPrice, email);
            payment.validKey();
            if (payment.paid){
                updateActivityLog("Paypal", "Purchased");
                
                myBasket.emptyBasket();
                return true;
            }
            
        }

        

        return false;

    }

    public void cancelItems(String payMethod){

         
         updateActivityLog(payMethod, "Cancelled");
         myBasket.cancelBasketStock();
         myBasket.emptyBasket();
    }

    public void updateActivityLog(String payMethod,String result){
        /*code to format the current date into the specified format*/ 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now =LocalDateTime.now();
        String date = dtf.format(now);


        

        try{
            

            FileWriter log = new FileWriter("ActivityLog.txt",true);

            if(!myBasket.items.isEmpty()){
                for (Books book  : myBasket.items) {
                    
                    log.write(this.id+","+this.postcode +","+book.retailPrice*book.quantity+","+book.isbn+
                    ","+book.quantity+","+result +","+payMethod+","+date+"\n");//writes the log description into txt file
                    
                }
                
            log.close();

            }else{
                System.out.println("empty");
            }



        }catch(IOException e){
            e.printStackTrace();
        }

        


    } 
}


class Books{
    protected String isbn,type,title,lang,genre,releaseDate,add1,add2;
    protected int quantity;
    protected float retailPrice;

    public Books(List<String> newBook){

        /* stores the book details in variables as attributes */
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


    public List<Books>  items = new ArrayList<>();//basket is a list of the books class

    

    public void addToBasket(Books newItem){
        boolean exists=false;

        if (newItem.quantity >0){//checks if the quantity is more than zero i.e out of stock
            
            for (Books booksInBasket : items) {
                if (booksInBasket.isbn.equals(newItem.isbn)){//performs a check if the book is already in the basket
                    booksInBasket.quantity+=1;//adds quantity to the book in the basket instead
                    updateStock(newItem.isbn, "remove",1);//updates stock by removing one
                    exists=true;
                    break;
                }
                
            }
            
    
            if (!exists){//if doersnt exist
                updateStock(newItem.isbn, "remove",1);
                newItem.quantity=1;//books quantity is set to 1
                items.add(newItem);}//adds book instance to a list of books

        }else{
            System.out.println("out of stock");
        }
        

        
    }

    public void removeFromBasket(Books book){

        updateStock(book.isbn, "add",1);//updates stock by removing one
        items.remove(book);

    }

    public float getTotalPrice(){

        float totalPrice=0;
        
        

        for (Books book : items) {//foreach loop through basket.items
            for(int i=1;i<=book.quantity;i++){

                totalPrice+=book.retailPrice;
            }
            
        }

        return totalPrice;

    }

    public void emptyBasket(){

        items.clear();
    }

    public void cancelBasketStock(){
        for (Books books : items) {
            updateStock(books.isbn, "add",books.quantity);
            
        }

    }

    public void updateStock(String id,String choice,int amount){

        String dataStr;// string variable to hold unformatted string after line is read
        List<String> dataArray=new ArrayList<>();// array variable to hold splitted string
        StringBuffer buff=new StringBuffer();
        boolean found=false;
        int quantity;
        String buffString;

        try {
           
            BufferedReader file = new BufferedReader(new FileReader("Stock.txt"));
            while ((dataStr=file.readLine()) !=null) {//reads each line and stores each line as a string in datastr

                if(!found){
                    
                        dataArray = Arrays.asList(dataStr.split("\\s*,\\s*"));//creates an array that splits the data by comma using regex
                        if (dataArray.get(0).equals(id)) {//checks if the id given as criteria is in the txt file
                            
                            quantity=Integer.parseInt(dataArray.get(7));//converts quantity string to an int 
                            
        
                            //either adds to quantity or removes depending on the choice
                            if (choice.equals("add")){

                                quantity+=amount;
                            }else if (choice.equals("remove")){
                                quantity-=amount;
                            }
   
                            dataArray.set(7,Integer.toString(quantity)); //sets it back to string and changes its value in the array

                            //code to convert array to string                           
                            buffString = dataArray.toString();
                            buffString= buffString.replace("[", "").replace("]", "");
                            buff.append(buffString+"\n");
                            found=true;
                            
                        }else{
                            buff.append(dataStr+"\n");//adds the line to a string buffer
                        }
                        

                }else{
                    buff.append(dataStr+"\n");

                }   
                        
                        

                
               
            }
            file.close();

            FileWriter stockFile = new FileWriter("Stock.txt");
            stockFile.write(buff.toString());
            stockFile.close();
            

        } catch ( IOException e) {
            ;
        }

        
        

    }
}

abstract class Pay{

    float amount;
    String payMethod;
    boolean paid = false;

    Pay(String payMethod,float amount){

        this.amount=amount;
        this.payMethod=payMethod;

    }

     public abstract void validKey();

    

}


class CreditCard extends Pay{
    private String cardNumber,securityCode;
    private static final String CARD_NUM_REGEX="^[0-9]{6}$";
    
    private static final String SEC_CODE_REGEX="^[0-9]{3}$";
    

    public CreditCard(String payMethod,float amount,String cardNumber,String securityCode){
        super(payMethod, amount);
        this.cardNumber=cardNumber;
        this.securityCode=securityCode;
    }

    public void validKey(){

        Pattern cardNum=Pattern.compile(CARD_NUM_REGEX);
        Pattern secCode =Pattern.compile(SEC_CODE_REGEX);

        Matcher cardMatch = cardNum.matcher(this.cardNumber);
        Matcher secMatch = secCode.matcher(this.securityCode);

        if (cardMatch.matches()&&secMatch.matches()){
            
            paid =true;

        }else{
            paid=false;
        }

    }
    
}



class PayPal extends Pay{

    private String email;
    private static final String EMAIL_REGEX="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public PayPal(String payMethod,float amount,String email){
        super(payMethod, amount);

        this.email=email;
        
    }


    public void validKey(){

        Pattern pattern =Pattern.compile(EMAIL_REGEX);
        Matcher match =pattern.matcher(this.email);

        if (match.matches()){
            paid =true;
            System.out.println("valid email");
        }
        

        

    }

}