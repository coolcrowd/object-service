package edu.kit.ipd.crowdcontrol.objectservice;

import edu.kit.ipd.crowdcontrol.objectservice.crowdworking.PlatformManager;
import edu.kit.ipd.crowdcontrol.objectservice.crowdworking.TaskOperationException;
import edu.kit.ipd.crowdcontrol.objectservice.event.ChangeEvent;
import edu.kit.ipd.crowdcontrol.objectservice.event.Event;
import edu.kit.ipd.crowdcontrol.objectservice.event.EventManager;
import edu.kit.ipd.crowdcontrol.objectservice.event.EventObservable;
import edu.kit.ipd.crowdcontrol.objectservice.proto.Experiment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rx.Observable;
import rx.Observer;

import java.util.concurrent.TimeUnit;

/**
 * Created by lucaskrauss at 05.02.2016
 */
public class ExperimentController implements Observer<Event<Experiment>> {

    private final Logger log = LogManager.getLogger(ExperimentController.class);
    private final Observable<Event<Experiment>> observable = EventManager.EXPERIMENT_CREATE.getObservable();
    private final EventObservable<ChangeEvent<Experiment>> endExpObservable = EventManager.EXPERIMENT_CHANGE;
    private final PlatformManager platformManager;

    public ExperimentController(PlatformManager manager){
        platformManager = manager;
        observable.subscribe();

    }


    /**
     * Starts the experiment by publishing it on the participating platforms.
     * If an error occurs during the publishing, all already created tasks for this experiment will be undone
     * @param experiment
     */
   private void startExperiment(Experiment experiment){

        for (int i = 0; i < experiment.getPopulationsCount(); i++) {
            try {
                platformManager.publishTask(experiment.getPopulations(i).getPlatformId(),experiment);

            }catch ( TaskOperationException e1){
                //could not create task
                log.fatal(String.format("Error! Could not create experiment on platform %s!", experiment.getPopulations(i).getPlatformId()), e1);
                unpublishExperiment(experiment);
            }catch (IllegalStateException | IllegalArgumentException e2){
                log.fatal("Error! Could not create experiment! "+e2.getMessage());
                unpublishExperiment(experiment);
            }
        }


    }

    private void unpublishExperiment(Experiment experiment){
        for (int i = 0; i < experiment.getPopulationsCount(); i++) {
            try {
                platformManager.unpublishTask(experiment.getPopulations(i).getPlatformId(),experiment);
            }catch ( TaskOperationException e1){
                //nothing to do here because experiment-publishing was aborted before this population was reached.
            }
        }
    }


    public void endExperiment(Experiment experiment){

        for (int i = 0; i < experiment.getPopulationsCount(); i++){
            try {
                platformManager.unpublishTask(experiment.getPopulations(i).getPlatformId(),experiment);
            }catch (TaskOperationException e1){
                log.fatal(String.format("Error! Cannot unpublish experiment from platform %s!",experiment.getPopulations(i).getPlatformId()),e1);
            }
        }
        //wait for crowdplatform time out
        try {
            TimeUnit.HOURS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Experiment newExperiment = experiment.toBuilder().setState(Experiment.State.STOPPED).build();
        //notify all observers
        endExpObservable.emit(new ChangeEvent<>(experiment,newExperiment));
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Event<Experiment> experimentEvent) {
        startExperiment(experimentEvent.getData());

    }


}