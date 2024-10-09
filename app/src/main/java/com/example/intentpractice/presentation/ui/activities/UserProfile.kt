package com.example.intentpractice.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.intentpractice.presentation.viewmodel.UserProfileViewModel
import com.example.intentpractice.presentation.ui.factory.UserProfileViewModelFactory
import com.example.intentpractice.data.database.AppDatabase
import com.example.intentpractice.databinding.ActivityUserProfileBinding
import com.example.intentpractice.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserProfile : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private lateinit var userRepository: UserRepository
    private lateinit var userProfileViewModel: UserProfileViewModel

    private var currentUserName: String = ""
    private var currentUserEmail: String = ""
    private var currentUserPassword: String = "" // Armazena a senha atual

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appDatabase = AppDatabase.getDatabase(applicationContext)
        userRepository = UserRepository(appDatabase.userDao())

        userProfileViewModel = UserProfileViewModelFactory(userRepository).create(
            UserProfileViewModel::class.java)

        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val userId = sharedPreferences.getInt("logged_user_id", -1)

        if (userId != -1) {
            lifecycleScope.launch {
                userProfileViewModel.getUserById(userId) { user ->
                    user?.let {
                        binding.nameEdit.setText(it.name)
                        binding.emailEdit.setText(it.email)
                        currentUserName = it.name
                        currentUserEmail = it.email // Armazena o email atual
                        currentUserPassword = it.password // Armazena a senha atual
                        binding.welcome.text = "Bem-vindo, $currentUserName"
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
            val currentPasswordInput = binding.passwordEdit.text.toString()
            val newPassword = binding.editTextTextPassword3.text.toString()

            // Variável de controle para rastrear se algo foi atualizado
            var isUpdated = false

            lifecycleScope.launch {
                // Verifica se o nome foi alterado
                if (newName != currentUserName) {
                    val nameUpdated = userProfileViewModel.updateName(userId, newName)
                    if (nameUpdated) {
                        binding.welcome.text = "Bem-vindo, $newName"
                        currentUserName = newName // Atualiza o nome localmente após a mudança
                        isUpdated = true
                    }
                }

                // Verifica se o email foi alterado
                if (newEmail != currentUserEmail) {
                    val emailUpdated = userProfileViewModel.updateEmail(userId, newEmail)
                    if (emailUpdated) {
                        currentUserEmail = newEmail // Atualiza o email localmente após a mudança
                        isUpdated = true
                    }
                }

                // Verifica se a senha foi alterada
                if (currentPasswordInput.isNotEmpty()) {
                    if (currentPasswordInput == currentUserPassword && newPassword.isNotEmpty() && newPassword != currentUserPassword) {
                        val passwordUpdated = userProfileViewModel.updatePassword(userId, newPassword)
                        if (passwordUpdated) {
                            currentUserPassword = newPassword // Atualiza a senha localmente após a mudança
                            isUpdated = true
                        } else {
                            showToast("Failed to update password")
                        }
                    } else if (currentPasswordInput != currentUserPassword) {
                        showToast("Incorrect current password")
                    }
                }

                // Feedback ao usuário
                if (isUpdated) {
                    showToast("Profile updated successfully")
                } else {
                    showToast("No changes detected or update failed")
                }
            }
        }

        // Excluir conta: exige que a senha seja inserida corretamente
        binding.deleteAccount.setOnClickListener {
            val email = binding.emailEdit.text.toString()
            val passwordInput = binding.passwordEdit.text.toString()

            // Verifica se o email e a senha estão preenchidos
            if (email.isNotEmpty() && passwordInput.isNotEmpty()) {
                // Verifica se a senha fornecida corresponde à senha atual do usuário
                if (passwordInput == currentUserPassword) {
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
                    showToast("Incorrect password. Cannot delete account.")
                }
            } else {
                showToast("Please enter both email and password.")
            }
        }

        binding.goBackButton.setOnClickListener {
            val goBack = Intent(this, MainMenu::class.java)
            startActivity(goBack)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@UserProfile, message, Toast.LENGTH_SHORT).show()
    }
}