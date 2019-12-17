package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val positions = guessedInRightPosition(secret, guess)
    val letters = gussedInWrongPositions(secret, guess)
    return Evaluation(positions,letters)
}

fun guessedInRightPosition(secret: String, guess: String): Int {
    var positions = 0
    for (i in 0 until secret.length) {
        if (secret[i] == guess[i]) {
            positions++
        }
    }
    return positions
}

fun gussedInWrongPositions(secret: String, guess: String): Int {
    var letters = 0
    var new_secrect = ""
    var new_guess = ""
    for (i in 0 until secret.length) {
        if (secret[i] != guess[i]) {
            new_secrect += secret[i]
            new_guess += guess[i]
        }
    }

    val evalutedChars = mutableListOf<Char>()
    if (!new_secrect.isEmpty()) {
        for (i in 0 until guess.length) {
            val letter = guess[i]
            if (!evalutedChars.contains((letter))) {
                val howManyInSecrect = countHowMany(new_secrect, letter)
                val howManyInGuess = countHowMany(new_guess, letter)

                letters += if (howManyInSecrect == howManyInGuess || howManyInSecrect > howManyInGuess) howManyInGuess
                else howManyInSecrect

                evalutedChars.add(letter)
            }
        }
    }
    return letters
}

fun countHowMany(letters: String, letter: Char): Int {
    var howMany = 0
    for (i in 0 until letters.length) {
        if (letters[i] == letter) {
            howMany++
        }
    }
    return howMany
}