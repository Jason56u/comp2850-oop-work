import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

@Suppress("unused")
class WordleTest : StringSpec({
    // Test for 3 kinds of valid words
    "isValid should return true for 5-letter words" {
        isValid("March") shouldBe true
        isValid("apple") shouldBe true
        isValid("ABOUT") shouldBe true
    }
    // Test for 3 kinds of invalid words
    "isValid should return false for invalid words" {
        isValid("888888") shouldBe false
        isValid("app") shouldBe false
        isValid("perfect") shouldBe false
    }
    // Creating a temporary file to test and have the correct output
    "readWordList should read valid words from file" {
        val testFileName = "test_words.txt"
        File(testFileName).writeText("March\napple\nABOUT\nperfect\n888888")

        val result = readWordList(testFileName)
        result shouldBe listOf("march", "apple", "about")
        File(testFileName).delete()
    }
    // Setting up a original testing data to see if the outcome is correct
    "pickRandomWord should remove selected word from list" {
        val words = mutableListOf("march", "apple", "about")
        val original = words.size
        val selected = pickRandomWord(words)
        words.size shouldBe original - 1
        words.contains(selected) shouldBe false
    }
    // Test different situations for the matching
    "evaluateGuess should return correct match" {
        val target = "apple"
        evaluateGuess("apple", target) shouldBe listOf(1, 1, 1, 1, 1)
        evaluateGuess("essay", target) shouldBe listOf(0, 0, 0, 0, 0)
        evaluateGuess("apply", target) shouldBe listOf(1, 1, 1, 1, 0)
        evaluateGuess("acute", target) shouldBe listOf(1, 0, 0, 0, 1)
    }
})