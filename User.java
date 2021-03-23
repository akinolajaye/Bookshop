import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        Scanner fileReader; // scanner variable is created to read the file and return lines
        String userDataStr; // string variable to hold unformatted string after line is read
        String[] userDataArray; // array variable to hold splitted string
        boolean found = false;

        try {
            fileReader = new Scanner(file);
            while (!found) {
                userDataStr = fileReader.nextLine();
                userDataArray = userDataStr.split(",");
                if (userDataArray[0].equals(id)) {

                    for (int i=0;i<userDataArray.length;i++) {
                        userDataArray[i]=userDataArray[i].strip();
                        
                    }

                    this.id=userDataArray[0];
                    username=userDataArray[1];
                    surname=userDataArray[2];
                    houseNumber=userDataArray[3];
                    postcode=userDataArray[4];
                    city=userDataArray[5];
                    role=userDataArray[6];


                    found = true;

                }

            }

        } catch (FileNotFoundException e) {
            ;
        }


        
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