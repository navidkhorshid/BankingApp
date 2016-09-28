package Controller;

import Model.BL.User;
import Model.TO.TransactionsTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/transfer")
public class Transfer
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/checkBalance")
    public String checkBalance(@QueryParam("aid") String aid,@QueryParam("amnt") String amount)
    {
        String balance = "";
        long balanceNum = 0;
        long transferAmount = 0;
        User user = new User();
        try
        {
            balance = user.getBalance(Long.parseLong(aid));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            //return "The Account number is not in a correct format"; //if balance equals null this may come up too, aid cant be wrong
        }
        try
        {
            balanceNum = Long.parseLong(balance);
            transferAmount = Long.parseLong(amount);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            //return "Only Numbers are allowed for amount of money";
        }
        if(balanceNum >= transferAmount)
        {
            return "1";//yes
        }
        return "0";//no
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/checkPerson")
    public String checkPerson(@QueryParam("aid") String aid)
    {
        User user = new User();
        String name = "";
        try
        {
            name = user.getName(Long.parseLong(aid));
        }   catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        if(name!=null)
            return name;
        return "0";   //Not Found
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/transferMoney")
    public String transferMoney(@QueryParam("aid_sender") String aid1, @QueryParam("aid_receiver") String aid2,@QueryParam("amnt") String amount)
    {
        User user = new User();
        try
        {
            if(checkBalance(aid1,amount)=="1")//yes
                user.transaction(Long.parseLong(aid1),Long.parseLong(aid2),amount);
            else
                return "0";//It's not gonna happen in android
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "2"; //error
        }
        return "1"; // Successful
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/listSends")
    public String listSends(@QueryParam("aid") String aid)
    {
        User user = new User();
        String data = "";
        ArrayList<TransactionsTO> transactionsTOs = new ArrayList<TransactionsTO>();
        try
        {
            transactionsTOs = user.getSends(Long.parseLong(aid));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        try
        {
            int last_trans = 5;
            for(TransactionsTO tto : transactionsTOs)
            {
                data += "To: "+tto.getSa_id()+"|Amount: "+tto.getAmount()+"|Time: "+tto.getTime()+";";
                if(--last_trans == 0) return data; //show
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return data;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/listReceives")
    public String listReceives(@QueryParam("aid") String aid)
    {
        User user = new User();
        String data = "";
        ArrayList<TransactionsTO> transactionsTOs = new ArrayList<TransactionsTO>();
        try
        {
            transactionsTOs = user.getReceives(Long.parseLong(aid));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        try
        {
            int last_trans = 5;
            for(TransactionsTO tto : transactionsTOs)
            {
                data += "From: "+tto.getFa_id()+"|Amount: "+tto.getAmount()+"|Time: "+tto.getTime()+";";
                if(--last_trans == 0) return data; //show
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return data;
    }

}
