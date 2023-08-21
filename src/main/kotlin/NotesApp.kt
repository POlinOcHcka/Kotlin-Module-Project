import java.util.*

class NotesApp(private val scanner: Scanner) {
    private val archives = mutableListOf<Archive>()

    fun run() {
        while (true) {
            printMainMenu()
            val choice = readIntInput()

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

    private fun readIntInput(): Int {
        while (true) {
            val rawInput = scanner.nextLine()
            if (rawInput.matches(Regex("\\d+"))) {
                return rawInput.toInt()
            } else {
                println("Некорректный ввод. Пожалуйста, введите число.")
            }
        }
    }

    private fun showArchivesMenu() {
        println("Архивы:")
        for ((index, archive) in archives.withIndex()) {
            println("$index. ${archive.name}")
        }

        print("Введите номер архива для просмотра заметок (или любую другую клавишу для возврата): ")
        val archiveChoice = readIntInput()
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

            print("Введите номер заметки для просмотра (или любую другую клавишу для возврата): ")
            val noteChoice = readIntInput()
            if (noteChoice in 0 until archive.notes.size) {
                val selectedNote = archive.notes[noteChoice]
                println("Детали заметки:")
                println(selectedNote.text)
            } else {
                break
            }
        }
    }
}