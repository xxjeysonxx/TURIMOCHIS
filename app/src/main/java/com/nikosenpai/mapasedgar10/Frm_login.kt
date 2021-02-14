package com.nikosenpai.mapasedgar10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth;
import kotlinx.android.synthetic.main.activity_main.*

class Frm_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    private fun setup()
    {
        cmd_register.setOnClickListener()
        {
            if (txt_usuario.text.isNotEmpty() && txt_contraseña.text.isNotEmpty())
            {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    txt_usuario.text.toString(),
                    txt_contraseña.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        ShowHome(it.result?.user?.email?:"",ProviderType.BASIC)
                        }
                    else
                    {
                        //showAlert()
                    }

            }
        }
 }
}
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR!!")
        builder.setMessage("Se ha producido un error autenticado al usuario")
        builder.setPositiveButton("OK",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun ShowHome(email: String, Provider: ProviderType)
    {
        val HomeIntent = Intent(this, frm_mapas::class.java).apply {
            putExtra("email",email)
            putExtra("provider",Provider)
        }
        startActivity(HomeIntent)

    }
}