/**
 * This class is generated by jOOQ
 */
package edu.ipd.kit.crowdcontrol.proto.databasemodel.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Hit implements java.io.Serializable {

	private static final long serialVersionUID = 2122592503;

	private java.lang.Integer idhit;
	private java.lang.Integer experimentH;
	private java.lang.String  type;
	private java.lang.Boolean running;
	private java.lang.Integer currentAmount;
	private java.lang.Integer maxAmount;
	private java.lang.Integer payment;
	private java.lang.Integer bonus;

	public Hit() {}

	public Hit(
		java.lang.Integer idhit,
		java.lang.Integer experimentH,
		java.lang.String  type,
		java.lang.Boolean running,
		java.lang.Integer currentAmount,
		java.lang.Integer maxAmount,
		java.lang.Integer payment,
		java.lang.Integer bonus
	) {
		this.idhit = idhit;
		this.experimentH = experimentH;
		this.type = type;
		this.running = running;
		this.currentAmount = currentAmount;
		this.maxAmount = maxAmount;
		this.payment = payment;
		this.bonus = bonus;
	}

	public java.lang.Integer getIdhit() {
		return this.idhit;
	}

	public void setIdhit(java.lang.Integer idhit) {
		this.idhit = idhit;
	}

	public java.lang.Integer getExperimentH() {
		return this.experimentH;
	}

	public void setExperimentH(java.lang.Integer experimentH) {
		this.experimentH = experimentH;
	}

	public java.lang.String getType() {
		return this.type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.Boolean getRunning() {
		return this.running;
	}

	public void setRunning(java.lang.Boolean running) {
		this.running = running;
	}

	public java.lang.Integer getCurrentAmount() {
		return this.currentAmount;
	}

	public void setCurrentAmount(java.lang.Integer currentAmount) {
		this.currentAmount = currentAmount;
	}

	public java.lang.Integer getMaxAmount() {
		return this.maxAmount;
	}

	public void setMaxAmount(java.lang.Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

	public java.lang.Integer getPayment() {
		return this.payment;
	}

	public void setPayment(java.lang.Integer payment) {
		this.payment = payment;
	}

	public java.lang.Integer getBonus() {
		return this.bonus;
	}

	public void setBonus(java.lang.Integer bonus) {
		this.bonus = bonus;
	}
}
