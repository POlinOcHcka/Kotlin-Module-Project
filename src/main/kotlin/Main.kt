import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val noteApp = NotesApp(scanner)
    noteApp.run()
}