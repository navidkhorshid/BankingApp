package Model.BL;

import Model.DA.AccountsDA;
import Model.DA.PersonsDA;
import Model.DA.TransactionsDA;
import Model.TO.AccountsTO;
import Model.TO.PersonsTO;
import Model.TO.TransactionsTO;
import oracle.sql.DATE;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class User
{
    public long getPersonID(String username, String password) throws Exception
    {
        PersonsDA personsDA = new PersonsDA();
        PersonsTO personsTO = personsDA.select(username,password);
        personsDA.close();
        return personsTO.getId();
    }
    public ArrayList<AccountsTO> getAccounts(long person_id) throws Exception
    {
        AccountsDA accountsDA = new AccountsDA();
        ArrayList<AccountsTO> accountsTOs = accountsDA.selectByPerson(person_id);
        accountsDA.close();
        return accountsTOs;
    }
    public String getBalance(long account_id) throws Exception
    {
        AccountsDA accountsDA = new AccountsDA();
        AccountsTO accountsTO = accountsDA.select(account_id);
        accountsDA.close();
        return accountsTO.getMoney();
    }
    public String getName(long account_id) throws Exception
    {
        AccountsDA accountsDA = new AccountsDA();
        AccountsTO accountsTO = accountsDA.select(account_id);
        accountsDA.close();
        long person_id = accountsTO.getPerson_id();
        return getMyName(person_id);
    }

    public String getMyName(long person_id) throws Exception
    {
        PersonsDA personsDA = new PersonsDA();
        PersonsTO personsTO = personsDA.select(person_id);
        personsDA.close();
        return personsTO.getName();
    }

    public ArrayList<TransactionsTO> getSends(long account_id) throws Exception
    {
        TransactionsDA transactionsDA = new TransactionsDA();
        ArrayList<TransactionsTO> transactionsTOs = transactionsDA.selectSends(account_id);
        transactionsDA.close();
        return transactionsTOs;
    }

    public ArrayList<TransactionsTO> getReceives(long account_id) throws Exception
    {
        TransactionsDA transactionsDA = new TransactionsDA();
        ArrayList<TransactionsTO> transactionsTOs = transactionsDA.selectReceives(account_id);
        transactionsDA.close();
        return transactionsTOs;
    }
    /*
    private static java.sql.Date getCurrentDate()
    {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
    */
    public void transaction(long account_id1,long account_id2, String amount) throws Exception
    {
        AccountsDA accountsDA = new AccountsDA();
        TransactionsDA transactionsDA = new TransactionsDA();
        long amount_account1 = Long.parseLong(getBalance(account_id1)) - Long.parseLong(amount);
        long amount_account2 = Long.parseLong(getBalance(account_id2)) + Long.parseLong(amount);
        TransactionsTO transactionsTO = new TransactionsTO();
        transactionsTO.setId(transactionsDA.selectCount()+1);
        transactionsTO.setFa_id(account_id1);
        transactionsTO.setSa_id(account_id2);
        transactionsTO.setAmount(amount);
        Date now = new Date();
        transactionsTO.setTime(now.toString().substring(0,19));

        accountsDA.update(account_id1,Long.toString(amount_account1));
        accountsDA.update(account_id2,Long.toString(amount_account2));
        transactionsDA.insert(transactionsTO);

        transactionsDA.close();
        accountsDA.close();
    }

}
