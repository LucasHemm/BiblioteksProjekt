import Niveau2_logik.TextUI;
import Niveau3_persistens.Book;
import Niveau3_persistens.Borrower;
import Niveau3_persistens.Database;

import javax.xml.crypto.Data;

public class Menu {

    TextUI textUi = new TextUI();
    Database database = new Database();

    public void run() {
        boolean onOff = true;

        while (onOff) {

            String[] options = {"Create Borrower", "Create Book", "Create Loan", "Search", "Amount borrowed by area", "Show all loans", "Quit"};
            int choice = textUi.select("", options, "");
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
                    System.out.println("Please enter Author");
                    String author = textUi.get();
                    System.out.println("Please enter Title");
                    String title = textUi.get();
                    System.out.println("Please enter Release year");
                    int releaseYear = textUi.getInteger();
                    database.createBook(new Book(author,title,releaseYear));
                    break;
                case 2:
                    database.printBorrowers();
                    System.out.println("Please enter name of borrower");
                    String borrowerName = textUi.get();
                    database.printBooks();
                    System.out.println("Please enter title of the book");
                    String bookTitle = textUi.get();
                    database.createLoan(borrowerName,bookTitle);
                    break;
                case 3:
                    System.out.println("Enter name of borrower");
                        database.currentLoans(textUi.get());
                    break;
                case 4:
                    System.out.println("Enter postalcode");
                    database.amountBorrowedByArea(textUi.getInteger());
                    break;
                case 5:
                    database.showAllLoans();
                    break;
                case 6:
                    onOff = false;
                    System.out.println("goodbye, and thanks for using my ");
                    break;


            }


        }
    }


}
