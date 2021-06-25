package ru.dariamikhailukova.mynotebook.repository
import androidx.lifecycle.LiveData
import ru.dariamikhailukova.mynotebook.data.NoteDao
import ru.dariamikhailukova.mynotebook.mvp.model.Note


//используется паттерн репозиторий
class NoteRepository(private val noteDao: NoteDao) {
    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }

}