package xyz.atriple.study.sqldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add.setOnClickListener { _: View ->
            var customer: Customer
            try {
                customer = Customer(
                    -1,
                    et_name.text.toString(),
                    et_age.text.toString().toInt(),
                    switch_active_customer.isChecked
                )
                Toast.makeText(this, customer.toString(), Toast.LENGTH_SHORT).show()
            }
            catch(e: Exception) {
                Toast.makeText(this, "Error creating customer", Toast.LENGTH_SHORT).show()
                customer = Customer(-1, "error", 0, false)
            }

            val databaseHelper: DatabaseHelper = DatabaseHelper(this)
            databaseHelper.addOne(customer)
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
        btn_viewAll.setOnClickListener { _: View ->
            Toast.makeText(
                this,
                "View All",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}