package biz.paluch.logging.tracking;

import biz.paluch.logging.tracking.sampleservices.RestServiceClientProvider;
import biz.paluch.logging.tracking.sampleservices.SampleResource;
import biz.paluch.logging.tracking.sampleservices.SampleWebService;
import biz.paluch.logging.tracking.sampleservices.WebServiceClientProvider;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 30.12.13 16:15
 */
public class CallAServiceIT
{

    @Test
    public void testCall1()
    {

        System.err.println("The first call: Simple two calls, which have nothing in common");

        SampleWebService wsclient =
                WebServiceClientProvider.getClient("http://localhost:8080/ws/SampleWebService?wsdl");
        wsclient.execute("SOAP 1");

        SampleResource rsclient = RestServiceClientProvider.getClient("http://localhost:8080/rest/");
        rsclient.doGet("REST 1");
    }

    @Test
    public void testCall2DelegateSoap()
    {

        System.err.println(
                "The second call: One external call, which calls the application itself in order to simulate a distributed call. These should have same request id's.");

        SampleWebService wsclient =
                WebServiceClientProvider.getClient("http://localhost:8080/ws/SampleWebService?wsdl");
        wsclient.delegateCall();

    }

    @Test
    public void testCall3DelegateRest()
    {

        System.err.println(
                "The second call: One external call, which calls the application itself using REST in order to simulate a distributed call. These should have same request id's.");

        SampleWebService wsclient =
                WebServiceClientProvider.getClient("http://localhost:8080/ws/SampleWebService?wsdl");
        wsclient.delegateCallRest();

    }
}
