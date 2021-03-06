package edu.kit.ipd.crowdcontrol.objectservice.crowdworking;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import edu.kit.ipd.crowdcontrol.objectservice.database.model.enums.ExperimentsPlatformStatusPlatformStatus;
import edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.ExperimentsPlatformRecord;
import edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.PlatformRecord;
import edu.kit.ipd.crowdcontrol.objectservice.database.operations.PlatformOperations;
import edu.kit.ipd.crowdcontrol.objectservice.database.operations.ExperimentsPlatformOperations;
import edu.kit.ipd.crowdcontrol.objectservice.database.operations.WorkerOperations;
import edu.kit.ipd.crowdcontrol.objectservice.proto.Experiment;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * Created by marcel on 22.01.16.
 */
public class PlatformManagerTest {
    private PlatformManager manager;
    private static List<Platform> platforms = new ArrayList<>();
    private Experiment experiment = Experiment.newBuilder()
            .setId(42)
            .build();
    private ExperimentsPlatformOperations experimentsPlatformOperations;
    private PlatformOperations platformOps;
    private WorkerOperations workerOps;

    @BeforeClass
    public static void setUp() {
        platforms = new ArrayList<>();
        platforms.add( new PlatformTest("test1", "test 1", true, false, false, false));
        platforms.add( new PlatformTest("test2", "test 2", false, true, false, true));
        platforms.add( new PlatformTest("test3", "test 3", true, false, true, true));
    }

    @Before
    public void prepare() {
        experimentsPlatformOperations = mock(ExperimentsPlatformOperations.class);
        platformOps = mock(PlatformOperations.class);
        workerOps = mock(WorkerOperations.class);

        when(platformOps.getPlatform("test1")).thenReturn(Optional.empty());
        when(platformOps.getPlatform("test2")).thenReturn(Optional.empty());
        when(platformOps.getPlatform("test3")).thenReturn(Optional.empty());

        manager = new PlatformManager(platforms,
                platform -> params -> WorkerIdentification.findByIdentification(platform, "42"),
                new Payment() {
                    @Override
                    public CompletableFuture<Boolean> payExperiment(int id, JsonElement data, Experiment experiment, List<PaymentJob> paymentJob) {
                        return CompletableFuture.completedFuture(true);
                    }

                    @Override
                    public int getCurrency() {
                        return 0;
                    }
                },
                experimentsPlatformOperations,
                platformOps,
                workerOps);
    }

    @Test
    public void testEmail() throws Exception {
       assertTrue(manager.getNeedemail("test1"));
       assertFalse(manager.getNeedemail("test2"));
       assertTrue(manager.getNeedemail("test3"));
    }

    @Test
    public void publishTest() {
        platforms.forEach(platform ->  {
            ExperimentsPlatformRecord record = new ExperimentsPlatformRecord();
            record.setExperiment(42);
            record.setIdexperimentsPlatforms(platform.hashCode());
            record.setPlatform(platform.getId());
            record.setPlatformData(new JsonPrimitive(42));

            when(experimentsPlatformOperations.getExperimentsPlatform(record.getPlatform(), experiment.getId()))
                    .thenReturn(Optional.of(record));

            when(experimentsPlatformOperations.updateExperimentsPlatform(record))
                    .thenReturn(true);

            try {
                manager.publishTask(record.getPlatform(), experiment).join();
            } catch (PreActionException e) {
                e.printStackTrace();
            }

            verify(experimentsPlatformOperations).setPlatformStatus(record.getIdexperimentsPlatforms(),
                    ExperimentsPlatformStatusPlatformStatus.running);
        });
    }

    @Test
    public void unpublishTask() {
        platforms.forEach(platform -> {
            ExperimentsPlatformRecord record = new ExperimentsPlatformRecord();
            record.setExperiment(42);
            record.setPlatform(platform.getId());
            record.setIdexperimentsPlatforms(platform.hashCode());
            record.setPlatformData(new JsonPrimitive(42));

            when(experimentsPlatformOperations.getExperimentsPlatform(platform.getId(),experiment.getId())).thenReturn(Optional.of(record));
            try {
                manager.unpublishTask(platform.getId(), experiment).join();
            } catch (PreActionException e) {
                e.printStackTrace();
            }
            //record.setStatus(TaskStatus.finished);
            verify(experimentsPlatformOperations).setPlatformStatus(record.getIdexperimentsPlatforms(),
                    ExperimentsPlatformStatusPlatformStatus.shutdown);
        });
    }



    static class PlatformTest implements Platform, Payment {
        private boolean needEmail;
        private boolean handlePayment;
        private boolean handleWorker;
        private boolean renderCalib;
        private String name;
        private String type;

        public PlatformTest(String type, String name, boolean needEmail, boolean handlePayment,
                            boolean renderCalib, boolean handleWorker) {
            this.name = name;
            this.needEmail = needEmail;
            this.handlePayment = handlePayment;
            this.renderCalib = renderCalib;
            this.handleWorker = handleWorker;
            this.type = type;
        }

        public PlatformRecord toRecord() {
            PlatformRecord record = new PlatformRecord();
            record.setIdPlatform(type);
            record.setName(name);
            record.setNeedsEmail(needEmail);
            record.setRenderCalibrations(renderCalib);

            return record;
        }

        @Override
        public Optional<Payment> getPayment() {
            if (!handlePayment)
                return Optional.empty();
            else
                return Optional.of(this);
        }

        @Override
        public Optional<WorkerIdentificationComputation> getWorker() {
            if (!handleWorker)
                return Optional.empty();
            else
                return Optional.of(params -> WorkerIdentification.findByIdentification(getId(), "50"));
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getRawId() {
            return type;
        }

        @Override
        public CompletableFuture<JsonElement> publishTask(Experiment experiment) {
            return CompletableFuture.completedFuture(new JsonPrimitive(experiment.getId()));
        }

        @Override
        public CompletableFuture<Boolean> unpublishTask(JsonElement data) {
            return CompletableFuture.completedFuture(true);
        }

        @Override
        public Boolean isCalibrationAllowed() {
            return renderCalib;
        }

        @Override
        public String getLink() {
            return "sample link";
        }

        @Override
        public CompletableFuture<Boolean> payExperiment(int id, JsonElement data, Experiment experiment, List<PaymentJob> paymentJob) {
            return CompletableFuture.completedFuture(true);
        }

        @Override
        public int getCurrency() {
            return 0;
        }
    }
}