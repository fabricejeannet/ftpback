package resources;

import org.restlet.Message;
import org.restlet.engine.header.Header;
import org.restlet.util.Series;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by fabricejeannet on 21/01/15.
 */
public class HeadersHelper {
    private static final String HEADERS_KEY = "org.restlet.http.headers";

    @SuppressWarnings("unchecked")
    static Series<Header> getMessageHeaders(Message message) {
        ConcurrentMap<String, Object> attrs = message.getAttributes();
        Series<Header> headers = (Series<Header>) attrs.get(HEADERS_KEY);
        if (headers == null) {
            headers = new Series<Header>(Header.class);
            Series<Header> prev = (Series<Header>)
                    attrs.putIfAbsent(HEADERS_KEY, headers);
            if (prev != null) {
                headers = prev;
            }
        }
        return headers;
    }
}
