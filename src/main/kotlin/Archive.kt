class Archive(val name: String) {
    val notes = mutableListOf<Note>()
}

class Note(val text: String, noteText: String)

