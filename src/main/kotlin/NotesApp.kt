import java.util.*

class NotesApp(private val scanner: Scanner) {
    private val archives = mutableListOf<Archive>()

    fun run() {
        while (true) {
            printMainMenu()
            val choice = readIntInput(scanner)

            when (choice) {
                1 -> showArchivesMenu()
                2 -> {
                    println("Выход из приложения.")
                    break
                }
                else -> println("Некорректный выбор. Пожалуйста, выберите один из пунктов меню.")
            }
        }
    }

    private fun printMainMenu() {
        println("Главное меню:")
        println("1. Показать архивы")
        println("2. Выход")
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
        while (true) {
            println("Архивы:")
            for ((index, archive) in archives.withIndex()) {
                println("$index. ${archive.name}")
            }

            println("${archives.size}. Создать новый архив")
            println("${archives.size + 1}. Вернуться в главное меню")
            print("Введите номер архива для просмотра заметок. ")
            print("Выберите вариант:")
            val archiveChoice = readIntInput(scanner)

            when {
                archiveChoice in 0 until archives.size -> {
                    val selectedArchive = archives[archiveChoice]
                    showNotesMenu(selectedArchive)
                }
                archiveChoice == archives.size -> createArchive()
                archiveChoice == archives.size + 1 -> return
                else -> println("Некорректный выбор. Пожалуйста, выберите правильный вариант.")
            }
        }
    }

    private fun createArchive() {
        print("Введите название архива: ")
        val archiveName = scanner.nextLine()
        archives.add(Archive(archiveName))
        println("Архив '$archiveName' создан.")
        println("1. Вернуться к списку архивов")
        print("Выберите вариант: ")
        val choice = readIntInput(scanner)
        when (choice) {
            1 -> return
            else -> println("Некорректный выбор. Пожалуйста, выберите правильный вариант.")
        }
    }

    private fun showNotesMenu(archive: Archive) {
        while (true) {
            println("Заметки в архиве '${archive.name}':")
            for ((index, note) in archive.notes.withIndex()) {
                println("$index. ${note.text}")
            }

            println("${archive.notes.size}. Создать новую заметку")
            println("${archive.notes.size + 1}. Вернуться в меню архива")
            print("Введите номер заметки для просмотра деталей. ")
            print("Выберите вариант: ")
            val noteChoice = readIntInput(scanner)

            when {
                noteChoice in 0 until archive.notes.size -> {
                    val selectedNote = archive.notes[noteChoice]
                    showNoteDetails(selectedNote, archive)
                }
                noteChoice == archive.notes.size -> createNote(archive)
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
            print("Выберите вариант:")
            val choice = readIntInput(scanner)
            when (choice) {
                1 -> return
                else -> println("Некорректный выбор. Пожалуйста, выберите правильный вариант.")
            }
        }
    }

    private fun createNote(archive: Archive) {
        print("Введите название новой заметки или оставьте пустым: ")
        val noteName = scanner.nextLine()
        print("Введите текст новой заметки: ")
        val noteText = scanner.nextLine()

        val displayText = if (noteName.isNotBlank()) {
            noteName
        } else {
            noteText.take(15)
        }

        archive.notes.add(Note(displayText, noteText))
        println("Заметка успешно добавлена.")
        println("1. Вернуться к списку заметок")
        print("Выберите вариант: ")
        val choice = readIntInput(scanner)
        when (choice) {
            1 -> return
            else -> println("Некорректный выбор. Пожалуйста, выберите правильный вариант.")
        }
    }
}