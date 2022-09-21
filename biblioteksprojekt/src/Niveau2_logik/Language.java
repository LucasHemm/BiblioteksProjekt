package Niveau2_logik;

import Niveau2_logik.TextUI;
import Niveau3_persistens.Book;
import Niveau3_persistens.Borrower;
import Niveau3_persistens.Database;

public class Language {
    private String language;
    private Database database = new Database();
    private TextUI textUi = new TextUI();

    public Language(String language) {
        this.language = language;
    }

    protected String changeLanguage(){
        System.out.println("Write engelsk for english\nSkriv dansk for dansk\nEscribir spansk para español");
        String chosenLanguage=textUi.get().toLowerCase();
        String selectedLanguage="";
        switch (chosenLanguage){
            case "dansk":
                this.language="dansk";
                selectedLanguage="Sproget er blevet ændret til dansk.";
                break;
            case "spansk":
                this.language="spansk";
                selectedLanguage="El idioma ha sido cambiado a español.";
                break;
            default:
                this.language="engelsk";
                selectedLanguage="The language has been changed to english.";
                break;
        }
        return selectedLanguage;
    }

    protected int menuChoice(){
        int choice = 0;
        String[] options;
        switch (this.language) {
            case "engelsk":
                System.out.println("English was chosen");
                options = new String[]{"Create Borrower", "Create Book", "Create Loan", "Search", "Amount borrowed by area", "Show all loans","Change language","Create postal code","Quit"};
                choice = textUi.select("", options, "");
                break;
            case "dansk":
                System.out.println("Dansk blev valgt");
                options = new String[]{"Opret låner", "Opret bog", "opreet udlån", "Søg", "Antal lånte bøger efter område", "Vis alle lån", "Skift sprog","Opret postnummer","Afslut"};
                choice = textUi.select("", options, "");
                break;
            case "spansk":
                System.out.println("El español fue elegido");
                options = new String[]{"Crear prestatario", "Crear libro", "préstamo creado", "Buscar", "Número de libros prestados por área", "Mostrar todos los préstamos","Cambiar idioma","Crear Código postal", "Salir"};
                choice = textUi.select("", options, "");
                break;
        }
        return choice;
    }

    protected void createBorrower(){

        switch (this.language) {
            case "engelsk":
                System.out.println("Please enter adress");
                break;
            case "dansk":
                System.out.println("Indsæt adresse");
                break;
            case "spansk":
                System.out.println("inserta dirección");
                break;
        }
        String adress = textUi.get();

        switch (this.language) {
            case "engelsk":
                System.out.println("Please enter Name");
                break;
            case "dansk":
                System.out.println("Indsæt navn");
                break;
            case "spansk":
                System.out.println("inserta nombre");
                break;
        }
        String name = textUi.get();


        switch (this.language) {
            case "engelsk":
                System.out.println("Please enter Postal code");
                break;
            case "dansk":
                System.out.println("Indsæt postnummer");
                break;
            case "spansk":
                System.out.println("Insertar código postal");
                break;
        }
        int postalCode = textUi.getInteger();
        database.createBorrower(new Borrower(adress,name,postalCode));
    }


    protected void createBook(){
        switch (this.language) {
            case "engelsk":
                System.out.println("Please enter name of the Author");
                break;
            case "dansk":
                System.out.println("Indsæt forfatterens navn");
                break;
            case "spansk":
                System.out.println("Insertar el nombre del autor");
                break;
        }
        String authorName = textUi.get();
        switch (this.language) {
            case "engelsk":
                System.out.println("Please enter title of the book");
                break;
            case "dansk":
                System.out.println("Indsæt bogens titel");
                break;
            case "spansk":
                System.out.println("Insertar el título del libro");
                break;
        }
        String bookTitle = textUi.get();

        switch (this.language) {
            case "engelsk":
                System.out.println("Please enter release year");
                break;
            case "dansk":
                System.out.println("Indsæt udgivelsesår");
                break;
            case "spansk":
                System.out.println("Insertar año de publicación");
                break;
        }
        int releaseYear = textUi.getInteger();

        database.createBook(new Book(authorName,bookTitle,releaseYear));
    }

    protected void createLoan(){
        database.printBorrowers();
        switch (this.language) {
            case "engelsk":
                System.out.println("Please name of borrower");
                break;
            case "dansk":
                System.out.println("Indsæt lånerens navn");
                break;
            case "spansk":
                System.out.println("Inserte el nombre del prestatario");
                break;
        }
        String borrowerName = textUi.get();

        database.printBooks();
        switch (this.language) {
            case "engelsk":
                System.out.println("Please enter title of the book");
                break;
            case "dansk":
                System.out.println("Indsæt bogens titel");
                break;
            case "spansk":
                System.out.println("Insertar el título del libro");
                break;
        }
        String bookTitle = textUi.get();

        database.createLoan(borrowerName,bookTitle);
    }
    protected void search(){
        switch (this.language) {
            case "engelsk":
                System.out.println("Please name of borrower");
                break;
            case "dansk":
                System.out.println("Indsæt lånerens navn");
                break;
            case "spansk":
                System.out.println("Inserte el nombre del prestatario");
                break;
        }
        database.currentLoans(textUi.get(),this.language);
    }

    protected void borrowedInArea(){
        switch (this.language) {
            case "engelsk":
                System.out.println("Enter postalcode");
                break;
            case "dansk":
                System.out.println("Indsæt postnummer");
                break;
            case "spansk":
                System.out.println("Insertar código postal");
                break;
        }
        database.amountBorrowedByArea(textUi.getInteger(), this.language);
    }

    protected void showLoans(){
        database.showAllLoans();
    }

    public void createPostalCode() {
        int postalCode = 0;
        String cityName = "";
        switch (this.language) {
            case "engelsk":
                System.out.println("Enter postalcode");
                postalCode = textUi.getInteger();
                System.out.println("Enter name of city");
                cityName = textUi.get();
                break;
            case "dansk":
                System.out.println("Indsæt postnummer");
                postalCode = textUi.getInteger();
                System.out.println("Skriv navn på by");
                cityName = textUi.get();
                break;
            case "spansk":
                System.out.println("Insertar código postal");
                postalCode = textUi.getInteger();
                System.out.println("Insertar nombre de la ciudad");
                cityName = textUi.get();
                break;
        }
        database.createPostalCode(postalCode,cityName);
    }
}
