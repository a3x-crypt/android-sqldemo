package xyz.atriple.study.sqldemo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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

    fun getEveryone(): List<Customer> {
        val returnList: MutableList<Customer> = ArrayList()

        val queryString = "SELECT * FROM $CUSTOMER_TABLE"
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery(queryString, null)

        if (cursor.moveToFirst()) {
            do {
                val customerID : Int = cursor.getInt(0)
                val customerName : String = cursor.getString(1)
                val customerAge : Int = cursor.getInt(2)
                val customerActive : Boolean = cursor.getInt(3) == 1

                val customer : Customer = Customer(customerID, customerName, customerAge, customerActive)
                returnList.add(customer)
            } while (cursor.moveToNext())
        } else {

        }

        cursor.close()
        db.close()
        return returnList
    }
}