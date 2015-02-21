package moeam.api.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

public class AsyncResourceTest
{
    @Test
    public void testAsyncGetConnectionCallback() throws InterruptedException, ExecutionException
    {
        ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);

        final AsyncInvoker asyncInvoker = client
                .target("http://localhost:8080/mo-test-eam/rest/resource/asyncConnCallback").request().async();
        final Future<Response> responseFuture = asyncInvoker.get();
        System.out.println("Request is being processed asynchronously.");
        final Response response = responseFuture.get();
        // get() waits for the response to be ready
        System.out.println("Response received : " + response);
        System.out.println("Response from GET method : " + response.readEntity(String.class));
    }

}