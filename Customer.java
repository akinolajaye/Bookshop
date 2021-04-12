
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


public class Customer extends User {

    private  Basket myBasket = new Basket();
    private float totalPrice=0;

    

    public Customer(String id){
        super(id);
    }

    public static void main(String[] args) {
        
        Customer joe = new Customer("101");

        joe.addItemToBasket("22334455");
        joe.addItemToBasket("11224455");
        System.out.println(joe.myBasket.items);
        System.out.println(joe.myBasket.items.get(1).title);
        System.out.println(joe.myBasket.items.get(0).title);
        joe.payForItems("Credit Card", "4756353625344", "465", "");
        System.out.println(joe.findOne("ff", "ActivityLog.txt"));
        joe.myBasket.updateStock("22446688","add");
        
        
       
        

        

        
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

    public float totalPrice(){

        this.totalPrice=0;
        
        

        for (Books book : myBasket.items) {//foreach loop through basket.items

            this.totalPrice+=book.retailPrice;
        }

        return totalPrice;

    }



    public boolean payForItems(String payMethod,String cardNumber,String securityCode,String email){

        
        float price=totalPrice();


        if (payMethod.equals("Credit Card")){
            CreditCard payment =new CreditCard("Credit Card", price, cardNumber, securityCode);
            payment.validKey();//add validation
            if (payment.paid){
                myBasket.emptyBasket();
                System.out.println(totalPrice);
                return true;
            }

        }else if(payMethod.equals("PayPal")){
            PayPal payment = new PayPal("PayPal", price, email);
            payment.validKey();
            if (payment.paid){
                myBasket.emptyBasket();
                return true;
            }
            
        }

        

        return false;

    }


    public void updateActivityLog(String payMethod,String result){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now =LocalDateTime.now();
        String date = dtf.format(now);


        

        try{

            FileWriter log = new FileWriter("ActivityLog.txt",true);
            for (Books book  : myBasket.items) {
                log.write(this.id+","+this.postcode +","+book.retailPrice+","+"quantity"+","+result +","+payMethod+","+date+"\n");
                
            }
            
            log.close();


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


    public void emptyBasket(){
        items.clear();
    }

    public void updateStock(String id,String choice){

        String dataStr;// string variable to hold unformatted string after line is read
        List<String> dataArray=new ArrayList<>();// array variable to hold splitted string
        StringBuffer buff=new StringBuffer();
        int found=0;
        int quantity;

        try {
           
            BufferedReader file = new BufferedReader(new FileReader("Stock.txt"));
            while ((dataStr=file.readLine()) !=null) {

                switch(found){
                    case 0:
                        dataArray = Arrays.asList(dataStr.split("\\s*,\\s*"));//creates an array that splits the data by comma using regex
                        if (dataArray.get(0).equals(id)) {//checks if the id given as criteria is in the txt file
                            String buffString;
                            quantity=Integer.parseInt(dataArray.get(7)); 
                            
        
                            //code to convert array to string
                            if (choice.equals("add")){

                                quantity+=1;
                            }else if (choice.equals("remove")){
                                quantity-=1;
                            }

                                    
                            dataArray.set(7,Integer.toString(quantity));                            
                            buffString = dataArray.toString();
                            buffString= buffString.replace("[", "").replace("]", "").replace(" ", "");
                            buff.append(buffString+"\n");
                            found=1;
                            
                        }else{
                            buff.append(dataStr+"\n");
                        }
                        break;

                    case 1:
                        buff.append(dataStr+"\n");
                        break;

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
    private static final String CARD_NUM_REGEX="^[0-9]{13}$|^[0-9]{16}$";
    
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
            System.out.println("Valid Card number");


        }else{
            System.out.println("invalid");
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