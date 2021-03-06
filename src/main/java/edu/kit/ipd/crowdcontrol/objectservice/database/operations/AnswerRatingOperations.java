package edu.kit.ipd.crowdcontrol.objectservice.database.operations;

import edu.kit.ipd.crowdcontrol.objectservice.database.model.Tables;
import edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.*;
import edu.kit.ipd.crowdcontrol.objectservice.database.transformers.AnswerRatingTransformer;
import edu.kit.ipd.crowdcontrol.objectservice.database.transformers.ExperimentTransformer;
import edu.kit.ipd.crowdcontrol.objectservice.proto.CalibrationAnswer;
import edu.kit.ipd.crowdcontrol.objectservice.proto.Experiment;
import edu.kit.ipd.crowdcontrol.objectservice.proto.Rating;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static edu.kit.ipd.crowdcontrol.objectservice.database.model.Tables.*;

/**
 * responsible for all queries related to the Answer and Rating Table
 *
 * @author LeanderK
 * @version 1.0
 */
public class AnswerRatingOperations extends AbstractOperations {
    private final CalibrationOperations calibrationOperations;
    private final WorkerCalibrationOperations workerCalibrationOperations;
    private final ExperimentOperations experimentOperations;

    public AnswerRatingOperations(DSLContext create, CalibrationOperations calibrationOperations,
                                  WorkerCalibrationOperations workerCalibrationOperations, ExperimentOperations experimentOperations) {
        super(create);
        this.calibrationOperations = calibrationOperations;
        this.workerCalibrationOperations = workerCalibrationOperations;
        this.experimentOperations = experimentOperations;
    }


    /**
     * Gets all ratings of a specified answer
     *
     * @param answerRecord answer, whose ratings are requested
     * @return list of ratings of a specified answer
     */
    public Result<RatingRecord> getRatingsOfAnswer(AnswerRecord answerRecord) {
        return create.selectFrom(RATING)
                .where(RATING.ANSWER_R.eq(answerRecord.getIdAnswer()))
                .and(RATING.RATING_.isNotNull())
                .fetch();
    }

    /**
     * Returns the answers of a worker to a certain experiment
     * @param expID the id of the experiment
     * @param workerID the id of the worker
     * @return list of all answers from a worker to an experiment
     */
    public Result<AnswerRecord> getAnswersOfWorkerFromExperiment(int expID, int workerID) {
        return create.selectFrom(ANSWER)
                .where(ANSWER.WORKER_ID.eq(workerID))
                .and(ANSWER.EXPERIMENT.eq(expID))
                .fetch();
    }

    /**
     * Get all answers of the experiment specified by given ID
     *
     * @param expID specifying the experiment
     * @return list of all answers of a experiment
     */
    public Result<AnswerRecord> getAnswersOfExperiment(int expID) {
        return create.selectFrom(ANSWER)
                .where(ANSWER.EXPERIMENT.eq(expID))
                .fetch();
    }


    /**
     * Fetches all answers of the specified experiment with a quality-value equal or above
     * the given threshold
     *
     * @param expID     of the experiment
     * @param threshold specifying good answers. A good answer has at least a quality-value of given threshold
     * @return Map of workers and a set of matching answerRecords.
     */
    public Map<WorkerRecord, List<AnswerRecord>> getGoodAnswersOfExperiment(int expID, int threshold) {
        return create.select(WORKER.fields())
                .select(ANSWER.fields())
                .from(WORKER)
                .rightJoin(ANSWER).onKey()
                .where(ANSWER.EXPERIMENT.eq(expID))
                .and(ANSWER.QUALITY.greaterOrEqual(threshold))
                .fetchGroups(WORKER, record -> record.into(ANSWER));
    }


    /**
     * Fetches the number all answers of the specified experiment with a quality-value equal or above
     * the given threshold
     *
     * @param expID     of the experiment
     * @param threshold specifying good answers. A good answer has at least a quality-value of given threshold
     * @return Map of workers and a number of matching answerRecords.
     */
    public Map<WorkerRecord, Integer> getNumOfGoodAnswersOfExperiment(int expID, int threshold){
        Field<Integer> count = DSL.count(ANSWER.ID_ANSWER).as("count");
        return create.select(WORKER.fields())
                .select(count)
                .from(WORKER)
                .rightJoin(ANSWER).onKey()
                .where(ANSWER.EXPERIMENT.eq(expID))
                .and(ANSWER.QUALITY.greaterOrEqual(threshold))
                .groupBy(WORKER.fields())
                .fetchMap(WORKER, record -> record.getValue(count));
    }


