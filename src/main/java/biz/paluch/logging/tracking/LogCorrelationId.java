package biz.paluch.logging.tracking;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class LogCorrelationId {

    /**
     * Constant for CorrelationId Logging (MDC).
     */
    private static final double RANDOM_MULTIPLIER = 1000000d;
    private static LogCorrelationId instance = new LogCorrelationId();

    private AtomicInteger correlationId;

    /**
     * Utility Constructor.
     */
    private LogCorrelationId() {
        correlationId = new AtomicInteger((int) (Math.random() * RANDOM_MULTIPLIER));
    }

    /**
     * @return next CorrelationId
     */
    public static String nextCorrelationId() {

        return Integer.toString(instance.correlationId.incrementAndGet(), Character.MAX_RADIX).toUpperCase();
    }

}
