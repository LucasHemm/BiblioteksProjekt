package Niveau3_persistens;

import java.sql.*;

public class Database {
    //Fields to connect to database
    private final String JdbcUrl = "jdbc:mysql://localhost:3306/bibliotek?serverTimezone=CET&useSSL=false";
    private final String username = "root";
    private final String password = ""; //Remember to change password**********************
    private Connection connection = null;


    public void createBorrower(Borrower borrower)
    {
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);

            //Saves the users information
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bibliotek.lånertabel (adresse, navn, postnummer)" +
                    " VALUES(?,?,?)");
            statement.setString(1,borrower.getAdress());
            statement.setString(2,borrower.getName());
            statement.setInt(3,borrower.getPostalCode());

            int result = statement.executeUpdate();
        } catch(SQLException e)
        {
            System.out.println("Name has been spelled incorrectly");
//            e.printStackTrace();
        }
    }

    public void createBook(Book book)
    {
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);

            //Saves the users information
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bibliotek.bogtabel (forfatter, titel, udgivelsesår)" +
                    " VALUES(?,?,?)");
            statement.setString(1,book.getAuthor());
            statement.setString(2,book.getTitle());
            statement.setInt(3,book.getReleaseYear());

            int result = statement.executeUpdate();
        } catch(SQLException e)
        {
            System.out.println("Title has been spelled incorrectly");
//            e.printStackTrace();
        }
    }

    public void showAllLoans(){
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);

            PreparedStatement statement = connection.prepareStatement("select * from bibliotek.listOfLoans");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                System.out.println( "udlånsID: " + resultSet.getString("udlånsid") + " - Navn på låner: " + resultSet.getString("navn") +
                        " - Titel på udlånt bog: "  + resultSet.getString("titel"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void currentLoans(String name){
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);

            PreparedStatement statement = connection.prepareStatement("select * from bibliotek.listOfLoans where navn = ?");

            statement.setString(1,name);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                System.out.println( "udlånsID: " + resultSet.getString("udlånsid") + " - Navn på låner: "
                        + resultSet.getString("navn") + " - Titel på udlånt bog: "  +
                        resultSet.getString("titel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createLoan(String name, String titel){
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);

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
    public void amountBorrowedByArea(int postalCode) {
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);

            PreparedStatement statement = connection.prepareStatement("select titel, count(*) as total from bibliotek.bookbyarea where postnummer = ? group by titel");

            statement.setInt(1,postalCode);

            ResultSet resultSet = statement.executeQuery();


            while(resultSet.next()){
                System.out.println("Book title: " + resultSet.getString("titel") +
                        " - times borrowed: " + resultSet.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printBorrowers(){

        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
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
            connection = DriverManager.getConnection(JdbcUrl, username, password);
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
            connection = DriverManager.getConnection(JdbcUrl, username, password);
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
            e.printStackTrace();
        }
        return ID;
    }

    private int getIndexFromTitel(String titel)
    {
        int ID = 0;
        PreparedStatement statement;
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
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
            e.printStackTrace();
        }
        return ID;
    }


//
//    @Override
//    public Person login(String email, String password1)
//    {
//        Person person = null;
//        try {
//            connection = DriverManager.getConnection(JdbcUrl, username, password);
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM iceprojekt.user where Email = ? AND Password =?");
//            statement.setString(1,email);
//            statement.setString(2,password1);
//            ResultSet result = statement.executeQuery();
//
//            if(result.next())
//            {
//                String userEmail = result.getString("Email");
//                String userPassword = result.getString("Password");
//                String firstName = result.getString("FirstName");
//                String lastName = result.getString("LastName");
//                int age = result.getInt("Age");
//                beer = (Beer) createAlcoholFromIndex(result.getInt("FavoriteBeer"), "beer");
//                wine = (Wine) createAlcoholFromIndex(result.getInt("FavoriteWine"), "wine");
//                spirit = (Spirit) createAlcoholFromIndex(result.getInt("FavoriteSpirit"), "spirit");
//
//                person = new Person(userEmail, userPassword, firstName, lastName, age, beer, wine, spirit);
//            }
//
//        } catch(SQLException e)
//        {
//            e.printStackTrace();
//        }
//
//        return person;
//    }
//
//    private Alcohol createAlcoholFromIndex(int index, String Alcoholtype)
//    {
//        PreparedStatement statement;
//        String beerString = "beer";
//        String wineString = "wine";
//
//        try {
//            connection = DriverManager.getConnection(JdbcUrl, username, password);
//            if(Alcoholtype.compareToIgnoreCase(beerString)==0)
//            {
//                statement = connection.prepareStatement("SELECT * FROM iceprojekt.beer where ID = ?");
//            }
//
//            else if(Alcoholtype.compareToIgnoreCase(wineString)==0)
//            {
//                statement = connection.prepareStatement("SELECT * FROM iceprojekt.wine where ID = ?");
//            }
//
//            else
//            {
//                statement = connection.prepareStatement("SELECT * FROM iceprojekt.spirits where ID = ?");
//
//            }
//
//            statement.setInt(1,index);
//            ResultSet result = statement.executeQuery();
//
//           while (result.next())
//            {
//                String name = result.getString("Name");
//                String type = result.getString("Type");
//                int price = result.getInt("Price");
//                String notes = result.getString("Notes");
//                String country = result.getString("Country");
//
//                if (Alcoholtype.compareToIgnoreCase(beerString) == 0)
//                {
//                    beer = new Beer(name, type, price, notes, country);
//                    return beer;
//                }
//                else if (Alcoholtype.compareToIgnoreCase(wineString) == 0)
//                {
//                    wine = new Wine(name, type, price, notes, country);
//                    return wine;
//                }
//                else
//                {
//                    spirit = new Spirit(name, type, price, notes, country);
//                    return spirit;
//                }
//
//            }
//
//        } catch(SQLException e)
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private int getIndexFromName(String name, String type)
//    {
//        int ID = 0;
//        PreparedStatement statement;
//        String beerString = "beer";
//        String wineString = "wine";
//
//        try {
//            connection = DriverManager.getConnection(JdbcUrl, username, password);
//            if(type.compareToIgnoreCase(beerString)==0)
//            {
//                statement = connection.prepareStatement("SELECT * FROM iceprojekt.beer where Name = ?");
//            }
//            else if(type.compareToIgnoreCase(wineString)==0)
//            {
//                statement = connection.prepareStatement("SELECT *  FROM iceprojekt.wine where Name = ?");
//            }
//            else
//            {
//                statement = connection.prepareStatement("SELECT * FROM iceprojekt.spirits where Name = ?");
//            }
//
//
//            statement.setString(1,name);
//            ResultSet result = statement.executeQuery();
//
//            while (result.next())
//            {
//                ID = result.getInt("ID");
//            }
//
//        } catch(SQLException e)
//        {
//            e.printStackTrace();
//        }
//        return ID;
//    }
//
//    private void deleteUser(Person person)
//    {
//        try {
//            connection = DriverManager.getConnection(JdbcUrl, username, password);
//
//            //Deletes the previous information of the user
//            PreparedStatement delete = connection.prepareStatement("DELETE FROM iceprojekt.user WHERE Email = ? and Password = ?");
//            delete.setString(1,person.getEmail());
//            delete.setString(2, person.getPassword());
//            int delResult = delete.executeUpdate();
//
//        } catch(SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
}
