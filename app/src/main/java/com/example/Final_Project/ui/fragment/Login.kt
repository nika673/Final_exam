package com.example.Final_Project.ui.fragment

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

import androidx.navigation.Navigation

import com.example.Final_Project.R
import com.example.Final_Project.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth


class Login : Fragment(R.layout.fragment_login) {
    private lateinit var mail: EditText
    private lateinit var pass: EditText
    private lateinit var login: Button
    private lateinit var register: Button
    private lateinit var reset: Button

    companion object {
        private const val CHANNEL_ID = "MY_CHANNEL"
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mail = view.findViewById(R.id.loginmail)
        pass = view.findViewById(R.id.loginpass)
        login = view.findViewById(R.id.login)
        register = view.findViewById(R.id.register)
        reset = view.findViewById(R.id.resetpass)


        val navController = Navigation.findNavController(view)

        createNotificationChannel()

        val notificationBuilder =
            NotificationCompat.Builder(this@Login.requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_restaurant_menu_24).setContentTitle("Final")
                .setContentText("თქვენ წარმატებით გაიარეთ ავტორიზაცია! შეარჩიეთ თქვენთვის სასურველი კერძი მოსამზადებლად და ისიამოვნეთ.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)



        register.setOnClickListener {
            val register = LoginDirections.actionLoginFragmentiToRegisterFragmenti()
            navController.navigate(register)

        }

        reset.setOnClickListener {
            val reset = LoginDirections.actionLoginFragmentiToResetPassFragmenti()
            navController.navigate(reset)
        }

        login.setOnClickListener {
            val email = mail.text.toString()
            val password = pass.text.toString()
            val login = LoginDirections.actionLoginFragmentiToHomeFragment()


            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this@Login.requireActivity(),
                    "გთხოვთ, შეიყვანეთ Email და პაროლი",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        navController.navigate(login)
                        NotificationManagerCompat.from(this@Login.requireContext())
                            .notify(1, notificationBuilder.build())

                    } else {
                        Toast.makeText(
                            this@Login.requireActivity(),
                            "გთხოვთ, შეიყვანეთ სწორი Email და პაროლი",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "NAME"
            val descriptionText = "Desc"
            val importance = NotificationManager.IMPORTANCE_DEFAULT


            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = descriptionText

            val notificationManager: NotificationManager =
                activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            NotificationManagerCompat.from(this.requireContext()).createNotificationChannel(channel)
        }

    }

}
