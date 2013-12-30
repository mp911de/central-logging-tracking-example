package biz.paluch.logging.tracking;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.MDC;

/**
 * Setup MDC.
 */
public class MDCFilter implements Filter
{

    public static final String APPLICATION = "Application";
    public static final String APP_VERSION = "App.Version";
    public static final String TRACKING_ROOT_USER = "Tracking.RootUser";
    public static final String TRACKING_ROOT_SESSION_ID = "Tracking.RootSessionId";
    public static final String TRACKING_ROOT_REQUEST_ID = "Tracking.RootRequestId";

    public static final String REQUEST_URI = "requestUri";
    public static final String REQUEST_METHOD = "requestMethod";

    protected void setupMDC(HttpServletRequest httpRequest)
    {
        RequestTracking.Data data = WebTracking.initialOrForwarded(httpRequest);
        if (data.userName != null)
        {
            MDC.put(TRACKING_ROOT_USER, data.userName);
        }

        if (data.rootSessionId != null)
        {
            MDC.put(TRACKING_ROOT_SESSION_ID, data.rootSessionId);
        }

        MDC.put(TRACKING_ROOT_REQUEST_ID, data.rootRequestId);
        MDC.put(APP_VERSION, "2.1 Build 1234");
        MDC.put(REQUEST_URI, httpRequest.getRequestURI());
        MDC.put(REQUEST_METHOD, httpRequest.getMethod());
    }

    protected void clearMDC()
    {
        RequestTracking.remove();
        MDC.remove(APPLICATION);
        MDC.remove(APP_VERSION);
        MDC.remove(TRACKING_ROOT_USER);
        MDC.remove(TRACKING_ROOT_SESSION_ID);
        MDC.remove(TRACKING_ROOT_REQUEST_ID);
        MDC.remove(REQUEST_URI);
        MDC.remove(REQUEST_METHOD);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
        try
        {
            setupMDC((HttpServletRequest) servletRequest);
            filterChain.doFilter(servletRequest, servletResponse);

        } finally
        {
            clearMDC();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void destroy()
    {

    }
}
