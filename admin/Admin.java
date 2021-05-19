package admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import user.User;

public class Admin extends User{

    public Admin(String id){
        super(id);
    }

    
    public void addNewBook(List<String> newBook){//add a new book to the database
        String dataStr;// string variable to hold unformatted string after line is read
        List<String> dataArray=new ArrayList<>();// array variable to hold splitted string
        StringBuffer buff=new StringBuffer();
        boolean found=false;
        int quantity;
        String buffString;
        int amount=Integer.parseInt(newBook.get(7)) ;

        StringBuilder book = new StringBuilder("");

        //converts array list to string;
        for (String i : newBook){
            book.append(i).append(",");
        }

        String bookList = book.toString();

        if (bookList.length() > 0)
            bookList
                = bookList.substring(
                    0, bookList.length() - 1);
  
        System.out.println(bookList);        
    
            try {

                InputStream is= this.getClass().getClassLoader().getResourceAsStream("Stock.txt");//gets file as an input stream
               
                BufferedReader file = new BufferedReader(new InputStreamReader(is));//stores it in a buffer read
               
                
                while ((dataStr=file.readLine()) !=null) {//reads each line and stores each line as a string in datastr
    
                    if(!found){
                        
                            dataArray = Arrays.asList(dataStr.split("\\s*,\\s*"));//creates an array that splits the data by comma using regex
                            if (dataArray.get(0).equals(newBook.get(0))) {//checks if the id given as criteria is in the txt file
                                
                                quantity=Integer.parseInt(dataArray.get(7));//converts quantity string to an int 
                                
                                quantity+=amount;
                                System.out.println("dd");
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
                stockFile.write(buff.toString());//writes all data back to the txt file
                stockFile.close();

                if (!found){//if not found then that means it is a new book and so is added to the end of the file

                FileWriter addBook = new FileWriter("Stock.txt",true);

                addBook.write(bookList+"\n");
                addBook.close();  


                }

              
                            
    
            } catch ( IOException e) {
                ;
            }
    
            
    }
}



