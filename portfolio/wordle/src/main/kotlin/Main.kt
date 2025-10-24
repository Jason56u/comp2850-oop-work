fun main() {
    // Read the words.txt and return a wordlist
    val wordList = readWordList("data/words.txt")
    val targetWord = pickRandomWord(wordList).lowercase()
    // Start to play
    println("Guess the 5-letter word(10 attempts are allowed)")
    println()

    var correctGuess = false

    // Loop for the player's attempts(up to 10 times)
    for (attempt in 1..MAX_ATTEMPTS) {
        val guess = obtainGuess(attempt)

        if (guess == targetWord) {
            println("Good! You guessed the correct ${targetWord.uppercase()}")
            correctGuess = true
            break
        }
        val matches = evaluateGuess(guess, targetWord)
        // Display the guess result
        displayGuess(guess, matches)
        println()
    }
    // Give the correct word if the player didn't guess the correct answer in 10 times
    if (!correctGuess) {
        println("Unfortunately,you have attempted for 10 times.")
        println("The correct word is: ${targetWord.uppercase()}!")
    }
}