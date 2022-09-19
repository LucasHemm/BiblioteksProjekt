import Niveau2_logik.TextUI;

public class Menu {

    TextUI textUi = new TextUI();

    public void run() {
        boolean onOff = true;

        while (onOff) {

            String[] options = {"Create Borrower", "Create Book", "Create Loan", "Search", "Delete", "Show all loans", "Quit"};
            int choice = textUi.select("header", options, "footer");
            switch (choice) {

                case 0:

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
