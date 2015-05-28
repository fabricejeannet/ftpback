package resources;

import org.joda.time.format.DateTimeFormat;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabricejeannet on 21/01/15.
 */
public class FilesResource extends ServerResource {

    @Get("json")
    public Representation represents() throws IOException {

        HeadersHelper.getMessageHeaders(getResponse()).add("Access-Control-Allow-Origin", "*");

        File root = new File("/srv/ftp/");


        ArrayList<FilePresentation> filePresentations = new ArrayList<FilePresentation>();

        for (File file : root.listFiles()) {
            if (!file.isDirectory()) {
                FilePresentation representation = new FilePresentation();
                representation.name = file.getName();
                Long longDate = file.lastModified();
                org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy hh:mm:ss");
                representation.date = fmt.print(longDate);
                filePresentations.add(representation);
            }
        }

        Representation json = new JacksonRepresentation<List<FilePresentation>>(filePresentations);

        return  json;

    }

}
