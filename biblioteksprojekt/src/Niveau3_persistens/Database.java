package Niveau3_persistens;

import java.sql.*;

public class Database {

    public void createBorrower(Borrower borrower)
    {
        try {
           Connection connection = ConnectionConfiguration.getConnection();

            //Saves the users information
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bibliotek.lånertabel (adresse, navn, postnummer)" +
                    " VALUES(?,?,?)");
            statement.setString(1,borrower.getAdress());
            statement.setString(2,borrower.getName());
            statement.setInt(3,borrower.getPostalCode());

            int result = statement.executeUpdate();
        } catch(SQLException e)
        {
            System.out.println("Name has already been registered");
//            e.printStackTrace();
        }
    }

    public void createBook(Book book)
    {
        try {
            Connection connection = ConnectionConfiguration.getConnection();

            //Saves the users information
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bibliotek.bogtabel (forfatter, titel, udgivelsesår)" +
                    " VALUES(?,?,?)");
            statement.setString(1,book.getAuthor());
            statement.setString(2,book.getTitle());
            statement.setInt(3,book.getReleaseYear());

            int result = statement.executeUpdate();
        } catch(SQLException e)
        {
            System.out.println("Book has already been registered");
//            e.printStackTrace();
        }
    }

    public void showAllLoans(){
        try {
            Connection connection = ConnectionConfiguration.getConnection();

            PreparedStatement statement = connection.prepareStatement("select * from bibliotek.listOfLoans order by udlånsid");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                System.out.println( "udlånsID: " + resultSet.getString("udlånsid") + " - Navn på låner: " + resultSet.getString("navn") +
                        " - Titel på udlånt bog: "  + resultSet.getString("titel"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void currentLoans(String name, String language){
        try {
            Connection connection = ConnectionConfiguration.getConnection();

            printBorrowers();
            PreparedStatement statement = connection.prepareStatement("select * from bibliotek.listOfLoans where navn = ?");

            statement.setString(1,name);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                switch (language) {
                    case "engelsk":
                        System.out.println( "borrowerID: " + resultSet.getString("udlånsid") + " - Name of borrower: "
                                + resultSet.getString("navn") + " - Book title: "  +
                                resultSet.getString("titel"));
                        break;
                    case "dansk":
                        System.out.println( "udlånsID: " + resultSet.getString("udlånsid") + " - Navn på låner: "
                                + resultSet.getString("navn") + " - Titel på udlånt bog: "  +
                                resultSet.getString("titel"));
                        break;
                    case "spansk":
                        System.out.println( "identificación de préstamo: " + resultSet.getString("udlånsid") + " - Nombre del Prestatario: "
                                + resultSet.getString("navn") + " - Titulo del libro: "  +
                                resultSet.getString("titel"));
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createLoan(String name, String titel){
        try {
            Connection connection = ConnectionConfiguration.getConnection();

            //Saves the users information
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bibliotek.udlånstabel (bogid, lånerid)" +
                    " VALUES(?,?)");
            statement.setInt(1,getIndexFromTitel(titel));
            statement.setInt(2, getIndexFromName(name));

            int result = statement.executeUpdate();
        } catch(SQLException e)
        {
            System.out.println("Either name or title has been spelled incorrectly");
            //e.printStackTrace();
        }
    }
    public void amountBorrowedByArea(int postalCode, String language) {
        try {
            Connection connection = ConnectionConfiguration.getConnection();

            PreparedStatement statement = connection.prepareStatement("select titel, count(*) as total from bibliotek.bookbyarea where postnummer = ? group by titel");

            statement.setInt(1,postalCode);

            ResultSet resultSet = statement.executeQuery();


            while(resultSet.next()){
                switch (language) {
                    case "engelsk":
                        System.out.println("Book title: " + resultSet.getString("titel") +
                                " - times borrowed: " + resultSet.getInt("total"));
                        break;
                    case "dansk":
                        System.out.println("Bog titel: " + resultSet.getString("titel") +
                                " - antal gange lånt: " + resultSet.getInt("total"));
                        break;
                    case "spansk":
                        System.out.println("Titulo del libro: " + resultSet.getString("titel") +
                                " - número de veces prestado: " + resultSet.getInt("total"));
                        break;
                }

            }
        } catch (SQLException e) {
            System.out.println("Postal code is not in database");
            //e.printStackTrace();
        }
    }

    public void printBorrowers(){

        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionConfiguration.getConnection();
            statement = connection.prepareStatement("select * from bibliotek.lånertabel");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                System.out.println(resultSet.getString("navn") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void printBooks(){


        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionConfiguration.getConnection();
            statement = connection.prepareStatement("select * from bibliotek.bogtabel");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                System.out.println(resultSet.getString("titel")+ "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private int getIndexFromName(String name)
    {
        int ID = 0;
        PreparedStatement statement;
        try {
            Connection connection = ConnectionConfiguration.getConnection();
            {
                statement = connection.prepareStatement("SELECT * FROM bibliotek.lånertabel where navn = ?");
            }

            statement.setString(1,name);
            ResultSet result = statement.executeQuery();

            while (result.next())
            {
                ID = result.getInt("lånerid");
            }

        } catch(SQLException e)
        {
            System.out.println("The name was spelled incorrectly");
            //e.printStackTrace();
        }
        return ID;
    }

    private int getIndexFromTitel(String titel)
    {
        int ID = 0;
        PreparedStatement statement;
        try {
            Connection connection = ConnectionConfiguration.getConnection();
            {
                statement = connection.prepareStatement("SELECT * FROM bibliotek.bogtabel where titel = ?");
            }

            statement.setString(1,titel);
            ResultSet result = statement.executeQuery();

            while (result.next())
            {
                ID = result.getInt("bogid");
            }

        } catch(SQLException e)
        {
            System.out.println("The title was spelled incorrectly");
            //e.printStackTrace();
        }
        return ID;
    }

}
