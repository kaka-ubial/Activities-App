package com.example.intentpractice

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.intentpractice.ViewModel.UserProfileViewModel
import com.example.intentpractice.ViewModel.UserProfileViewModelFactory
import com.example.intentpractice.MainMenu
import com.example.intentpractice.database.AppDatabase
import com.example.intentpractice.databinding.ActivityUserProfileBinding
import com.example.intentpractice.repository.UserRepository
import kotlinx.coroutines.launch

class UserProfile : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private lateinit var userRepository: UserRepository
    private lateinit var userProfileViewModel: UserProfileViewModel

    private var currentUserName: String = ""
    private var currentUserEmail: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appDatabase = AppDatabase.getDatabase(applicationContext)
        userRepository = UserRepository(appDatabase.userDao())

        userProfileViewModel = UserProfileViewModelFactory(userRepository).create(UserProfileViewModel::class.java)

        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val userId = sharedPreferences.getInt("logged_user_id", -1)

        if (userId != -1) {
            lifecycleScope.launch {
                userProfileViewModel.getUserById(userId) { user ->
                    user?.let {
                        binding.nameEdit.setText(it.name)
                        binding.emailEdit.setText(it.email)
                        currentUserName = it.name
                        binding.welcome.text = "Bem-vindo, $currentUserName"
                        currentUserEmail = it.email // Armazena o email atual
                    } ?: run {
                        showToast("User not found")
                    }
                }
            }
        } else {
            showToast("User ID not found. Please log in again.")
        }

        binding.button2.setOnClickListener {
            val newName = binding.nameEdit.text.toString()
            val newEmail = binding.emailEdit.text.toString()
            

            // Verifica se o nome ou email foi alterado
            if (newName != currentUserName || newEmail != currentUserEmail) {
                lifecycleScope.launch {
                    var isUpdated = true

                    if (newName != currentUserName) {
                        isUpdated = userProfileViewModel.updateName(userId, newName)
                        binding.welcome.text = "Bem-vindo, $newName"
                    }

                    if (newEmail != currentUserEmail) {
                        isUpdated = isUpdated && userProfileViewModel.updateEmail(userId, newEmail)
                    }

                    showToast(if (isUpdated) "Profile updated successfully" else "Failed to update profile")
                }
            } else {
                showToast("No changes detected")
            }
        }

        binding.deleteAccount.setOnClickListener {
            val email = binding.emailEdit.text.toString()
            if (email.isNotEmpty()) {
                lifecycleScope.launch {
                    val isDeleted = userProfileViewModel.deleteUserAccount(email)
                    showToast(if (isDeleted) "Account deleted successfully" else "Failed to delete account")
                    if (isDeleted) {
                        // Iniciar a atividade de login
                        startActivity(Intent(this@UserProfile, Login::class.java))
                        finish() // Finaliza a atividade atual
                    }
                }
            } else {
                showToast("Please enter an email")
            }
        }

        binding.goBackBtn.setOnClickListener{
            val goBack = Intent(this, MainMenu::class.java)
            startActivity(goBack)
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this@UserProfile, message, Toast.LENGTH_SHORT).show()
    }
}
