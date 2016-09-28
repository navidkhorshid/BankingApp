package Controller;


import Model.BL.User;
import Model.TO.AccountsTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/balance")
public class Balance
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/name")
    public String name(@QueryParam("pid") String p)
    {
        User user = new User();
        String name = "";
        try
        {
            name = user.getMyName(Long.parseLong(p));
        }   catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        if(name!=null)
            return name;
        return "0";   //Not Found - not possible in Android
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/accounts")
    public String accounts(@QueryParam("pid") String p)
    {
        User user = new User();
        String accountStr = "";
        ArrayList<AccountsTO> accountsTOs = new ArrayList<AccountsTO>();
        try
        {
            accountsTOs = user.getAccounts(Long.parseLong(p));
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        try
        {
            for(AccountsTO ato : accountsTOs)
            {
                accountStr += ato.getId() + ":";
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return accountStr;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/money")
    public String money(@QueryParam("aid") String aid)
    {
        User user = new User();
        String money="";
        try
        {
            money = user.getBalance(Long.parseLong(aid));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage()+"dsa");
        }
        return money;
    }
}
