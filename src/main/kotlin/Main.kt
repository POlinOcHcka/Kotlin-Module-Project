import java.util.Scanner

class Note(val title: String, val content: String)

class Archive(val name: String) {
    val notes = mutableListOf<Note>()
}

class Menu(private val options: List<Pair<String, () -> Unit>>) {
    fun display() {
        println("Выберите опцию:")
        options.forEachIndexed { index, option ->
            println("${index + 1}. ${option.first}")
        }
        println("0. Выход")
    }

    fun selectOption(scanner: Scanner): Int {
        print("Введите номер опции: ")
        return scanner.nextInt()
    }

    fun executeOption(index: Int) {
        if (index >= 1 && index <= options.size) {
            options[index - 1].second.invoke()
        } else if (index == 0) {
            println("До свидания!")
            System.exit(0)
        } else {
            println("Некорректный выбор.")
        }
    }
}

class NotesApp {
    private val archives = mutableListOf<Archive>()
    private val scanner = Scanner(System.`in`)
    private val menu = Menu(
        listOf(
            "Создать архив" to ::createArchive,
            "Просмотреть архивы" to ::viewArchives,
            "Выход" to ::exitApp
        )
    )

    private fun createArchive() {
        print("Введите название архива: ")
        val archiveName = scanner.next()
        archives.add(Archive(archiveName))
        println("Архив '$archiveName' создан.")
    }

    private fun viewArchives() {
        println("Доступные архивы:")
        archives.forEachIndexed { index, archive ->
            println("${index + 1}. ${archive.name}")
        }
        println("0. Назад")
        val choice = scanner.nextInt()
        if (choice == 0) {
            start()
        } else if (choice in 1..archives.size) {
            val selectedArchive = archives[choice - 1]
            viewNotes(selectedArchive)
        } else {
            println("Некорректный выбор.")
            viewArchives()
        }
    }

    private fun viewNotes(archive: Archive) {
        println("Заметки в архиве '${archive.name}':")
        if (archive.notes.isEmpty()) {
            println("Нет заметок.")
        } else {
            archive.notes.forEachIndexed { index, note ->
                println("${index + 1}. ${note.title}")
            }
            println("0. Назад")
            val choice = scanner.nextInt()
            if (choice == 0) {
                viewArchives()
            } else if (choice in 1..archive.notes.size) {
                val selectedNote = archive.notes[choice - 1]
                viewNoteContent(selectedNote)
            } else {
                println("Некорректный выбор.")
                viewNotes(archive)
            }
        }
    }

    private fun viewNoteContent(note: Note) {
        println("Содержимое заметки '${note.title}':")
        println(note.content)
        println("0. Назад")
        val choice = scanner.nextInt()
        if (choice == 0) {
            val archive = archives.find { archive -> archive.notes.contains(note) }
            if (archive != null) {
                viewNotes(archive)
            } else {
                viewArchives()
            }
        }
    }

    private fun exitApp() {
        println("До свидания!")
        System.exit(0)
    }

    fun start() {
        println("Добро пожаловать в приложение 'Заметки'!")
        while (true) {
            menu.display()
            val choice = menu.selectOption(scanner)
            menu.executeOption(choice)
        }
    }
}

fun main() {
    val app = NotesApp()
    app.start()
}