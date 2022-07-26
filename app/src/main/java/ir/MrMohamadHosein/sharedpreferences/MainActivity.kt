package ir.MrMohamadHosein.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.MrMohamadHosein.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        // put data into shared preferences =>
        binding.btnSubmit.setOnClickListener {

            val name = binding.edtName.text.toString()
            sharedPreferences.edit().putString("name", name).apply()

            val email = binding.edtEmail.text.toString()
            sharedPreferences.edit().putString("email", email).apply()

            if (binding.radioMale.isChecked) {
                // user is male =>
                sharedPreferences.edit().putBoolean("isMale", true).apply()
            } else {
                // user is female =>
                sharedPreferences.edit().putBoolean("isMale", false).apply()
            }

        }

        // get from shared preferences =>
        val name = sharedPreferences.getString("name" , "")
        val email = sharedPreferences.getString("email" , "")
        val isMale = sharedPreferences.getBoolean("isMale" , true)

        binding.edtName.setText( name )
        binding.edtEmail.setText( email )

        if (isMale) {
            // male =>
            binding.radioMale.isChecked = true
        } else {
            // female =>
            binding.radioFemale.isChecked = true
        }

    }

    private fun functionsOfSharedPreferences() {
        val isNameAvailable = sharedPreferences.contains("name") // check exist item
        sharedPreferences.edit().remove("email").apply() // remove an item
        sharedPreferences.edit().clear().apply() // remove xml file
    }
}

