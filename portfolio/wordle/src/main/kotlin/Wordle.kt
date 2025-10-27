// Implement the six required functions here
// Implement the six required functions here
import java.io.File

const val WORD_LENGTH = 5
const val LAST_WORD_INDEX = 4
const val MAX_ATTEMPTS = 10

// fun1
// Returns true if the given word is valid in Wordle(i.e., if it consists of exactly 5 letters)
// fun isValid(word: String): Boolean {
//     if (word.length != WORD_LENGTH) {
//         return false
//     }

//     for (char in word) {
//         if (!char.isLetter()) {
//             return false
//         }
//     }
//     return true
// }
// Return true if the word length is 5 and all letters
fun isValid(word: String): Boolean = word.length == WORD_LENGTH

// fun2
// Reads Wordle target words from the specified file, returning them as a list of strings.
fun readWordList(filename: String): MutableList<String> {
    val wordList = mutableListOf<String>()
    // Read all lines in words.txt file
    val file = File(filename)
    val lines = file.readLines()
    // Remove the leading and ending blanks and turn it into lowercase()
    for (line in lines) {
        val word = line.lowercase()
        if (isValid(word)) {
            wordList.add(word)
        }
    }
    return wordList
}

// fun3
// Chooses a random word from the given list, removes that word from the list, then returns it.
fun pickRandomWord(words: MutableList<String>): String {
    val selectedWord = words.random()
    words.remove(selectedWord)

    return selectedWord
}

// fun4
// Prints a prompt using the given attempt number
// (e.g. "Attempt 1: "), then reads a word from stdin. The word should be returned if valid,
// otherwise the user should be prompted to try again.
fun obtainGuess(attempt: Int): String {
    // Using while loop to remind and read the player's input
    while (true) {
        // Obtain the player input
        print("Attempt $attempt: ")
        val input = readln()
        val guess = input
        // Continue to next step(if the input is valid and give the instructions)
        if (isValid(guess)) {
            return guess.lowercase()
        }
        else {
            println("Please enter a 5-letter word!")
        }
    }
}

// fun5
// Compares a guess with the target word. Returns a list containing 5 integers,
// representing the result of comparison at each letter position. 0 indicates no match,
// 1 indicates a match.
fun evaluateGuess(guess: String, target: String): List<Int> {
    val matches = mutableListOf<Int>()
    // Traverse each letter position and determine whether it matches or not match
    for (i in 0..LAST_WORD_INDEX) {
        if (guess[i] == target[i]) {
            matches.add(1)
        }
        else {
            matches.add(0)
        }
    }
    return matches
}

// fun6
// Displays the letters of a guess that match target word, or a ‘?’ character where there is no match
fun displayGuess(guess: String, matches: List<Int>) {
    var result = ""
    // Determine the display characters based on matches
    for (i in 0..LAST_WORD_INDEX) {
        if (matches[i] == 1) {
            result = result + guess[i].uppercase()
        }
        else {
            result = result + '?'
        }
    }
    println(result)
}
