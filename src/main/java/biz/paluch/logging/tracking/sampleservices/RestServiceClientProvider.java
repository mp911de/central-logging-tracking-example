package biz.paluch.logging.tracking.sampleservices;

import biz.paluch.logging.tracking.RequestTrackingHeaderRestInterceptor;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.ClientProxy;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import java.util.HashMap;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 30.12.13 15:43
 */
public class RestServiceClientProvider
{

    public static SampleResource getClient(String baseUri)
    {
        ResteasyProviderFactory factory = new ResteasyProviderFactory();
        factory.registerProvider(RequestTrackingHeaderRestInterceptor.class);
        ResteasyProviderFactory.setInstance(factory);
        return ProxyFactory.create(SampleResource.class, baseUri, factory, new HashMap<String, Object>());
    }
}
