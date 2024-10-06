package com.example.intentpractice.repository

import com.example.intentpractice.dao.NomesIngredienteDao
import com.example.intentpractice.model.NomesIngredienteModel

class NomesIngredienteRepository(private val nomesIngredienteDao: NomesIngredienteDao) {
    suspend fun insert(nomesIngrediente: NomesIngredienteModel) {
        nomesIngredienteDao.insert(nomesIngrediente)
    }

    suspend fun getAllNomesIngrediente(): List<NomesIngredienteModel> {
        return nomesIngredienteDao.getAllNomesIngrediente()
    }

    suspend fun deleteAll() {
        nomesIngredienteDao.deleteAll()
    }
}
