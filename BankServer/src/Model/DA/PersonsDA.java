package Model.DA;


import Model.TO.PersonsTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PersonsDA
{
    private Connection connection;
    private PreparedStatement statement;

    public PersonsDA()throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "navid", "navid123");

    }
    public void insert(PersonsTO personsTO)throws Exception
    {
        statement = connection.prepareStatement("INSERT INTO PERSON (ID,NAME,USERNAME,PASSWORD) VALUES (?,?,?,?)");
        statement.setLong(1, personsTO.getId());
        statement.setString(2, personsTO.getName());
        statement.setString(3, personsTO.getUsername());
        statement.setString(4, personsTO.getPassword());
        statement.executeUpdate();
    }
    public ArrayList<PersonsTO> select()throws Exception
    {
        mstatement = connection.prepareStatement("SELECT * FROM PERSON");
        ResultSet resultSet =  statement.executeQuery();
        ArrayList<PersonsTO> PersonTOs = new ArrayList<PersonsTO>();
        while (resultSet.next())
        {
            PersonsTO PersonsTO = new PersonsTO();
            PersonsTO.setId(resultSet.getLong("ID"));
            PersonsTO.setName(resultSet.getString("NAME"));
            PersonsTO.setUsername(resultSet.getString("USERNAME"));
            PersonsTO.setPassword(resultSet.getString("PASSWORD"));
            PersonTOs.add(PersonsTO);
        }
        return PersonTOs;
    }
    public PersonsTO select(String username, String password)throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE USERNAME=? AND PASSWORD=?");
        statement.setString(1,username);
        statement.setString(2,password);
        ResultSet resultSet = statement.executeQuery();
        PersonsTO personsTO = new PersonsTO();
        while(resultSet.next())
        {
            personsTO.setId(resultSet.getLong("ID"));
            personsTO.setName(resultSet.getString("NAME"));
            personsTO.setUsername(resultSet.getString("USERNAME"));
            personsTO.setPassword(resultSet.getString("PASSWORD"));
        }
        return personsTO;
    }

    public PersonsTO select(long person_id)throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID = ?");
        statement.setLong(1,person_id);
        ResultSet resultSet = statement.executeQuery();
        PersonsTO personsTO = new PersonsTO();
        while(resultSet.next())
        {
            personsTO.setId(resultSet.getLong("ID"));
            personsTO.setName(resultSet.getString("NAME"));
            personsTO.setUsername(resultSet.getString("USERNAME"));
            personsTO.setPassword(resultSet.getString("PASSWORD"));
        }
        return personsTO;
    }

    public void close() throws Exception
    {
        statement.close();
        connection.close();
    }
}
