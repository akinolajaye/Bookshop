package user;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class User {

    
    public String id, username, surname,houseNumber, postcode, city, role;
    public String filename;
    protected String regex ="\\s*,\\s*";//defines a regex that removes commas and trailing white space

    /* creates the attributes for the user */
    public User(String id) {

        this.filename="UserAccounts.txt";
        
        List<String> userDataArray=findOne(id,this.filename);

        this.id=userDataArray.get(0);
        username=userDataArray.get(1);
        surname=userDataArray.get(2);
        houseNumber=userDataArray.get(3);
        postcode=userDataArray.get(4);
        city=userDataArray.get(5);
        role=userDataArray.get(6);

        
    }
    public User(){
        
    }

    public static void main(String[] args) {
        
    }


    public List<String> findOne(String id,String filename){
  
        String dataStr;// string variable to hold unformatted string after line is read
        List<String> dataArray=new ArrayList<>();// array variable to hold splitted string

        try {
           
           
            InputStream is= this.getClass().getClassLoader().getResourceAsStream(filename);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader file = new BufferedReader(isr);
            
            while ((dataStr=file.readLine()) !=null) {
                
                //dataStr = fileReader.nextLine(); //reads/returns current line from the txt file
                
                dataArray = Arrays.asList(dataStr.split(regex));//creates an array that splits the data by comma using regex
                if (dataArray.get(0).equals(id)) {//checks if the id given as criteria is in the txt file

                    //fileReader.close();
                    file.close();
                    return dataArray;
                }
                
            }
            file.close();
            

        } catch ( IOException e) {
            ;
        }

        dataArray=new ArrayList<>();
        return dataArray;
        

    }


    public List<List<String>> findAll(String filename) {

  
        String dataStr; // string variable to hold unformatted string after line is read
        List<String> dataField; //array variable to hold splitted data field strings to go in a 2d array
        List<List<String>> dataArray=new ArrayList<>(); // 2darray variable to hold splitted data field strings


        int j =0;

        try {
            InputStream is= this.getClass().getClassLoader().getResourceAsStream(filename);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader file = new BufferedReader(isr);
            ;
            while ((dataStr=file.readLine())!=null) {//reads/returns current line from the txt file
                
                 
                dataField = Arrays.asList(dataStr.split(regex));//creates an array that splits the data by comma using regex

                dataArray.add(j,dataField);//adds the data field list to data array to form 2d array
                j++;
                
            }
            

        } catch (IOException e) {
            ;
        }



        return dataArray;

        

        
        
    }


    public List<List<String>> searchBooks(String filename,List<String> searchField){

        String dataStr;

        List<List<String>> resultArray=new ArrayList<>(); // 2darray variable to hold splitted data field strings
        int i =0;// initializes i for the index when calling 'add' for the resultArray List
        
        try{
            
            InputStream is= this.getClass().getClassLoader().getResourceAsStream(filename);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader file = new BufferedReader(isr);

            while((dataStr=file.readLine())!=null){//loop while the textfile has another line
                String searchId =Arrays.asList(dataStr.split(regex)).get(0);//gets and converts the text file line the retrieves the id
                List<String> dataField=findOne(searchId, filename);//calls the find one function to get the single data row based on id

                if (!searchField.isEmpty()){

                    if (dataField.containsAll(searchField)){ //checks if the data being searched for is in the data field
                        resultArray.add(i,dataField);//adds it to result array if true
                        i++;

                    }
                }

            }

        }catch(IOException e){
            ;
        }

        

        return resultArray;
        
        
      

        
        


    }

    public List<List<String>> viewBooks(String filename){
        
         
        return findAll(filename);
    }

    

}