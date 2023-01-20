package com.example.Final_Project.ui.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.Final_Project.R
import com.google.firebase.auth.FirebaseAuth

class Register : Fragment(R.layout.fragment_register) {


    private lateinit var mail: EditText
    private lateinit var pass: EditText
    private lateinit var register: Button
    private lateinit var back: Button

    companion object {
        private const val CHANNEL_ID = "MY_CHANNEL"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mail = view.findViewById(R.id.email2)
        pass = view.findViewById(R.id.pass2)
        register = view.findViewById(R.id.register2)
        back = view.findViewById(R.id.back)


        val navController = Navigation.findNavController(view)


        createNotificationChannel()

        val notificationBuilder = NotificationCompat.Builder(this.requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_restaurant_menu_24).setContentTitle("Final")
            .setContentText("თქვენ წარმატებით გაიარეთ რეგისტრაცია!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)


        back.setOnClickListener {


            val action = RegisterDirections.actionRegisterFragmentiToLoginFragmenti()
            navController.navigate(action)


        }


        register.setOnClickListener {

            val email = mail.text.toString()
            val password = pass.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(
                    this@Register.requireActivity(), "Please, input your Email", Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 5) {
                Toast.makeText(
                    this@Register.requireActivity(),
                    "Input your password(min 4 letter)",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@Register.requireActivity(),
                            "Registration, was successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        val goToProfile =
                            RegisterDirections.actionRegisterFragmentiToLoginFragmenti()
                        navController.navigate(goToProfile)
                        NotificationManagerCompat.from(this.requireContext())
                            .notify(1, notificationBuilder.build())

                    } else {
                        Toast.makeText(
                            this@Register.requireActivity(),
                            "There was an error",
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