package moeam.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.ConnectionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

// Hit this URL to test the api http://localhost:8080/mo-test-eam/rest/v1

/**
 * Moeam ReST API containing the bare essentials to get started. Specific
 * functions that we'll support should be categorised and contained in a
 * subclass.
 */
@Path("/resource")
public class AsyncResource
{

    @GET
    @Path("/asyncConnCallback")
    public void asyncGetConnectionCallback(@Suspended final AsyncResponse asyncResponse)
    {
        asyncResponse.register(new ConnectionCallback()
        {
            @Override
            public void onDisconnect(AsyncResponse asyncResponse)
            {
                asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Connection Callback")
                        .build());
            }
        });

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String result = veryExpensiveOperation();
                asyncResponse.resume(result);
            }

            private String veryExpensiveOperation()
            {
                return "Very Expensive Operation with Connection Callback";
            }
        }).start();
    }

}
