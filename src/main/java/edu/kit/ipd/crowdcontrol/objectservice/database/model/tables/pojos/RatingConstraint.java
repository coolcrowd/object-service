/**
 * This class is generated by jOOQ
 */
package edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RatingConstraint implements java.io.Serializable {

	private static final long serialVersionUID = 1259499292;

	private java.lang.Integer idRatingConstraints;
	private java.lang.Integer refRating;
	private java.lang.Integer offConstraint;

	public RatingConstraint() {}

	public RatingConstraint(
		java.lang.Integer idRatingConstraints,
		java.lang.Integer refRating,
		java.lang.Integer offConstraint
	) {
		this.idRatingConstraints = idRatingConstraints;
		this.refRating = refRating;
		this.offConstraint = offConstraint;
	}

	public java.lang.Integer getIdRatingConstraints() {
		return this.idRatingConstraints;
	}

	public void setIdRatingConstraints(java.lang.Integer idRatingConstraints) {
		this.idRatingConstraints = idRatingConstraints;
	}

	public java.lang.Integer getRefRating() {
		return this.refRating;
	}

	public void setRefRating(java.lang.Integer refRating) {
		this.refRating = refRating;
	}

	public java.lang.Integer getOffConstraint() {
		return this.offConstraint;
	}

	public void setOffConstraint(java.lang.Integer offConstraint) {
		this.offConstraint = offConstraint;
	}
}