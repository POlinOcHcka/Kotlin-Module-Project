import java.util.*

class NotesApp(private val scanner: Scanner) {
    private val archives = mutableListOf<Archive>()

    fun run() {
        while (true) {
            printMainMenu()
            val choice = readIntInput(scanner)

            when (choice) {
                1 -> showArchivesMenu()
                2 -> createArchive()
                3 -> {
                    println("Выход из приложения.")
                    break
                }
                else -> println("Некорректный выбор. Пожалуйста, выберите правильный вариант.")
            }
        }
    }

    private fun printMainMenu() {
        println("Главное меню:")
        println("1. Показать архивы")
        println("2. Создать архив")
        println("3. Выход")
        print("Выберите вариант: ")
    }

    private fun readIntInput(scanner: Scanner): Int {
        while (true) {
            val rawInput = this.scanner.nextLine()
            if (rawInput.matches(Regex("\\d+"))) {
                return rawInput.toInt()
            } else {
                println("Некорректный выбор. Пожалуйста, выберите правильный вариант.")
            }
        }
    }

    private fun showArchivesMenu() {
        println("Архивы:")
        for ((index, archive) in archives.withIndex()) {
            println("$index. ${archive.name}")
        }

        print("Введите номер архива для просмотра заметок: ")
        val archiveChoice = readIntInput(scanner)
        if (archiveChoice in 0 until archives.size) {
            val selectedArchive = archives[archiveChoice]
            showNotesMenu(selectedArchive)
        }
    }

    private fun createArchive() {
        print("Введите название архива: ")
        val archiveName = scanner.nextLine()
        archives.add(Archive(archiveName))
        println("Архив '$archiveName' создан.")
    }

    private fun showNotesMenu(archive: Archive) {
        while (true) {
            println("Заметки в архиве '${archive.name}':")
            for ((index, note) in archive.notes.withIndex()) {
                println("$index. ${note.text}")
            }

            println("${archive.notes.size}. Создать новую заметку")
            println("${archive.notes.size + 1}. Вернуться в меню архива")
            print("Выберите вариант: ")
            val noteChoice = readIntInput(scanner)

            when {
                noteChoice in 0 until archive.notes.size -> {
                    val selectedNote = archive.notes[noteChoice]
                    showNoteDetails(selectedNote, archive)
                }
                noteChoice == archive.notes.size -> createNewNote(archive)
                noteChoice == archive.notes.size + 1 -> return
                else -> println("Некорректный выбор. Пожалуйста, выберите правильный вариант.")
            }
        }
    }

    private fun showNoteDetails(note: Note, archive: Archive) {
        println("Детали заметки:")
        println(note.text)
        while (true) {
            println("1. Вернуться к списку заметок")
            print("Выберите вариант: ")
            val choice = readIntInput(scanner)
            when (choice) {
                1 -> return
                else -> println("Некорректный выбор. Пожалуйста, выберите правильный вариант.")
            }
        }
    }

    private fun createNewNote(archive: Archive) {
        print("Введите текст новой заметки: ")
        val noteText = scanner.nextLine()
        archive.notes.add(Note(noteText))
        println("Заметка успешно добавлена.")
    }
}