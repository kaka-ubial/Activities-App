package com.example.intentpractice.data.database

import android.content.Context
import com.example.intentpractice.utils.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.intentpractice.data.dao.BaseIngredientsDao
import com.example.intentpractice.data.dao.IngredientNamesDao
import com.example.intentpractice.data.dao.RecipeDao
import com.example.intentpractice.data.dao.UserDao
import com.example.intentpractice.data.model.BaseIngredients
import com.example.intentpractice.data.model.IngredientNames
import com.example.intentpractice.data.model.Recipe
import com.example.intentpractice.data.model.User

@Database
    (
    entities = [User::class, IngredientNames::class, BaseIngredients::class, Recipe::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun ingredientNamesDao(): IngredientNamesDao
    abstract fun baseIngredientsDao(): BaseIngredientsDao
    abstract fun recipeDao(): RecipeDao

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
