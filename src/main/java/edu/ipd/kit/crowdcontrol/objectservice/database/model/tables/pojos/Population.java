/**
 * This class is generated by jOOQ
 */
package edu.ipd.kit.crowdcontrol.objectservice.database.model.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Population implements java.io.Serializable {

	private static final long serialVersionUID = 278061879;

	private java.lang.Integer idpopulation;
	private java.lang.Integer experiment;
	private java.lang.String  volume;
	private java.lang.Object  platformData;

	public Population() {}

	public Population(
		java.lang.Integer idpopulation,
		java.lang.Integer experiment,
		java.lang.String  volume,
		java.lang.Object  platformData
	) {
		this.idpopulation = idpopulation;
		this.experiment = experiment;
		this.volume = volume;
		this.platformData = platformData;
	}

	public java.lang.Integer getIdpopulation() {
		return this.idpopulation;
	}

	public void setIdpopulation(java.lang.Integer idpopulation) {
		this.idpopulation = idpopulation;
	}

	public java.lang.Integer getExperiment() {
		return this.experiment;
	}

	public void setExperiment(java.lang.Integer experiment) {
		this.experiment = experiment;
	}

	public java.lang.String getVolume() {
		return this.volume;
	}

	public void setVolume(java.lang.String volume) {
		this.volume = volume;
	}

	public java.lang.Object getPlatformData() {
		return this.platformData;
	}

	public void setPlatformData(java.lang.Object platformData) {
		this.platformData = platformData;
	}
}
