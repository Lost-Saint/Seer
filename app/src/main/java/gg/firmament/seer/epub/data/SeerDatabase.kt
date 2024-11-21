package gg.firmament.seer.epub.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import gg.firmament.seer.core.util.Constants
import gg.firmament.seer.epub.data.library.LibraryDao
import gg.firmament.seer.epub.data.library.LibraryItem
import gg.firmament.seer.epub.data.progress.ProgressDao
import gg.firmament.seer.epub.data.progress.ProgressData

@Database(
	entities = [LibraryItem::class, ProgressData::class],
	version = 5,
	exportSchema = true,
	autoMigrations = [
		AutoMigration(from = 1, to = 2),
		AutoMigration(from = 2, to = 3),
		AutoMigration(from = 4, to = 5),
	]
)

abstract class SeerDatabase : RoomDatabase() {

	abstract fun getLibraryDao(): LibraryDao
	abstract fun getReaderDao(): ProgressDao

	companion object {

		private val migration3to4 = Migration(3, 4) { database ->
			database.execSQL("ALTER TABLE reader_table RENAME COLUMN book_id TO library_item_id")
		}

		@Volatile
		private var INSTANCE: SeerDatabase? = null

		fun getInstance(context: Context): SeerDatabase {
			/*
			if the INSTANCE is not null, then return it,
			if it is, then create the database and save
			in instance variable then return it.
			*/
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					SeerDatabase::class.java,
					Constants.DATABASE_NAME,
				).addMigrations(migration3to4).build()
				INSTANCE = instance
				// return instance
				instance
			}
		}
	}
}
