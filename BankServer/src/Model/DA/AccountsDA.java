package Model.DA;

import Model.TO.AccountsTO;
import Model.TO.PersonsTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountsDA
{
    private Connection connection;
    private PreparedStatement statement;

    public AccountsDA()throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "navid", "navid123");
        connection.setAutoCommit(false);
    }
    public void insert(AccountsTO accountsTO)throws Exception
    {
        statement = connection.prepareStatement("INSERT INTO ACCOUNT(ID,PERSON_ID,MONEY) VALUES (?,?,?)");
        statement.setLong(1, accountsTO.getId());
        statement.setLong(2, accountsTO.getPerson_id());
        statement.setString(3, accountsTO.getMoney());
        statement.executeUpdate();
    }

    public void update(long id, String money) throws Exception
    {
        statement = connection.prepareStatement("UPDATE ACCOUNT SET MONEY = ? WHERE ID = ?");
        statement.setString(1, money);
        statement.setLong(2, id);
        statement.executeUpdate();
    }

    public ArrayList<AccountsTO> select() throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM ACCOUNT");
        ResultSet resultSet =  statement.executeQuery();
        ArrayList<AccountsTO> AccountTOs = new ArrayList<AccountsTO>();
        while (resultSet.next())
        {
            AccountsTO AccountsTO = new AccountsTO();
            AccountsTO.setId(resultSet.getLong("ID"));
            AccountsTO.setPerson_id(resultSet.getLong("PERSON_ID"));
            AccountsTO.setMoney(resultSet.getString("MONEY"));
            AccountTOs.add(AccountsTO);
        }
        return AccountTOs;
    }

    public ArrayList<AccountsTO> selectByPerson(long person_id) throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE PERSON_ID = ?");
        statement.setLong(1,person_id);
        ResultSet resultSet =  statement.executeQuery();
        ArrayList<AccountsTO> AccountTOs = new ArrayList<AccountsTO>();
        while (resultSet.next())
        {
            AccountsTO AccountsTO = new AccountsTO();
            AccountsTO.setId(resultSet.getLong("ID"));
            AccountsTO.setPerson_id(resultSet.getLong("PERSON_ID"));
            AccountsTO.setMoney(resultSet.getString("MONEY"));
            AccountTOs.add(AccountsTO);
        }
        return AccountTOs;
    }
    public AccountsTO select(long account_id) throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE ID = ?");
        statement.setLong(1,account_id);
        ResultSet resultSet =  statement.executeQuery();
        AccountsTO AccountsTO = new AccountsTO();
        while (resultSet.next())
        {
            AccountsTO.setId(resultSet.getLong("ID"));
            AccountsTO.setPerson_id(resultSet.getLong("PERSON_ID"));
            AccountsTO.setMoney(resultSet.getString("MONEY"));
        }
        return AccountsTO;
    }

    public void close() throws Exception
    {
        connection.commit();
        statement.close();
        connection.close();
    }
}
