package edu.kit.ipd.crowdcontrol.objectservice.crowdworking;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.WorkerRecord;
import edu.kit.ipd.crowdcontrol.objectservice.database.operations.WorkerOperations;

import java.util.Map;
import java.util.Optional;

/**
 * Created by marcel on 19.01.16.
 */
public interface WorkerIdentification {
    /**
     * Parse a worker id out of the params
     * @return The id of the worker if one can be found, as JSON
     */
    JsonElement getWorkerData() throws UnidentifiedWorkerException;

    /**
     * Tries to find the worker in the database
     *
     * @param workerOperations The worker operations to use
     * @return A WorkerRecord, if one is found
     */
    Optional<WorkerRecord> findWorker(WorkerOperations workerOperations);

    /**
     * this instance of WorkerIdentification tries to identify the worker by the passed identification
     * @param platform The platform on which this is called
     * @param identification the computed id of the worker
     * @return the resulting WorkerIdentification
     */
    static WorkerIdentification findByIdentification(String platform, String identification) {
        return new WorkerIdentification() {
            @Override
            public JsonElement getWorkerData() throws UnidentifiedWorkerException {
                JsonObject jsonObject = new JsonObject();
                jsonObject.add("identification", new JsonPrimitive(identification));
                return jsonObject;
            }

            @Override
            public Optional<WorkerRecord> findWorker(WorkerOperations workerOperations) {
                return workerOperations.getWorker(platform, new JsonPrimitive(identification).toString());
            }
        };
    }
}