    /**
     * Fetches all ratings of the specified experiment with a quality-value equal or above
     * the given threshold
     *
     * @param expID     of the experiment
     * @param threshold specifying good rating. A good rating has at least a quality-value of given threshold
     * @return Map of workers and a set of matching ratings.
     */
    public Map<WorkerRecord, List<RatingRecord>> getGoodRatingsOfExperiment(int expID, int threshold) {
        return create.select(WORKER.fields())
                .select(RATING.fields())
                .from(WORKER)
                .rightJoin(RATING).onKey()
                .where(ANSWER.EXPERIMENT.eq(expID))
                .and(ANSWER.QUALITY.greaterOrEqual(threshold))
                .fetchGroups(WORKER, record -> record.into(RATING));
    }

    /**
     * Fetches the number of all ratings of the specified experiment with a quality-value equal or above
     * the given threshold
     *
     * @param expID     of the experiment
     * @param threshold specifying good rating. A good rating has at least a quality-value of given threshold
     * @return Map of workers and a number of matching ratings.
     */
    public Map<WorkerRecord, Integer> getNumOfGoodRatingsOfExperiment(int expID, int threshold){
        Field<Integer> count = DSL.count(RATING.ID_RATING).as("count");
        return create.select(WORKER.fields())
                .select(count)
                .from(WORKER)
                .rightJoin(RATING).onKey()
                .where(RATING.EXPERIMENT.eq(expID))
                .and(RATING.QUALITY.greaterOrEqual(threshold))
                .groupBy(WORKER.fields())
                .fetchMap(WORKER, record -> record.getValue(count));
    }


    /**
     * Returns all ratings of given answer, which have a quality rating above passed threshold
     *
     * @param answerRecord answer, whose good ratings (specified by given threshold) are returned
     * @param threshold    of type int, which specifies good ratings
     * @return list of all ratings of given answer with a quality rating equal or greater than given threshold
     */
    public Result<RatingRecord> getGoodRatingsOfAnswer(AnswerRecord answerRecord, int threshold) {
        return create.selectFrom(RATING)
                .where(RATING.ANSWER_R.eq(answerRecord.getIdAnswer()))
                .and(RATING.QUALITY.greaterThan(threshold))
                .fetch();
    }


    /**
     * Sets quality ratings to a set of ratings
     *
     * @param map of ratings and matching qualities
     */
    public void setQualityToRatings(Map<RatingRecord, Integer> map) {
        List<RatingRecord> toUpdate = map.entrySet().stream()
                .map(entry -> {
                    entry.getKey().setQuality(entry.getValue());
                    return entry.getKey();
                })
                .collect(Collectors.toList());

        create.batchUpdate(toUpdate).execute();
    }

    /**
     * Sets quality rating to an answer
     *
     * @param answer  whose quality is to be set
     * @param quality of the answer
     */
    public void setQualityToAnswer(AnswerRecord answer, int quality) {
        answer.setQuality(quality);

        create.batchUpdate(answer).execute();
    }


    /**
     * inserts a new answer into the DB
     *
     * @param answerRecord the record to insert
     * @return the resulting record
     * @throws IllegalArgumentException if the experiment the answers is referring to is not
     *                                  existing
     * @throws IllegalStateException    if the worker is not allowed to submit more answers
     */
    public AnswerRecord insertNewAnswer(AnswerRecord answerRecord) throws IllegalArgumentException, IllegalStateException {
        answerRecord.setIdAnswer(null);
        ExperimentRecord experimentRecord = experimentOperations.getExperiment(answerRecord.getExperiment())
                .orElseThrow(() -> new IllegalArgumentException("Illegal experiment-value in answer record."));
        if (getAnswerCount(answerRecord.getWorkerId(), experimentRecord.getIdExperiment()) >= experimentRecord.getAnwersPerWorker()) {
            throw new IllegalStateException(
                    String.format("Worker %d already submitted the maximum of allowed answers", answerRecord.getWorkerId())
            );
        }

        AnswerRecord result = doIfRunning(answerRecord.getExperiment(), conf -> {
            AnswerReservationRecord reservation = DSL.using(conf).selectFrom(ANSWER_RESERVATION)
                    .where(ANSWER_RESERVATION.ID_ANSWER_RESERVATION.eq(answerRecord.getReservation()))
                    .fetchOne();

            if (reservation.getUsed()) {
                throw new IllegalStateException(String.format("Reservation %d is already in use", answerRecord.getReservation()));
            }

            reservation.setUsed(true);

            DSL.using(conf).executeUpdate(reservation);

            return DSL.using(conf)
                    .insertInto(ANSWER)
                    .set(answerRecord)
                    .returning()
                    .fetchOne();
        });
        addToExperimentCalibration(answerRecord.getWorkerId(), answerRecord.getExperiment());
        return result;
    }

