import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {
    public static void main(String[] args) {
        File f = new File("UserAccounts.txt");
        User guy = new User("101", f);
        guy.print();
    }

    /* creates the attributes for the user */
   

    private String id, username, surname,houseNumber, postcode, city, role;

    public User(String id, File file) {
        // File file; //variable for the file where reader user is read from
        String[]userDataArray=findOne(id,file);

        this.id=userDataArray[0];
        username=userDataArray[1];
        surname=userDataArray[2];
        houseNumber=userDataArray[3];
        postcode=userDataArray[4];
        city=userDataArray[5];
        role=userDataArray[6];

        
    }

    
    private String[] findOne(String id, File file){

        Scanner fileReader; // scanner variable is created to read the file and return lines
        String userDataStr; // string variable to hold unformatted string after line is read
        String[] userDataArray={""}; // array variable to hold splitted string
        boolean found = false;

        try {
            fileReader = new Scanner(file);
            while (!found) {
                userDataStr = fileReader.nextLine(); //reads/returns current line from the txt file
                userDataArray = userDataStr.split(",");//creates an array that splits the data by comma
                if (userDataArray[0].equals(id)) {//checks if the id given as criteria is in the txt file

                    for (int i=0;i<userDataArray.length;i++) {
                        userDataArray[i]=userDataArray[i].strip(); // removes all trailing and leading whitespace
                        
                    }
                    fileReader.close();
                    return userDataArray;
                }

            }

        } catch (FileNotFoundException e) {
            ;
        }

        return userDataArray;
        

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


}