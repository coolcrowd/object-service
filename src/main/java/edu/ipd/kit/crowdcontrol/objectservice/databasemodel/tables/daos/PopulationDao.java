/**
 * This class is generated by jOOQ
 */
package edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.daos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PopulationDao extends org.jooq.impl.DAOImpl<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.PopulationRecord, edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Population, java.lang.Integer> {

	/**
	 * Create a new PopulationDao without any configuration
	 */
	public PopulationDao() {
		super(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Population.POPULATION, edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Population.class);
	}

	/**
	 * Create a new PopulationDao with an attached configuration
	 */
	public PopulationDao(org.jooq.Configuration configuration) {
		super(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Population.POPULATION, edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Population.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected java.lang.Integer getId(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Population object) {
		return object.getIdpopulation();
	}

	/**
	 * Fetch records that have <code>idPopulation IN (values)</code>
	 */
	public java.util.List<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Population> fetchByIdpopulation(java.lang.Integer... values) {
		return fetch(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Population.POPULATION.IDPOPULATION, values);
	}

	/**
	 * Fetch a unique record that has <code>idPopulation = value</code>
	 */
	public edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Population fetchOneByIdpopulation(java.lang.Integer value) {
		return fetchOne(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Population.POPULATION.IDPOPULATION, value);
	}

	/**
	 * Fetch records that have <code>experiment IN (values)</code>
	 */
	public java.util.List<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Population> fetchByExperiment(java.lang.Integer... values) {
		return fetch(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Population.POPULATION.EXPERIMENT, values);
	}

	/**
	 * Fetch records that have <code>volume IN (values)</code>
	 */
	public java.util.List<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Population> fetchByVolume(java.lang.String... values) {
		return fetch(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Population.POPULATION.VOLUME, values);
	}

	/**
	 * Fetch records that have <code>platform_data IN (values)</code>
	 */
	public java.util.List<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Population> fetchByPlatformData(java.lang.Object... values) {
		return fetch(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Population.POPULATION.PLATFORM_DATA, values);
	}
}