    /**
     * Returns the number of answers a given worker has submitted for a given experiment.
     *
     * @param workerID the primary key of the worker
     * @param experimentID the primary key of the experiment
     *
     * @return the number of answers
     */
    private int getAnswerCount(int workerID, int experimentID) {
        return create.fetchCount(
                DSL.selectFrom(ANSWER)
                        .where(ANSWER.WORKER_ID.eq(workerID))
                        .and(ANSWER.EXPERIMENT.eq(experimentID))
        );
    }



    /**
     * Sets the quality-assured-bit for the given answerRecord
     * This indicates, that the answers quality is unlikely to change
     *
     * @param answerRecord whose quality-assured-bit is set
     */
    public void setAnswerQualityAssured(AnswerRecord answerRecord) {
        answerRecord.setQualityAssured(true);

        create.batchUpdate(answerRecord).execute();
    }

    /**
     * updates the rating.
     * <p>
     * the usual workflow is that first a rating has to be reserved an therefore an empty rating gets inserted.
     * Then the worker actually commits the rating and this method gets called to update it.
     *
     * @param rating       the rating to update
     * @return the resulting rating
     * @throws IllegalArgumentException if the experiment the rating is referring to is not
     *                                  existing
     * @throws IllegalStateException    if the worker is not allowed to submit more ratings
     */
    public Rating insertRating(Rating rating) throws IllegalArgumentException, IllegalStateException {
        assertHasField(rating, Rating.RESERVATION_FIELD_NUMBER);

        String feedback = null;

        if (rating.hasField(rating.getDescriptorForType().findFieldByNumber(Rating.FEEDBACK_FIELD_NUMBER))) {
            feedback = rating.getFeedback();
        }

        String finalFeedback = feedback;

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        RatingRecord result = doIfRunning(rating.getExperimentId(), conf -> {
            RatingReservationRecord reservation = DSL.using(conf).selectFrom(RATING_RESERVATION)
                    .where(RATING_RESERVATION.ID_RESERVERD_RATING.eq(rating.getReservation()))
                    .fetchOptional()
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Reservation %d is not existing", rating.getReservation())));

            if (reservation.getUsed()) {
                throw new IllegalStateException(String.format("Reservation %d is already in use", rating.getReservation()));
            }

            reservation.setUsed(true);

            DSL.using(conf).executeUpdate(reservation);

            RatingRecord ratingRecord = new RatingRecord(
                    null,
                    rating.getExperimentId(),
                    reservation.getAnswer(),
                    timestamp,
                    rating.getRating(),
                    rating.getReservation(),
                    finalFeedback,
                    rating.getWorker(),
                    null
            );

            return DSL.using(conf).insertInto(RATING)
                    .set(ratingRecord)
                    .returning()
                    .fetchOne();
        });

        List<RatingConstraintRecord> toInsert = rating.getViolatedConstraintsList().stream()
                .map(constraint -> {
                    RatingConstraintRecord constraintRecord = new RatingConstraintRecord();
                    constraintRecord.setRefRating(result.getIdRating());
                    constraintRecord.setOffConstraint(constraint.getId());
                    return constraintRecord;
                })
                .collect(Collectors.toList());

        create.batchInsert(toInsert).execute();

        addToExperimentCalibration(result.getWorkerId(), result.getExperiment());

        Result<RatingConstraintRecord> ratingConstraints = create.selectFrom(RATING_CONSTRAINT)
                .where(RATING_CONSTRAINT.REF_RATING.eq(result.getIdRating()))
                .fetch();

        Result<ConstraintRecord> constraints = create.selectFrom(CONSTRAINT)
                .where(CONSTRAINT.ID_CONSTRAINT.in(ratingConstraints.map(RatingConstraintRecord::getOffConstraint)))
                .fetch();

        return AnswerRatingTransformer.toRatingProto(result, constraints);
    }

