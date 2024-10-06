package com.example.intentpractice.database

import android.content.Context
import com.example.intentpractice.utils.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.intentpractice.dao.IngredientesBaseDao
import com.example.intentpractice.dao.NomesIngredienteDao
import com.example.intentpractice.dao.ReceitaModelDao
import com.example.intentpractice.dao.UserDao
import com.example.intentpractice.model.IngredientesBaseModel
import com.example.intentpractice.model.NomesIngredienteModel
import com.example.intentpractice.model.ReceitaModel
import com.example.intentpractice.model.User

@Database
    (
    entities = [User::class, NomesIngredienteModel::class, IngredientesBaseModel::class, ReceitaModel::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun nomesIngredienteDao(): NomesIngredienteDao
    abstract fun ingredientesBaseDao(): IngredientesBaseDao
    abstract fun receitaModelDao(): ReceitaModelDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val MIGRATION_1_2 = object : androidx.room.migration.Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Adiciona a coluna 'name' com valor padrão vazio
                database.execSQL("ALTER TABLE user_table ADD COLUMN name TEXT NOT NULL DEFAULT ''")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration() // Adiciona esta linha para permitir migrações destrutivas
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
