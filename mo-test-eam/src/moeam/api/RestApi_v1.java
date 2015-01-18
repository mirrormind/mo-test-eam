package moeam.api;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

// Hit this URL to test the api http://localhost:8080/mo-test-eam/rest/v1

/**
 * Moeam ReST API containing the bare essentials to get started. Specific
 * functions that we'll support should be categorised and contained in a
 * subclass.
 */
@Path("/v1")
public class RestApi_v1 {

	// /** API version number */
	// private static final String API_VERSION = "1.0";
	//
	// /** Hitting the URL root/version will return the API version number. */
	// @Path("/version")
	// @GET
	// @Produces(MediaType.TEXT_HTML)
	// public String getVersion() {
	// return API_VERSION;
	// }
	
	// /** Hitting the API URL root will display the readme. */
	// @GET
	// @Produces(MediaType.TEXT_HTML)
	// public String getReadMe() {
	// return null; // TODO need to create the readme
	// }

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		System.out.print("Debug: API root was accessed");
		return "Welcome to Moeam's ReST API!";
	}
}
