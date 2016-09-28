package Model.DA;

import Model.TO.AccountsTO;
import Model.TO.TransactionsTO;

import java.sql.*;
import java.util.ArrayList;

public class TransactionsDA
{
    private Connection connection;
    private PreparedStatement statement;

    public TransactionsDA()throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "navid", "navid123");
        connection.setAutoCommit(false);

    }

    public void insert(TransactionsTO transactionsTO) throws Exception
    {
        statement = connection.prepareStatement("INSERT INTO TRANSACTION(ID,FA_ID,SA_ID,AMOUNT,TIME) VALUES (?,?,?,?,?)");
        statement.setLong(1, transactionsTO.getId());
        statement.setLong(2, transactionsTO.getFa_id());
        statement.setLong(3, transactionsTO.getSa_id());
        statement.setString(4, transactionsTO.getAmount());
        statement.setString(5, transactionsTO.getTime());
        statement.executeUpdate();
    }
    public ArrayList<TransactionsTO> select() throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM TRANSACTION");
        ResultSet resultSet =  statement.executeQuery();
        ArrayList<TransactionsTO> TransactionTOs = new ArrayList<TransactionsTO>();
        while (resultSet.next())
        {
            TransactionsTO TransactionsTO = new TransactionsTO();
            TransactionsTO.setId(resultSet.getLong("ID"));
            TransactionsTO.setFa_id(resultSet.getLong("FA_ID"));
            TransactionsTO.setSa_id(resultSet.getLong("SA_ID"));
            TransactionsTO.setAmount(resultSet.getString("AMOUNT"));
            TransactionsTO.setTime(resultSet.getString("TIME"));
            TransactionTOs.add(TransactionsTO);
        }
        return TransactionTOs;
    }

    public ArrayList<TransactionsTO> selectSends(long account_id) throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM TRANSACTION WHERE FA_ID = ? ORDER BY ID DESC");
        statement.setLong(1,account_id);
        ResultSet resultSet =  statement.executeQuery();
        ArrayList<TransactionsTO> TransactionTOs = new ArrayList<TransactionsTO>();
        while (resultSet.next())
        {
            TransactionsTO TransactionsTO = new TransactionsTO();
            TransactionsTO.setId(resultSet.getLong("ID"));
            TransactionsTO.setFa_id(resultSet.getLong("FA_ID"));
            TransactionsTO.setSa_id(resultSet.getLong("SA_ID"));
            TransactionsTO.setAmount(resultSet.getString("AMOUNT"));
            TransactionsTO.setTime(resultSet.getString("TIME"));
            TransactionTOs.add(TransactionsTO);
        }
        return TransactionTOs;
    }

    public ArrayList<TransactionsTO> selectReceives(long account_id) throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM TRANSACTION WHERE SA_ID = ? ORDER BY ID DESC");
        statement.setLong(1,account_id);
        ResultSet resultSet =  statement.executeQuery();
        ArrayList<TransactionsTO> TransactionTOs = new ArrayList<TransactionsTO>();
        while (resultSet.next())
        {
            TransactionsTO TransactionsTO = new TransactionsTO();
            TransactionsTO.setId(resultSet.getLong("ID"));
            TransactionsTO.setFa_id(resultSet.getLong("FA_ID"));
            TransactionsTO.setSa_id(resultSet.getLong("SA_ID"));
            TransactionsTO.setAmount(resultSet.getString("AMOUNT"));
            TransactionsTO.setTime(resultSet.getString("TIME"));
            TransactionTOs.add(TransactionsTO);
        }
        return TransactionTOs;
    }

    public long selectCount() throws Exception
    {
        statement = connection.prepareStatement("SELECT COUNT(*) FROM TRANSACTION");
        ResultSet resultSet = statement.executeQuery();
        long count = 0;
        while(resultSet.next())
        {
            count = resultSet.getLong("COUNT(*)");
        }
        return  count;
    }

    public void close() throws Exception
    {
        connection.commit();
        statement.close();
        connection.close();
    }
}
