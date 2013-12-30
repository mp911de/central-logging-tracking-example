package biz.paluch.logging.tracking.sampleservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 30.12.13 15:22
 */
@WebService(name = "SampleWebService",  targetNamespace = "http://sample.paluch.biz")
public interface SampleWebService
{

    @WebMethod(operationName = "execute")
    void execute(@WebParam(name = "message") String message);

    @WebMethod(operationName = "delegateCall")
    void delegateCall();

    @WebMethod(operationName = "delegateCallRest")
    void delegateCallRest();
}
