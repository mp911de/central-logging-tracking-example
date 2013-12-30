package biz.paluch.logging.tracking.sampleservices;

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 30.12.13 15:30
 */
@Path("something")
public class SampleResourceImpl implements SampleResource
{

    private Logger logger = Logger.getLogger(getClass());

    @Override
    @GET
    public String doGet(String message)
    {
        logger.info("executing doGet with message: " + message);
        return message;
    }
}
