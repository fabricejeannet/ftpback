package resources;

import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Options;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.io.File;

/**
 * Created by fabricejeannet on 21/01/15.
 */
public class FileResource extends ServerResource {


    @Override
    protected void doInit() throws ResourceException {
        HeadersHelper.getMessageHeaders(getResponse()).add("Access-Control-Allow-Origin", "*");
        fileName = getRequestAttributes().get("fileName").toString();
    }


    @Delete
    @Options
    public Status deleteFile() {

        HeadersHelper.getMessageHeaders(getResponse()).add("Access-Control-Allow-Methods", "DELETE");
        File file = new File(fileName);
        file.delete();

        return Status.SUCCESS_ACCEPTED;
    }

    private String fileName;
}
