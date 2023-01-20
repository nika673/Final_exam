package com.example.Final_Project.ui.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
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

class Reset : Fragment(R.layout.fragment_reset) {
    private lateinit var mail: EditText
    private lateinit var send: Button


    companion object {
        private const val CHANNEL_ID = "MY_CHANNEL"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mail = view.findViewById(R.id.resetmail)
        send = view.findViewById(R.id.resetmailpass)
        val navController = Navigation.findNavController(view)

        createNotificationChannel()

        val notificationBuilder = NotificationCompat.Builder(
            this.requireContext(),
            CHANNEL_ID
        )
            .setSmallIcon(R.drawable.baseline_restaurant_menu_24).setContentTitle("Final")
            .setContentText("თქვენი მოთხოვნა, პაროლის აღდგენასთან დაკავშირებით, მიღებულია!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)



        send.setOnClickListener {
            val email = mail.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(
                    this@Reset.requireActivity(),
                    "Please, input Email",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@Reset.requireActivity(),
                            "Recovery link sent, Check Email",
                            Toast.LENGTH_SHORT
                        ).show()
                        val login = ResetDirections.actionResetPassFragmentiToLoginFragmenti()
                        navController.navigate(login)
                        NotificationManagerCompat.from(this.requireContext())
                            .notify(1, notificationBuilder.build())

                    } else {
                        Toast.makeText(
                            this@Reset.requireActivity(),
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