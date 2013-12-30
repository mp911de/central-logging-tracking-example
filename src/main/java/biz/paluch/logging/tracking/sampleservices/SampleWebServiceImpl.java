package biz.paluch.logging.tracking.sampleservices;

import org.apache.log4j.Logger;

import javax.jws.WebService;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 30.12.13 15:22
 */
@WebService(name = "SampleWebService", serviceName = "SampleWebService",
            portName = "SampleWebServicePort",
            targetNamespace = "http://sample.paluch.biz",
            endpointInterface = "biz.paluch.logging.tracking.sampleservices.SampleWebService")
public class SampleWebServiceImpl implements SampleWebService
{

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public void execute(String message)
    {
        logger.info("executing execute() with message: " + message);
    }

    @Override
    public void delegateCall()
    {
        logger.info("delegating SOAP call...");

        SampleWebService client = WebServiceClientProvider.getClient("http://localhost:8080/ws/SampleWebService?wsdl");
        client.execute("calling my delegate");

    }
    @Override
    public void delegateCallRest()
    {
        logger.info("delegating REST call...");

        SampleResource client = RestServiceClientProvider.getClient("http://localhost:8080/rest/");
        client.doGet("calling my delegate");
    }
}
