/**
 * This class is generated by jOOQ
 */
package edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Experiment extends org.jooq.impl.TableImpl<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord> {

	private static final long serialVersionUID = 1877062859;

	/**
	 * The singleton instance of <code>crowdcontrol.Experiment</code>
	 */
	public static final edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Experiment EXPERIMENT = new edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Experiment();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord> getRecordType() {
		return edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord.class;
	}

	/**
	 * The column <code>crowdcontrol.Experiment.idexperiment</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.Integer> IDEXPERIMENT = createField("idexperiment", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Experiment.question</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.String> QUESTION = createField("question", org.jooq.impl.SQLDataType.VARCHAR.length(45), this, "");

	/**
	 * The column <code>crowdcontrol.Experiment.rating_options</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.Object> RATING_OPTIONS = createField("rating_options", org.jooq.impl.DefaultDataType.getDefaultDataType("json"), this, "");

	/**
	 * The column <code>crowdcontrol.Experiment.titel</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.String> TITEL = createField("titel", org.jooq.impl.SQLDataType.VARCHAR.length(45), this, "");

	/**
	 * The column <code>crowdcontrol.Experiment.answer_description</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.String> ANSWER_DESCRIPTION = createField("answer_description", org.jooq.impl.SQLDataType.VARCHAR.length(45), this, "");

	/**
	 * The column <code>crowdcontrol.Experiment.rating_description</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.String> RATING_DESCRIPTION = createField("rating_description", org.jooq.impl.SQLDataType.VARCHAR.length(45), this, "");

	/**
	 * The column <code>crowdcontrol.Experiment.ratings_per_answer</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.Integer> RATINGS_PER_ANSWER = createField("ratings_per_answer", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>crowdcontrol.Experiment.algorithm_task_chooser</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.String> ALGORITHM_TASK_CHOOSER = createField("algorithm_task_chooser", org.jooq.impl.SQLDataType.VARCHAR.length(45), this, "");

	/**
	 * The column <code>crowdcontrol.Experiment.algorithm_quality_answer</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.String> ALGORITHM_QUALITY_ANSWER = createField("algorithm_quality_answer", org.jooq.impl.SQLDataType.VARCHAR.length(45), this, "");

	/**
	 * The column <code>crowdcontrol.Experiment.algorithm_quality_rating</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.String> ALGORITHM_QUALITY_RATING = createField("algorithm_quality_rating", org.jooq.impl.SQLDataType.VARCHAR.length(45), this, "");

	/**
	 * The column <code>crowdcontrol.Experiment.base_payment</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.Integer> BASE_PAYMENT = createField("base_payment", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>crowdcontrol.Experiment.bonus_answer</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.Integer> BONUS_ANSWER = createField("bonus_answer", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>crowdcontrol.Experiment.bonus_rating</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord, java.lang.Integer> BONUS_RATING = createField("bonus_rating", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>crowdcontrol.Experiment</code> table reference
	 */
	public Experiment() {
		this("Experiment", null);
	}

	/**
	 * Create an aliased <code>crowdcontrol.Experiment</code> table reference
	 */
	public Experiment(java.lang.String alias) {
		this(alias, edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Experiment.EXPERIMENT);
	}

	private Experiment(java.lang.String alias, org.jooq.Table<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord> aliased) {
		this(alias, aliased, null);
	}

	private Experiment(java.lang.String alias, org.jooq.Table<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, edu.ipd.kit.crowdcontrol.objectservice.databasemodel.Crowdcontrol.CROWDCONTROL, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord> getPrimaryKey() {
		return edu.ipd.kit.crowdcontrol.objectservice.databasemodel.Keys.KEY_EXPERIMENT_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.ExperimentRecord>>asList(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.Keys.KEY_EXPERIMENT_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Experiment as(java.lang.String alias) {
		return new edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Experiment(alias, this);
	}

	/**
	 * Rename this table
	 */
	public edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Experiment rename(java.lang.String name) {
		return new edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Experiment(name, null);
	}
}
