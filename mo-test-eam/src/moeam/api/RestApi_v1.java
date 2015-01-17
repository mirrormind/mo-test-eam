package moeam.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// Hit this URL to test the api http://localhost:7001/mo-test-eam/api/v1/

/**
 * Moeam ReST API containing the bare essentials to get started. Specific
 * functions that we'll support should be categorised and contained in a
 * subclass.
 */
@Path("/v1/*")
public class RestApi_v1 {

	/** API version number */
	private static final String API_VERSION = "1.0";

	/** Hitting the API URL root will display the readme. */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getReadMe() {
		return null; // TODO need to create the readme
	}

	/** Hitting the URL root/version will return the API version number. */
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getVersion() {
		return API_VERSION;
	}
}
