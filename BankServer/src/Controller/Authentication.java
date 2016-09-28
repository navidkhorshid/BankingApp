package Controller;

import Model.BL.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;

@Path("/authentication")
public class Authentication {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/login")
    public String login(@QueryParam("user") String username, @QueryParam("pass") String password)
    {
        User user = new User();
        long person_ID = 0;
        try
        {
            person_ID = user.getPersonID(username, password);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return Long.toString(person_ID);
    }

}
