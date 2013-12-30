package biz.paluch.logging.tracking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;


/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 19.12.13 09:39
 */
public class WebTracking {

	public final static String DELEGATION_ROOT_SESSION_ID = "X-RootSessionId";
	public final static String DELEGATION_ROOT_REQUEST_ID = "X-RootRequestId";
	public final static String DELEGATION_ROOT_USER_ID = "X-RootUserId";

	public static RequestTracking.Data initialOrForwarded(HttpServletRequest httpServletRequest) {

		String userId = getUserId(httpServletRequest);
		String requestId = getRequestId(httpServletRequest);
		String sessionId = getSessionId(httpServletRequest);
		RequestTracking.set(userId, sessionId, requestId);
		return RequestTracking.get();

	}

	private static String getUserId(HttpServletRequest httpServletRequest) {
		String value = httpServletRequest.getHeader(DELEGATION_ROOT_USER_ID);
		if (StringUtils.isEmpty(value)) {
			if (httpServletRequest.getUserPrincipal() != null) {
				value = httpServletRequest.getUserPrincipal().getName();
			}
		}
		return value;
	}

	private static String getRequestId(HttpServletRequest httpServletRequest) {
		String value = httpServletRequest.getHeader(DELEGATION_ROOT_REQUEST_ID);
		if (StringUtils.isEmpty(value)) {
			value = RuntimeContainer.HOSTNAME + "." + LogCorrelationId.nextCorrelationId();
		}
		return value;
	}

	private static String getSessionId(HttpServletRequest httpServletRequest) {
		String value = httpServletRequest.getHeader(DELEGATION_ROOT_SESSION_ID);
		if (StringUtils.isEmpty(value)) {
			HttpSession session = httpServletRequest.getSession(false);
			if (session != null) {
				value = session.getId();
			} else {
				value = "none";
			}
		}
		return value;
	}

}
