package application;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import resources.FileResource;
import resources.FilesResource;

/**
 * Created by fabricejeannet on 21/01/15.
 */
public class FtpApplication extends Application {

    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/file/{fileName}", FileResource.class);
        router.attach("/files", FilesResource.class);
        return router;
    }

    public static void main(String[] args) throws Exception {
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8080);
        component.getDefaultHost().attach("",new FtpApplication());
        component.start();
    }


}
