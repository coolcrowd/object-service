/**
 * This class is generated by jOOQ
 */
package edu.kit.ipd.crowdcontrol.objectservice.database.model.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class NotificationToken extends org.jooq.impl.TableImpl<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord> {

	private static final long serialVersionUID = -110155130;

	/**
	 * The singleton instance of <code>crowdcontrol.Notification_Token</code>
	 */
	public static final edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.NotificationToken NOTIFICATION_TOKEN = new edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.NotificationToken();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord> getRecordType() {
		return edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord.class;
	}

	/**
	 * The column <code>crowdcontrol.Notification_Token.id_notification_Token</code>.
	 */
	public final org.jooq.TableField<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord, java.lang.Integer> ID_NOTIFICATION_TOKEN = createField("id_notification_Token", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Notification_Token.result_id</code>.
	 */
	public final org.jooq.TableField<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord, java.lang.Integer> RESULT_ID = createField("result_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Notification_Token.result_token</code>.
	 */
	public final org.jooq.TableField<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord, java.lang.String> RESULT_TOKEN = createField("result_token", org.jooq.impl.SQLDataType.VARCHAR.length(191).nullable(false), this, "");

	/**
	 * The column <code>crowdcontrol.Notification_Token.notification</code>.
	 */
	public final org.jooq.TableField<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord, java.lang.Integer> NOTIFICATION = createField("notification", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>crowdcontrol.Notification_Token</code> table reference
	 */
	public NotificationToken() {
		this("Notification_Token", null);
	}

	/**
	 * Create an aliased <code>crowdcontrol.Notification_Token</code> table reference
	 */
	public NotificationToken(java.lang.String alias) {
		this(alias, edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.NotificationToken.NOTIFICATION_TOKEN);
	}

	private NotificationToken(java.lang.String alias, org.jooq.Table<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord> aliased) {
		this(alias, aliased, null);
	}

	private NotificationToken(java.lang.String alias, org.jooq.Table<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, edu.kit.ipd.crowdcontrol.objectservice.database.model.Crowdcontrol.CROWDCONTROL, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord, java.lang.Integer> getIdentity() {
		return edu.kit.ipd.crowdcontrol.objectservice.database.model.Keys.IDENTITY_NOTIFICATION_TOKEN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord> getPrimaryKey() {
		return edu.kit.ipd.crowdcontrol.objectservice.database.model.Keys.KEY_NOTIFICATION_TOKEN_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord>>asList(edu.kit.ipd.crowdcontrol.objectservice.database.model.Keys.KEY_NOTIFICATION_TOKEN_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.records.NotificationTokenRecord, ?>>asList(edu.kit.ipd.crowdcontrol.objectservice.database.model.Keys.BELONGING_NOTIFICATION);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.NotificationToken as(java.lang.String alias) {
		return new edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.NotificationToken(alias, this);
	}

	/**
	 * Rename this table
	 */
	public edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.NotificationToken rename(java.lang.String name) {
		return new edu.kit.ipd.crowdcontrol.objectservice.database.model.tables.NotificationToken(name, null);
	}
}
