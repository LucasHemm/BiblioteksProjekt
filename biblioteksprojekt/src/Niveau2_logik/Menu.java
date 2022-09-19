package Niveau2_logik;
import Niveau3_persistens.Database;

public class Menu {
    Language language = new Language("engelsk");

    public void run() {
        language.changeLanguage();
        boolean onOff = true;

        while (onOff) {

            int choice = language.menuChoice();

            switch (choice) {
                case 0:
                    language.createBorrower();
                    break;
                case 1:
                    language.createBook();
                    break;
                case 2:
                    language.createLoan();
                    break;
                case 3:
                    language.search();
                    break;
                case 4:
                    language.borrowedInArea();
                    break;
                case 5:
                    language.showLoans();
                    break;
                case 6:
                    language.changeLanguage();
                    break;
                case 7:
                    onOff = false;
                    break;
            }
        }
    }
}
