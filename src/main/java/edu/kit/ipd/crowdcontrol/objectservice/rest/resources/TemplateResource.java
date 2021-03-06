package edu.kit.ipd.crowdcontrol.objectservice.rest.resources;

import edu.kit.ipd.crowdcontrol.objectservice.database.operations.TemplateOperations;
import edu.kit.ipd.crowdcontrol.objectservice.event.ChangeEvent;
import edu.kit.ipd.crowdcontrol.objectservice.event.EventManager;
import edu.kit.ipd.crowdcontrol.objectservice.proto.Template;
import edu.kit.ipd.crowdcontrol.objectservice.proto.TemplateList;
import edu.kit.ipd.crowdcontrol.objectservice.rest.Paginated;
import edu.kit.ipd.crowdcontrol.objectservice.rest.exceptions.BadRequestException;
import edu.kit.ipd.crowdcontrol.objectservice.rest.exceptions.InternalServerErrorException;
import edu.kit.ipd.crowdcontrol.objectservice.rest.exceptions.NotFoundException;
import spark.Request;
import spark.Response;

import java.util.Optional;
import java.util.function.Supplier;

import static edu.kit.ipd.crowdcontrol.objectservice.rest.RequestUtil.*;

/**
 * Handles requests to template resources.
 *
 * @author Niklas Keller
 */
public class TemplateResource {
    private TemplateOperations operations;
    private final EventManager eventManager;

    public TemplateResource(TemplateOperations operations, EventManager eventManager) {
        this.operations = operations;
        this.eventManager = eventManager;
    }

    /**
     * @param request  request provided by Spark
     * @param response response provided by Spark
     *
     * @return List of templates.
     */
    public Paginated<Integer> all(Request request, Response response) {
        int from = getQueryInt(request, "from", 0);
        boolean asc = getQueryBool(request, "asc", true);

        Supplier<RuntimeException> ex = () -> new InternalServerErrorException("Could not fetch a single template in a list.");

        // TODO: (low priority) Optimize for multiple templates
        return operations.getTemplatesFrom(from, asc, 20)
                .map(template -> (Template) operations.getTemplate(template.getId()).orElseThrow(ex))
                .constructPaginated(TemplateList.newBuilder(), TemplateList.Builder::addAllItems);
    }

    /**
     * @param request  request provided by Spark
     * @param response response provided by Spark
     *
     * @return Single template.
     */
    public Template get(Request request, Response response) {
        return operations.getTemplate(getParamInt(request, "id"))
                .orElseThrow(NotFoundException::new);
    }

    /**
     * @param request  request provided by Spark
     * @param response response provided by Spark
     *
     * @return Created template.
     */
    public Template put(Request request, Response response) {
        Template template = request.attribute("input");

        try {
            template = operations.insertTemplate(template);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }

        eventManager.TEMPLATE_CREATE.emit(template);

        response.status(201);
        response.header("Location", "/templates/" + template.getId());

        return template;
    }

    /**
     * @param request  request provided by Spark
     * @param response response provided by Spark
     *
     * @return Modified template.
     */
    public Template patch(Request request, Response response) {
        int id = getParamInt(request, "id");
        Template patch = request.attribute("input");

        Template oldTemplate = operations.getTemplate(id).orElseThrow(NotFoundException::new);
        Template newTemplate = operations.updateTemplate(id, patch);

        eventManager.TEMPLATE_UPDATE.emit(new ChangeEvent<>(oldTemplate, newTemplate));

        return newTemplate;
    }

    /**
     * @param request  request provided by Spark
     * @param response response provided by Spark
     *
     * @return {@code null}.
     */
    public Template delete(Request request, Response response) {
        int id = getParamInt(request, "id");

        Optional<Template> template = operations.getTemplate(id);
        template.map(eventManager.TEMPLATE_DELETE::emit);

        boolean existed = operations.deleteTemplate(id);

        if (!existed) {
            throw new NotFoundException();
        }

        return null;
    }
}