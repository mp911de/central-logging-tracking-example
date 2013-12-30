package biz.paluch.logging.tracking.sampleservices;

import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.Set;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 30.12.13 16:08
 */
public class RestApplication extends Application
{
    @Override
    public Set<Class<?>> getClasses()
    {
        return (Set) Collections.singleton(SampleResourceImpl.class);
    }
}
