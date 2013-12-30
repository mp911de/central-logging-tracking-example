package biz.paluch.logging.tracking.sampleservices;

import biz.paluch.logging.tracking.RequestTrackingHeaderInterceptor;
import org.apache.cxf.jaxws.JaxWsClientFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 30.12.13 15:39
 */
public class WebServiceClientProvider
{
    public static SampleWebService getClient(String wsdlUrl)
    {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(SampleWebService.class);
        factoryBean.setWsdlURL(wsdlUrl);

        int index = wsdlUrl.indexOf("?wsdl");

        factoryBean.setAddress(wsdlUrl.substring(0, index));

        // This is important in order to add HTTP headers to each outgoing call.
        factoryBean.getOutInterceptors().add(new RequestTrackingHeaderInterceptor());
        return (SampleWebService) factoryBean.create();
    }
}
