import customer.Customer;
import admin.Admin;

public class Library {

    public static void main(String[] args) {
        Customer joe = new Customer("101");

        System.out.println(joe.findOne("11223344", "Stock.txt").toString());
    }
    

}
