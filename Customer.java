import user.User;


public class Customer extends User {
    public Customer(String id, String filename){
        super(id, filename);
    }

    public static void main(String[] args) {
        
        Customer guy = new Customer("101", "UserAccounts.txt");

        System.out.println(guy.viewBooks());
    }


    
}
