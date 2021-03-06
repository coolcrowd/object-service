package edu.kit.ipd.crowdcontrol.objectservice.crowdworking.pybossa;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class wraps all requests to the pybossa platform API.
 *
 * @author Simon Korz
 * @version 1.0
 */
public class PyBossaRequests {
    private final String taskUrl;
    private final int projectId;
    private final String apiKey;
    private final String apiUrl;

    /**
     * The constructor of PyBossaRequests
     *
     * @param apiUrl    the url of the api
     * @param projectId the project id
     * @param apiKey    the api key
     */
    public PyBossaRequests(String apiUrl, int projectId, String apiKey) {
        this.apiUrl = apiUrl;
        this.taskUrl = apiUrl + "/task";
        this.projectId = projectId;
        this.apiKey = apiKey;
    }

    /**
     * Gets all tasks from the platform
     *
     * @return all tasks in the project. Might be empty
     * @throws PyBossaRequestException if the tasks cannot be fetched
     */
    public JSONArray getAllTasks() {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.get(taskUrl)
                    .header("Content-Type", "application/json")
                    .queryString("api_key", apiKey)
                    .queryString("project_id", projectId)
                    .asJson();
        } catch (UnirestException e) {
            throw new PyBossaRequestException(e);
        }
        JsonNode body = response.getBody();
        if (body.isArray()) {
            return body.getArray();
        }
        return new JSONArray();
    }

    /**
     * Posts a new task to the platform. That task will be publicly visible afterwards
     *
     * @param task the task to post
     * @return the task id
     * @throws PyBossaRequestException if the task cannot be posted
     */
    public int postTask(JSONObject task) {
        JsonNode jsonNode = new JsonNode(task.toString());

        HttpResponse<JsonNode> response;
        try {
            response = Unirest.post(taskUrl)
                    .header("Content-Type", "application/json")
                    .queryString("api_key", apiKey)
                    .body(jsonNode)
                    .asJson();
        } catch (UnirestException e) {
            throw new PyBossaRequestException(e);
        }

        if (response.getStatus() == 200) {
            return response.getBody().getObject().getInt("id");
        } else {
            throw new PyBossaRequestException(response.getBody().getObject()
                    .optString("exception_msg", "Publishing task failed"));
        }
    }


    /**
     * Deletes a task from the platform
     *
     * @param id the task to delete
     * @return true if successful, else false
     * @throws PyBossaRequestException if the connection to the platform fails
     */
    public boolean deleteTask(String id) {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.delete(taskUrl + "/{taskId}")
                    .header("Content-Type", "application/json")
                    .routeParam("taskId", id)
                    .queryString("api_key", apiKey)
                    .asJson();
        } catch (UnirestException e) {
            throw new PyBossaRequestException(e);
        }
        // if response status is 204 the task was successfully deleted
        return response.getStatus() == 204;
    }

    /**
     * Deletes a task from the platform.
     *
     * @param id the task to delete
     * @return true if successful, else false
     * @throws PyBossaRequestException if the connection to the platform fails
     */
    public boolean deleteTask(int id) {
        return deleteTask(String.valueOf(id));
    }

    /**
     * Get all taskRuns for the given task and user.
     *
     * @param taskId the taskId
     * @param userId the userId
     * @return all taskRuns, might be empty
     * @throws PyBossaRequestException if the task runs cannot be fetched
     */
    public JSONArray getTaskRuns(String taskId, String userId) {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.get(apiUrl + "/taskrun")
                    .queryString("api_key", apiKey)
                    .queryString("task_id", taskId)
                    .queryString("user_id", userId)
                    .asJson();
        } catch (UnirestException e) {
            throw new PyBossaRequestException(String.format("could not get taskRuns for task %s and worker %s",
                    taskId, userId), e);
        }

        JsonNode body = response.getBody();
        if (body.isArray()) {
            return body.getArray();
        }
        return new JSONArray();
    }


    /**
     * Deletes a task run from the platform.
     *
     * @param taskRunId the id of the task run
     * @throws PyBossaRequestException if task run cannot be deleted
     */
    public void deleteTaskRun(int taskRunId) {
        deleteTaskRun(String.valueOf(taskRunId));
    }

    /**
     * Deletes a task run from the platform.
     *
     * @param taskRunId the id of the task run
     * @throws PyBossaRequestException if task run cannot be deleted
     */
    public void deleteTaskRun(String taskRunId) {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.delete(apiUrl + "/taskrun/{id}")
                    .queryString("api_key", apiKey)
                    .routeParam("id", taskRunId)
                    .asJson();
        } catch (UnirestException e) {
            throw new PyBossaRequestException(e);
        }
        if (response.getStatus() != 204) {
            throw new PyBossaRequestException(String.format("Task run with id %s could not be deleted", taskRunId));
        }
    }

    /**
     * Get the current project.
     *
     * @return the project
     * @throws PyBossaRequestException if the project cannot be fetched
     */
    public JSONObject getProject() {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.get(apiUrl + "/project/{id}")
                    .queryString("api_key", apiKey)
                    .routeParam("id", String.valueOf(projectId))
                    .asJson();
        } catch (UnirestException e) {
            throw new PyBossaRequestException(e);
        }
        if (response.getStatus() != 200) {
            throw new PyBossaRequestException(String.format("GET project with id %s failed", projectId));
        } else {
            return response.getBody().getObject();
        }
    }

    /**
     * Gets a String from the given url.
     *
     * @param url the url to get the String from
     * @return the String returned by the get request
     * @throws PyBossaRequestException if the request fails
     */
    public String getStringFromUrl(String url) {
        HttpResponse<String> response;
        try {
            response = Unirest.get(url).asString();
        } catch (UnirestException e) {
            throw new PyBossaRequestException(e);
        }
        if (response.getStatus() == 200) {
            return response.getBody();
        } else {
            throw new PyBossaRequestException(String.format("The GET request to %s did not return a String.", url));
        }
    }

    /**
     * Will check if the url exists.
     *
     * @param url the url to check
     * @return true if the url returns status 200
     * @throws PyBossaRequestException if the request fails
     */
    public boolean existsUrl(String url) {
        HttpResponse<String> response;
        try {
            response = Unirest.get(url).asString();
        } catch (UnirestException e) {
            throw new PyBossaRequestException(e);
        }
        return response.getStatus() == 200;
    }

    /**
     * Sets the task presenter for the configured project.
     *
     * @param html the task presenter html
     * @throws PyBossaRequestException if the request fails
     */
    public void setTaskPresenter(String html) {
        JsonNode jsonNode = new JsonNode("");
        jsonNode.getObject().put("info", new JSONObject().put("task_presenter", html));

        HttpResponse<JsonNode> response;
        try {
            response = Unirest.put(apiUrl + "/project/{projectId}")
                    .header("Content-Type", "application/json")
                    .routeParam("projectId", String.valueOf(projectId))
                    .queryString("api_key", apiKey)
                    .body(jsonNode)
                    .asJson();
        } catch (UnirestException e) {
            throw new PyBossaRequestException(e);
        }
        if (response.getStatus() != 200) {
            throw new PyBossaRequestException(response.getBody().getObject()
                    .optString("exception_msg", "Setting task presenter failed"));
        }
    }
}
