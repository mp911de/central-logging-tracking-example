package biz.paluch.logging.tracking;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 19.12.13 09:35
 */
public class RequestTracking {

	private final static ThreadLocal<Data> threadLocal = new ThreadLocal<Data>();

	public static void set(String userName, String rootSessionId, String rootRequestId) {

		threadLocal.set(new Data(userName, rootSessionId, rootRequestId));
	}

	public static Data get() {
		return threadLocal.get();
	}

    public static void remove()
    {
        threadLocal.remove();
    }

    public static class Data {

		public final String userName;
		public final String rootSessionId;
		public final String rootRequestId;

		public Data(String userName, String rootSessionId, String rootRequestId) {
			this.userName = userName;
			this.rootSessionId = rootSessionId;
			this.rootRequestId = rootRequestId;
		}
	}
}
