/**
 * This class is generated by jOOQ
 */
package edu.kit.ipd.crowdcontrol.objectservice.database.model.tables;


import edu.kit.ipd.crowdcontrol.objectservice.database.model.Crowdcontrol;
import edu.kit.ipd.crowdcontrol.objectservice.database.model.Keys;
import edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.AnswerReservationRecord;

import java.sql.Timestamp;
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
public class AnswerReservation extends TableImpl<AnswerReservationRecord> {

	private static final long serialVersionUID = 2097864487;

	/**
	 * The reference instance of <code>crowdcontrol.Answer_Reservation</code>
	 */
	public static final AnswerReservation ANSWER_RESERVATION = new AnswerReservation();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<AnswerReservationRecord> getRecordType() {
		return AnswerReservationRecord.class;
	}

	/**
	 * The column <code>crowdcontrol.Answer_Reservation.idAnswer_Reservation</code>.
	 */
	public final TableField<AnswerReservationRecord, Integer> IDANSWER_RESERVATION = createField("idAnswer_Reservation", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Answer_Reservation.worker</code>.
	 */
	public final TableField<AnswerReservationRecord, Integer> WORKER = createField("worker", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Answer_Reservation.experiment</code>.
	 */
	public final TableField<AnswerReservationRecord, Integer> EXPERIMENT = createField("experiment", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Answer_Reservation.timestamp</code>.
	 */
	public final TableField<AnswerReservationRecord, Timestamp> TIMESTAMP = createField("timestamp", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>crowdcontrol.Answer_Reservation</code> table reference
	 */
	public AnswerReservation() {
		this("Answer_Reservation", null);
	}

	/**
	 * Create an aliased <code>crowdcontrol.Answer_Reservation</code> table reference
	 */
	public AnswerReservation(String alias) {
		this(alias, ANSWER_RESERVATION);
	}

	private AnswerReservation(String alias, Table<AnswerReservationRecord> aliased) {
		this(alias, aliased, null);
	}

	private AnswerReservation(String alias, Table<AnswerReservationRecord> aliased, Field<?>[] parameters) {
		super(alias, Crowdcontrol.CROWDCONTROL, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<AnswerReservationRecord, Integer> getIdentity() {
		return Keys.IDENTITY_ANSWER_RESERVATION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<AnswerReservationRecord> getPrimaryKey() {
		return Keys.KEY_ANSWER_RESERVATION_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<AnswerReservationRecord>> getKeys() {
		return Arrays.<UniqueKey<AnswerReservationRecord>>asList(Keys.KEY_ANSWER_RESERVATION_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<AnswerReservationRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<AnswerReservationRecord, ?>>asList(Keys.WORKER_WIL_ANSWER, Keys.EXPERIMENT_WILL_BE_ANSWERED);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AnswerReservation as(String alias) {
		return new AnswerReservation(alias, this);
	}

	/**
	 * Rename this table
	 */
	public AnswerReservation rename(String name) {
		return new AnswerReservation(name, null);
	}
}
