package com.oscar_aguilar.appdelivery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.oscar_aguilar.appdelivery.R
import com.oscar_aguilar.appdelivery.models.ResponseHttp
import com.oscar_aguilar.appdelivery.models.User
import com.oscar_aguilar.appdelivery.providers.UsersProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private val TAG = "RegisterActivity"

    private var imageViewGoToLogin: ImageView? = null
    private var editTextName: EditText? = null
    private var editTextLastname: EditText? = null
    private var editTextEmail: EditText? = null
    private var editTextPhone: EditText? = null
    private var editTextPassword: EditText? = null
    private var editTextConfirmPassword: EditText? = null
    private var buttonRegister: Button? = null

    private val usersProvider = UsersProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        imageViewGoToLogin = findViewById(R.id.image_view_go_to_login)
        editTextName = findViewById(R.id.edittext_name)
        editTextLastname = findViewById(R.id.edittext_lastname)
        editTextEmail = findViewById(R.id.edittext_email)
        editTextPhone = findViewById(R.id.edittext_phone)
        editTextPassword = findViewById(R.id.edittext_password)
        editTextConfirmPassword = findViewById(R.id.edittext_confirm_password)
        buttonRegister = findViewById(R.id.btn_register)

        imageViewGoToLogin?.setOnClickListener { goToLogin() }
        buttonRegister?.setOnClickListener { register() }
    }

    private fun register() {
        val name = editTextName?.text.toString()
        val lastName = editTextLastname?.text.toString()
        val email = editTextEmail?.text.toString()
        val phone = editTextPhone?.text.toString()
        val password = editTextPassword?.text.toString()
        val confirmPassword = editTextConfirmPassword?.text.toString()

        if (isValidForm(name, lastName, email, phone, password, confirmPassword)) {
            val user = User(
                name = name,
                lastname = lastName,
                email = email,
                phone = phone,
                password = password
            )

            usersProvider.register(user)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(
                    call: Call<ResponseHttp>,
                    response: Response<ResponseHttp>
                ) {
                    Toast.makeText(
                        this@RegisterActivity, response.body()?.message, Toast.LENGTH_LONG
                    ).show()
                    Log.d(TAG, "Response $response")
                    Log.d(TAG, "Response ${response.body()}")
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Ha ocurrido un error ${t.message}")
                    Toast.makeText(
                        this@RegisterActivity,
                        "Ha ocurrido un error ${t.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

    private fun isValidForm(
        name: String,
        lastName: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String,
    ): Boolean {
        if (name.isBlank()) {
            Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }

        if (lastName.isBlank()) {
            Toast.makeText(this, "El apellido es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isBlank()) {
            Toast.makeText(this, "El email es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!email.isEmailValid()) {
            Toast.makeText(this, "No es un email valido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (phone.isBlank()) {
            Toast.makeText(this, "El teléfono es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isBlank()) {
            Toast.makeText(this, "La contraseña es obligatoria", Toast.LENGTH_SHORT).show()
            return false
        }


        if (confirmPassword.isBlank()) {
            Toast.makeText(this, "Campo obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Los campos deben coincidir", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}