    /**
     * this method adds a worker to the experiment-calibration.
     * <p>
     * Every experiment has calibration with one answer-option, which gets auto-generated when the
     * event got published. If a worker now submits a rating/answer, the worker gets linked to the
     * calibration. This is used to exclude workers, who have worked on a specific event, from
     * working on another.
     *
     * @param workerID     the worker to link to the calibration
     * @param experimentId the experiment the calibration belongs to
     */
    private void addToExperimentCalibration(int workerID, int experimentId) {
        Supplier<Optional<CalibrationAnswer>> doAdd = () -> calibrationOperations
                .getCalibrationForExperiment(experimentId)
                .map(answerOption -> {
                    try {
                        return workerCalibrationOperations
                                        .insertAnswer(workerID, answerOption.getIdCalibrationAnswerOption());
                    } catch (IllegalArgumentException ignored) {
                        //is ok, the worker was already added
                        return CalibrationAnswer.getDefaultInstance();
                    }
                });

        Optional<CalibrationAnswer> result = doAdd.get();
        if (!result.isPresent()) {
            System.err.println(String.format("Database inconsistency! No calibration for experiment: %d present", experimentId));
            Experiment experiment = experimentOperations.getExperiment(experimentId)
                    .map(record -> ExperimentTransformer.toProto(record, experimentOperations.getExperimentState(experimentId)))
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Experiment %d is not existing", experimentId)));
            calibrationOperations.createExperimentsCalibration(experimentId, experiment);
            doAdd.get();
        }
    }

    /**
     * gets the answer with the passed primary key
     *
     * @param answerID the primary key of the answer
     *
     * @return the answerRecord or emtpy
     */
    public Optional<AnswerRecord> getAnswer(int answerID) {
        return create.fetchOptional(ANSWER, ANSWER.ID_ANSWER.eq(answerID));
    }

    /**
     * Returns a range of answers starting from {@code cursor}.
     *
     * @param cursor Pagination cursor
     * @param next   {@code true} for next, {@code false} for previous
     * @param limit  Number of records
     *
     * @return List of answers
     */
    public Range<AnswerRecord, Integer> getAnswersFrom(int expid, int cursor, boolean next, int limit) {
        SelectConditionStep<AnswerRecord> query = create.selectFrom(ANSWER)
                .where(ANSWER.EXPERIMENT.eq(expid));
        return getNextRange(query, ANSWER.ID_ANSWER, ANSWER, cursor, next, limit);
    }

    /**
     * Get a Rating form a id
     *
     * @param id The id to search for
     *
     * @return a RatingRecord if it exists in the db
     */
    public Optional<RatingRecord> getRating(int id) {
        return create.fetchOptional(RATING, RATING.ID_RATING.eq(id));
    }

    /**
     * Returns the list of ratings from a answer
     *
     * @param answerId the answer which was rated
     *
     * @return A list of ratingRecords
     */
    public List<Rating> getRatings(int answerId) {
        List<RatingRecord> ratingRecords = create.selectFrom(RATING)
                .where(RATING.ANSWER_R.eq(answerId))
                .fetch();

        return ratingRecords.stream().map((ratingRecord) -> {
            Result<RatingConstraintRecord> ratingConstraints = create.selectFrom(RATING_CONSTRAINT)
                    .where(RATING_CONSTRAINT.REF_RATING.eq(ratingRecord.getIdRating()))
                    .fetch();

            Result<ConstraintRecord> constraints = create.selectFrom(CONSTRAINT)
                    .where(CONSTRAINT.ID_CONSTRAINT.in(ratingConstraints.map(RatingConstraintRecord::getOffConstraint)))
                    .fetch();

            return AnswerRatingTransformer.toRatingProto(ratingRecord, constraints);
        }).collect(Collectors.toList());
    }

    /**
     * Returns the number of all answers with the correct number of ratings specified in the experiment.
     * The answers quality has to be equal or above the experiment's payment quality-threshold
     * @param idExperiment id of the experiment
     * @return number of answers with a final and good quality
     */
    public int getNumberOfFinalGoodAnswers(int idExperiment) {
        return create.fetchCount(
                DSL.selectFrom(ANSWER)
                        .where(ANSWER.EXPERIMENT.eq(idExperiment))
                        .and(ANSWER.QUALITY_ASSURED.eq(true))
                        .and(ANSWER.QUALITY.greaterOrEqual(
                                        DSL.select(EXPERIMENT.RESULT_QUALITY_THRESHOLD)
                                                .from(EXPERIMENT)
                                                .where(EXPERIMENT.ID_EXPERIMENT.eq(idExperiment))
                        ))
        );
    }

