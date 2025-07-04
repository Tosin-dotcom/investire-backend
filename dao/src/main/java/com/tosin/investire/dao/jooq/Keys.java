/*
 * This file is generated by jOOQ.
 */
package com.tosin.investire.dao.jooq;


import com.tosin.investire.dao.jooq.tables.Users;
import com.tosin.investire.dao.jooq.tables.records.UsersRecord;

import javax.annotation.processing.Generated;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in the
 * default schema.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.19.21"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<UsersRecord> USERS_EMAIL_KEY = Internal.createUniqueKey(Users.USERS, DSL.name("users_email_key"), new TableField[] { Users.USERS.EMAIL }, true);
    public static final UniqueKey<UsersRecord> USERS_PKEY = Internal.createUniqueKey(Users.USERS, DSL.name("users_pkey"), new TableField[] { Users.USERS.ID }, true);
}
