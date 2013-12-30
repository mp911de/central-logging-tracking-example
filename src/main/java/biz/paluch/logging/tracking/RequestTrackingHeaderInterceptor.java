package biz.paluch.logging.tracking;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

public class RequestTrackingHeaderInterceptor extends AbstractSoapInterceptor {

	public RequestTrackingHeaderInterceptor() {
		super(Phase.PREPARE_SEND);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		Map<String, List<?>> headers = (Map<String, List<?>>) message.get(Message.PROTOCOL_HEADERS);

		RequestTracking.Data data = RequestTracking.get();
		if (data != null) {
			headers.put(WebTracking.DELEGATION_ROOT_REQUEST_ID, Collections.singletonList(data.rootRequestId));
			headers.put(WebTracking.DELEGATION_ROOT_SESSION_ID, Collections.singletonList(data.rootSessionId));
			headers.put(WebTracking.DELEGATION_ROOT_USER_ID, Collections.singletonList(data.userName));
		}
	}

}
