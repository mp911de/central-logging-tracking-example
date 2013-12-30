package biz.paluch.logging.tracking.sampleservices;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 30.12.13 15:29
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("something")
public interface SampleResource
{
    @GET
    String doGet(@QueryParam("message") String message);
}
