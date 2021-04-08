package user;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User {

    
    private String id, username, surname,houseNumber, postcode, city, role;
    private String filename;
    private String regex ="\\s*,\\s*";//defines a regex that removes commas and trailing white space

    /* creates the attributes for the user */
    protected User(String id, String filename) {

        this.filename=filename;
        
        List<String> userDataArray=findOne(id,this.filename);

        this.id=userDataArray.get(0);
        username=userDataArray.get(1);
        surname=userDataArray.get(2);
        houseNumber=userDataArray.get(3);
        postcode=userDataArray.get(4);
        city=userDataArray.get(5);
        role=userDataArray.get(6);

        
    }



    public List<String> findOne(String id,String filename){

        File file= new File(filename);
        Scanner fileReader; // scanner variable is created to read the file and return lines
        String dataStr; // string variable to hold unformatted string after line is read
         // array variable to hold splitted string
        boolean found = false;
        List<String> dataArray=new ArrayList<>();

        try {
            fileReader = new Scanner(file);
            while (!found) {
                
                dataStr = fileReader.nextLine(); //reads/returns current line from the txt file
                
                dataArray = Arrays.asList(dataStr.split(regex));//creates an array that splits the data by comma using regex
                if (dataArray.get(0).equals(id)) {//checks if the id given as criteria is in the txt file

                    fileReader.close();
                    return dataArray;
                }

            }

        } catch (FileNotFoundException e) {
            ;
        }

        return dataArray;
        

    }


    public List<List<String>> findAll(String filename) {

        File file= new File(filename);

        Scanner fileReader; // scanner variable is created to read the file and return lines
        String dataStr; // string variable to hold unformatted string after line is read
        List<String> dataField; //array variable to hold splitted data field strings to go in a 2d array
        List<List<String>> dataArray=new ArrayList<>(); // 2darray variable to hold splitted data field strings
        

        int j =0;

        try {
            fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                
                dataStr = fileReader.nextLine(); //reads/returns current line from the txt file
                dataField = Arrays.asList(dataStr.split(regex));//creates an array that splits the data by comma using regex

                dataArray.add(j,dataField);//adds the data field list to data array to form 2d array
                j++;
                
            }
            fileReader.close();

        } catch (FileNotFoundException e) {
            ;
        }



        return dataArray;

        

        
        
    }


    public List<List<String>> searchBooks(String filename,List<String> searchField){

        File file= new File(filename);//creates file variable
        Scanner fileReader; // scanner variable is created to read the file and return lines
        List<List<String>> resultArray=new ArrayList<>(); // 2darray variable to hold splitted data field strings
        int i =0;// initializes i for the index when calling 'add' for the resultArray List
        
        try{
            fileReader=new Scanner(file);//scans file

            while(fileReader.hasNextLine()){//loop while the textfile has another line
                String searchId =Arrays.asList(fileReader.nextLine().split(regex)).get(0);//gets and converts the text file line the retrieves the id
                List<String> dataField=findOne(searchId, filename);//calls the find one function to get the single data row based on id
                if (dataField.containsAll(searchField)){ //checks if the data being searched for is in the data field
                    resultArray.add(i,dataField);//adds it to result array if true
                    i++;




                }
            }

        }catch(FileNotFoundException e){
            ;
        }

        return resultArray;
        
        
      

        
        


    }

    public List<List<String>> viewBooks(String filename){
        
         
        return findAll(filename);
    }

    

}