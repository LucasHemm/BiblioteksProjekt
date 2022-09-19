import Niveau2_logik.TextUI;
import Niveau3_persistens.Borrower;
import Niveau3_persistens.Database;

import javax.xml.crypto.Data;

public class Menu {

    TextUI textUi = new TextUI();
    Database database = new Database();

    public void run() {
        boolean onOff = true;

        while (onOff) {

            String[] options = {"Create Borrower", "Create Book", "Create Loan", "Search", "Delete", "Show all loans", "Quit"};
            int choice = textUi.select("header", options, "footer");
            switch (choice) {

                case 0:
                    System.out.println("Please enter your adress");
                    String adress = textUi.get();
                    System.out.println("Please enter Name");
                    String name = textUi.get();
                    System.out.println("Please enter Postal code");
                    int postalCode = textUi.getInteger();
                    database.createBorrower(new Borrower(adress,name,postalCode));
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;


            }


        }
    }


}
