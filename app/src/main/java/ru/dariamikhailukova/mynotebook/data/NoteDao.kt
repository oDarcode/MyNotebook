package ru.dariamikhailukova.mynotebook.data
import androidx.lifecycle.LiveData
import androidx.room.*
import ru.dariamikhailukova.mynotebook.mvp.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //игнорировать если одинаковые
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun readAllData():LiveData<List<Note>>

}