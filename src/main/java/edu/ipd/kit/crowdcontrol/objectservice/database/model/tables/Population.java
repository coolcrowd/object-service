/**
 * This class is generated by jOOQ
 */
package edu.ipd.kit.crowdcontrol.objectservice.database.model.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Population extends org.jooq.impl.TableImpl<edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord> {

	private static final long serialVersionUID = -1914882670;

	/**
	 * The singleton instance of <code>crowdcontrol.Population</code>
	 */
	public static final edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.Population POPULATION = new edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.Population();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord> getRecordType() {
		return edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord.class;
	}

	/**
	 * The column <code>crowdcontrol.Population.idPopulation</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord, java.lang.Integer> IDPOPULATION = createField("idPopulation", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Population.property</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord, java.lang.String> PROPERTY = createField("property", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Population.description</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>crowdcontrol.Population.name</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Population.experiment</code>.
	 */
	public final org.jooq.TableField<edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord, java.lang.Integer> EXPERIMENT = createField("experiment", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>crowdcontrol.Population</code> table reference
	 */
	public Population() {
		this("Population", null);
	}

	/**
	 * Create an aliased <code>crowdcontrol.Population</code> table reference
	 */
	public Population(java.lang.String alias) {
		this(alias, edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.Population.POPULATION);
	}

	private Population(java.lang.String alias, org.jooq.Table<edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord> aliased) {
		this(alias, aliased, null);
	}

	private Population(java.lang.String alias, org.jooq.Table<edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, edu.ipd.kit.crowdcontrol.objectservice.database.model.Crowdcontrol.CROWDCONTROL, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord> getPrimaryKey() {
		return edu.ipd.kit.crowdcontrol.objectservice.database.model.Keys.KEY_POPULATION_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.records.PopulationRecord>>asList(edu.ipd.kit.crowdcontrol.objectservice.database.model.Keys.KEY_POPULATION_PRIMARY, edu.ipd.kit.crowdcontrol.objectservice.database.model.Keys.KEY_POPULATION_EXPERIMENT_UNIQUE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.Population as(java.lang.String alias) {
		return new edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.Population(alias, this);
	}

	/**
	 * Rename this table
	 */
	public edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.Population rename(java.lang.String name) {
		return new edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.Population(name, null);
	}
}