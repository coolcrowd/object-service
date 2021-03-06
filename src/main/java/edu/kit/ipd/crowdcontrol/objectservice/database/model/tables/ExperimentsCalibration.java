/**
 * This class is generated by jOOQ
 */
package edu.kit.ipd.crowdcontrol.objectservice.database.model.tables;


import edu.kit.ipd.crowdcontrol.objectservice.database.model.Crowdcontrol;
import edu.kit.ipd.crowdcontrol.objectservice.database.model.Keys;
import edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.ExperimentsCalibrationRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.3"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ExperimentsCalibration extends TableImpl<ExperimentsCalibrationRecord> {

	private static final long serialVersionUID = -1499044428;

	/**
	 * The reference instance of <code>crowdcontrol.Experiments_Calibration</code>
	 */
	public static final ExperimentsCalibration EXPERIMENTS_CALIBRATION = new ExperimentsCalibration();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<ExperimentsCalibrationRecord> getRecordType() {
		return ExperimentsCalibrationRecord.class;
	}

	/**
	 * The column <code>crowdcontrol.Experiments_Calibration.id_experiments_calibration</code>.
	 */
	public final TableField<ExperimentsCalibrationRecord, Integer> ID_EXPERIMENTS_CALIBRATION = createField("id_experiments_calibration", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Experiments_Calibration.experiments_platform</code>.
	 */
	public final TableField<ExperimentsCalibrationRecord, Integer> EXPERIMENTS_PLATFORM = createField("experiments_platform", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Experiments_Calibration.answer</code>.
	 */
	public final TableField<ExperimentsCalibrationRecord, Integer> ANSWER = createField("answer", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Experiments_Calibration.not</code>.
	 */
	public final TableField<ExperimentsCalibrationRecord, Boolean> NOT = createField("not", org.jooq.impl.SQLDataType.BIT.nullable(false), this, "");

	/**
	 * Create a <code>crowdcontrol.Experiments_Calibration</code> table reference
	 */
	public ExperimentsCalibration() {
		this("Experiments_Calibration", null);
	}

	/**
	 * Create an aliased <code>crowdcontrol.Experiments_Calibration</code> table reference
	 */
	public ExperimentsCalibration(String alias) {
		this(alias, EXPERIMENTS_CALIBRATION);
	}

	private ExperimentsCalibration(String alias, Table<ExperimentsCalibrationRecord> aliased) {
		this(alias, aliased, null);
	}

	private ExperimentsCalibration(String alias, Table<ExperimentsCalibrationRecord> aliased, Field<?>[] parameters) {
		super(alias, Crowdcontrol.CROWDCONTROL, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<ExperimentsCalibrationRecord, Integer> getIdentity() {
		return Keys.IDENTITY_EXPERIMENTS_CALIBRATION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<ExperimentsCalibrationRecord> getPrimaryKey() {
		return Keys.KEY_EXPERIMENTS_CALIBRATION_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<ExperimentsCalibrationRecord>> getKeys() {
		return Arrays.<UniqueKey<ExperimentsCalibrationRecord>>asList(Keys.KEY_EXPERIMENTS_CALIBRATION_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<ExperimentsCalibrationRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<ExperimentsCalibrationRecord, ?>>asList(Keys.REFERENCED_EXPERIMENTS_PLATFORM, Keys.REFERENCED_ANSWER);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExperimentsCalibration as(String alias) {
		return new ExperimentsCalibration(alias, this);
	}

	/**
	 * Rename this table
	 */
	public ExperimentsCalibration rename(String name) {
		return new ExperimentsCalibration(name, null);
	}
}
