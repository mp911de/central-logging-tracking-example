package biz.paluch.logging.tracking;

import org.apache.cxf.message.Message;
import org.jboss.resteasy.annotations.interception.ClientInterceptor;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.spi.interception.ClientExecutionContext;
import org.jboss.resteasy.spi.interception.ClientExecutionInterceptor;

import javax.ws.rs.ext.Provider;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 30.12.13 15:46
 */
@ClientInterceptor
@Provider
public class RequestTrackingHeaderRestInterceptor implements ClientExecutionInterceptor
{
    @Override
    public ClientResponse execute(ClientExecutionContext ctx) throws Exception
    {
        RequestTracking.Data data = RequestTracking.get();
        if (data != null)
        {

            ctx.getRequest().header(WebTracking.DELEGATION_ROOT_REQUEST_ID, data.rootRequestId);
            ctx.getRequest().header(WebTracking.DELEGATION_ROOT_SESSION_ID, data.rootSessionId);
            if (data.userName != null)
            {
                ctx.getRequest().header(WebTracking.DELEGATION_ROOT_USER_ID, data.userName);
            }
        }

        return ctx.proceed();
    }
}
