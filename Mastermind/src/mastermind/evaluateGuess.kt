package mastermind

import java.lang.Integer.min

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess( secret: String, guess: String): Evaluation {
    //count of right positions
    var right = 0
    //A-0,B-1,C-2,D-3,E-4,F-5
    //array for A-F with count for each occurrence (secret)
    val secretCharCount = IntArray(6)
    //array for A-F with count for each occurrence (guess)
    val guessCharCount = IntArray(6)
    //iterating over 4 letters
    for (i in 0..3){
        val s = secret[i]
        val g = guess[i]
        //increasing count in appropriate index of letter
        ++secretCharCount[s.toInt() - 65]
        ++guessCharCount[g.toInt() - 65]
        //if equal,increase right
        if (s == g)
            ++right
    }
    /*
    Here we have count and actual guess and secret
    noOfCommonLetters contains no.of letters that are common uniquely
    for ex: secret-ABCD,guess-EAAA,  noOfCommonLetters=1 (secret[0]-guess[1])
    here secretCharCount:A=1,guessCharCount:A=3 and min gives 1
     */
    val noOfCommonLetters = (secretCharCount.indices).sumBy { min(secretCharCount[it], guessCharCount[it]) }
    //wrong letters = no.of common letters - no.of right placed letters.
    return Evaluation(right, noOfCommonLetters - right)
}
