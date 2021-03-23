import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User {
    public static void main(String[] args) {
        File f = new File("UserAccounts.txt");
        User guy = new User("101", f);
        guy.print();
        guy.findAll(f);
    }

    /* creates the attributes for the user */
   

    private String id, username, surname,houseNumber, postcode, city, role;

    public User(String id, File file) {

        List<String> userDataArray=findOne(id,file);

        this.id=userDataArray.get(0);
        username=userDataArray.get(1);
        surname=userDataArray.get(2);
        houseNumber=userDataArray.get(3);
        postcode=userDataArray.get(4);
        city=userDataArray.get(5);
        role=userDataArray.get(6);

        
    }

   

    public void print(){
        System.out.println(this.id);
        System.out.println(username);
        System.out.println(surname);
        System.out.println(houseNumber);
        System.out.println(postcode);
        System.out.println(city);
        System.out.println(role);

    }

    private List<String> findOne(String id, File file){

        Scanner fileReader; // scanner variable is created to read the file and return lines
        String dataStr; // string variable to hold unformatted string after line is read
         // array variable to hold splitted string
        boolean found = false;
        List<String> dataArray=new ArrayList<>();

        try {
            fileReader = new Scanner(file);
            while (!found) {
                
                dataStr = fileReader.nextLine(); //reads/returns current line from the txt file
                //dataArray = dataStr.split(",");//creates an array that splits the data by comma
                dataArray = Arrays.asList(dataStr.split("\\s*,\\s*"));//creates an array that splits the data by comma using regex
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


    public List<List<String>> findAll(File file) {

        Scanner fileReader; // scanner variable is created to read the file and return lines
        String dataStr; // string variable to hold unformatted string after line is read
        List<String> dataField; //array variable to hold splitted data field strings to go in a 2d array
        List<List<String>> dataArray=new ArrayList<>(); // 2darray variable to hold splitted data field strings
        

        int j =0;

        try {
            fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                
                dataStr = fileReader.nextLine(); //reads/returns current line from the txt file
                dataField = Arrays.asList(dataStr.split("\\s*,\\s*"));//creates an array that splits the data by comma using regex

                dataArray.add(j,dataField);//adds the data field list to data array to form 2d array
                j++;
                
            }
            fileReader.close();

        } catch (FileNotFoundException e) {
            ;
        }

        System.out.println(dataArray.toString());

        return dataArray;

        

        
        
    }




}