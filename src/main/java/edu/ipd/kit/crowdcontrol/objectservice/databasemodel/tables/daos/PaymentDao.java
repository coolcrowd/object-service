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
public class PaymentDao extends org.jooq.impl.DAOImpl<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.records.PaymentRecord, edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Payment, java.lang.Integer> {

	/**
	 * Create a new PaymentDao without any configuration
	 */
	public PaymentDao() {
		super(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Payment.PAYMENT, edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Payment.class);
	}

	/**
	 * Create a new PaymentDao with an attached configuration
	 */
	public PaymentDao(org.jooq.Configuration configuration) {
		super(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Payment.PAYMENT, edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Payment.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected java.lang.Integer getId(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Payment object) {
		return object.getIdpayment();
	}

	/**
	 * Fetch records that have <code>idPayment IN (values)</code>
	 */
	public java.util.List<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Payment> fetchByIdpayment(java.lang.Integer... values) {
		return fetch(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Payment.PAYMENT.IDPAYMENT, values);
	}

	/**
	 * Fetch a unique record that has <code>idPayment = value</code>
	 */
	public edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Payment fetchOneByIdpayment(java.lang.Integer value) {
		return fetchOne(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Payment.PAYMENT.IDPAYMENT, value);
	}

	/**
	 * Fetch records that have <code>worker_id IN (values)</code>
	 */
	public java.util.List<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Payment> fetchByWorkerId(java.lang.Integer... values) {
		return fetch(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Payment.PAYMENT.WORKER_ID, values);
	}

	/**
	 * Fetch records that have <code>experiment_id IN (values)</code>
	 */
	public java.util.List<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Payment> fetchByExperimentId(java.lang.Integer... values) {
		return fetch(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Payment.PAYMENT.EXPERIMENT_ID, values);
	}

	/**
	 * Fetch records that have <code>timestamp IN (values)</code>
	 */
	public java.util.List<edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.pojos.Payment> fetchByTimestamp(java.sql.Timestamp... values) {
		return fetch(edu.ipd.kit.crowdcontrol.objectservice.databasemodel.tables.Payment.PAYMENT.TIMESTAMP, values);
	}
}
