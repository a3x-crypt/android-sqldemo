package xyz.atriple.study.sqldemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(
    context: Context?
) : SQLiteOpenHelper(context, "customer.db", null, 1) {
    val CUSTOMER_TABLE: String = "CUSTOMER_TABLE"
    val CUSTOMER_NAME: String = "CUSTOMER_NAME"
    val CUSTOMER_AGE: String = "CUSTOMER_AGE"
    val ACTIVE_CUSTOMER: String = "ACTIVE_CUSTOMER"
    val COLUMN_ID: String = "ID"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement: String =
            "CREATE TABLE $CUSTOMER_TABLE ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $CUSTOMER_NAME TEXT, $CUSTOMER_AGE INT, $ACTIVE_CUSTOMER BOOL)"

        db?.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addOne(customer: Customer): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(CUSTOMER_NAME, customer.name)
        cv.put(CUSTOMER_AGE, customer.age)
        cv.put(ACTIVE_CUSTOMER, customer.isActive)
        val insert: Long = db.insert(CUSTOMER_TABLE, null, cv)

        return insert != -1L
    }
}