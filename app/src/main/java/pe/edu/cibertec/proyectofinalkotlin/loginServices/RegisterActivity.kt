package pe.edu.cibertec.proyectofinalkotlin.loginServices

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import pe.edu.cibertec.proyectofinalkotlin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setUp()
    }

    private fun setUp() {
        title = "Registro"

        val email = binding.txtEmail.text
        val contrasenia = binding.txtContrasenia.text

        binding.btnRegister.setOnClickListener {
            if (email.isNotEmpty() && contrasenia.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.toString(), contrasenia.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showSuccess()
                            navigateLogin()
                        } else {
                            showAlert()
                        }
                    }
            }
        }

        binding.btnVolver.setOnClickListener {
            navigateLogin()
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al registrar el usuario, la contraseña debe tener mínimo 6 carácteres")
        builder.setPositiveButton("Ok",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showSuccess() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registro")
        builder.setMessage("Usuario registrado con éxito")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun navigateLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
    }
}