    /**
     * this query returns of all non-duplicate answers have max-ratings
     * @param experiment the primary key of the experiment
     * @return true if all answers have max ratings, false if not
     */
    public boolean allAnswersHaveMaxRatings(int experiment) {
        Field<Integer> countRating = DSL.count(RATING.ID_RATING).as("countRating");
        return !create.fetchExists(
                DSL.select(ANSWER_RESERVATION.ID_ANSWER_RESERVATION, countRating)
                        .from(ANSWER_RESERVATION)
                        .leftJoin(ANSWER).onKey()
                        .leftJoin(RATING).on(
                            RATING.ANSWER_R.eq(ANSWER.ID_ANSWER)
                        )
                        .where(
                                ANSWER.QUALITY_ASSURED.eq(true)
                                .and(ANSWER.QUALITY.notEqual(0))
                                .or(ANSWER.QUALITY_ASSURED.eq(false))
                        )
                        .and(ANSWER.EXPERIMENT.eq(experiment))
                        .groupBy(ANSWER_RESERVATION.ID_ANSWER_RESERVATION)
                        .having(countRating.lessThan(
                                DSL.select(EXPERIMENT.RATINGS_PER_ANSWER)
                                    .from(EXPERIMENT)
                                    .where(EXPERIMENT.ID_EXPERIMENT.eq(experiment))
                        ))
        );
    }

    /**
     * returns all the ratings pointing to the same answer
     * @param answersID the answer-ID
     * @return a list of ratings
     */
    public List<RatingRecord> getRelatedRatings(int answersID) {
        return create.selectFrom(RATING)
                    .where(RATING.ANSWER_R.eq(answersID))
                    .fetch();
    }


    /**
     * Gets the AnswerRecord belonging to the specified rating
     * @param rating of the answerRecord
     * @return AnswerRecord of specified rating
     */
    public Optional<AnswerRecord> getAnswerFromRating(Rating rating){
        return create.selectFrom(ANSWER)
                .where(ANSWER.ID_ANSWER.eq(
                        DSL.select(RATING_RESERVATION.ANSWER)
                                .from(RATING_RESERVATION)
                                .where(RATING_RESERVATION.ID_RESERVERD_RATING.eq(rating.getReservation()))
                ))
                .fetchOptional();
    }

    /**
     * Sets system-response-field of specified answerRecord
     */
    public void setSystemResponseField(AnswerRecord answerRecord, String systemResponse){
        assertHasField(answerRecord, ANSWER.ID_ANSWER);
        create.update(ANSWER)
                .set(ANSWER.SYSTEM_RESPONSE, systemResponse)
                .where(ANSWER.ID_ANSWER.eq(answerRecord.getIdAnswer()))
                .execute();
    }

    /**
     * Updates the Answer-Record in the database
     * @param answer the answer
     */
    public void updateAnswer(AnswerRecord answer){
        create.executeUpdate(answer);
    }

    /**
     * Returns mapping of duplicate-answers to their corresponding original-answers.
     * <p>
     * by computing 1.0 - (((double) Long.bitCount(xor) + 1) / (65 - Long.numberOfLeadingZeros(xor))) and comparing
     * that to the threshold. xor is Answer.Hash.xor(hash).
     * @param hash the hash-value of the specified answer
     * @param experiment the primary key of the experiment
     * @param threshold the threshold
     * @return mapping of the identified duplicates to their corresponding original answer
     */
    public List<AnswerRecord> getDuplicates(long hash, int experiment, double threshold){
        Field<Long> xor = ANSWER.HASH.bitXor(hash).as("XOR");
        //bitcount(xor) + 1
        Field<Integer> dividend = DSL.bitCount(xor);
        //65 - (63 - floor(log2(xor))) or 65 - numberOfLeadingZeros(xor)
        Field<Integer> divisor = DSL.val(65).minus(DSL.val(63).minus(DSL.floor(DSL.isnull(DSL.log(xor, 2), BigDecimal.valueOf(1)))));
        return create.select(ANSWER.fields())
                .select(xor)
                .from(ANSWER)
                .where(ANSWER.EXPERIMENT.eq(experiment))
                .having(DSL.val(1.0).minus(dividend.divide(divisor)).greaterOrEqual(threshold))
                .fetchInto(ANSWER);
    }


    /**
     * Retrieves all answers which were not checked by the duplicate-checker before
     * @return list of all unchecked answers
     */
    public List<AnswerRecord> getAnswersWithoutHash(int expID) {
        return create.selectFrom(ANSWER)
                .where(ANSWER.HASH.isNull())
                .and(ANSWER.EXPERIMENT.eq(expID))
                .fetch();
    }

}
