/**
 * This class is generated by jOOQ
 */
package edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value = {"http://www.jooq.org", "3.4.1"},
        comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ChosenRatingQualityParam implements java.io.Serializable {

    private static final long serialVersionUID = -1240929643;

    private java.lang.Integer idAlgorithmRatingQualityChosen;
    private java.lang.String value;
    private java.lang.Integer experiment;
    private java.lang.Integer param;

    public ChosenRatingQualityParam() {
    }

    public ChosenRatingQualityParam(
            java.lang.Integer idAlgorithmRatingQualityChosen,
            java.lang.String value,
            java.lang.Integer experiment,
            java.lang.Integer param
    ) {
        this.idAlgorithmRatingQualityChosen = idAlgorithmRatingQualityChosen;
        this.value = value;
        this.experiment = experiment;
        this.param = param;
    }

    public java.lang.Integer getIdAlgorithmRatingQualityChosen() {
        return this.idAlgorithmRatingQualityChosen;
    }

    public void setIdAlgorithmRatingQualityChosen(java.lang.Integer idAlgorithmRatingQualityChosen) {
        this.idAlgorithmRatingQualityChosen = idAlgorithmRatingQualityChosen;
    }

    public java.lang.String getValue() {
        return this.value;
    }

    public void setValue(java.lang.String value) {
        this.value = value;
    }

    public java.lang.Integer getExperiment() {
        return this.experiment;
    }

    public void setExperiment(java.lang.Integer experiment) {
        this.experiment = experiment;
    }

    public java.lang.Integer getParam() {
        return this.param;
    }

    public void setParam(java.lang.Integer param) {
        this.param = param;
    }
}
