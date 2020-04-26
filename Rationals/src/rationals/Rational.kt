package rationals

import java.lang.IllegalArgumentException
import java.math.BigInteger

class Rational(private var numerator: BigInteger, private var denominator: BigInteger):Comparable<Rational> {
    init {
        if (denominator == BigInteger.ZERO)
            throw IllegalArgumentException()
        else if (denominator < BigInteger.ZERO) {
            numerator = -numerator
            denominator = -denominator
        }
    }

    operator fun plus(second: Rational): Rational {
        val newNumerator = this.numerator * second.denominator + second.numerator * this.denominator
        val newDenominator = this.denominator * second.denominator
        return Rational(newNumerator, newDenominator)
    }

    operator fun minus(second: Rational): Rational {
        return when {
            this.numerator == BigInteger.ZERO -> Rational(-second.numerator, second.denominator)
            second.numerator == BigInteger.ZERO -> this
            this.denominator == second.denominator -> Rational(this.numerator - second.denominator, this.denominator)
            else -> {
                val newNumerator = this.numerator * second.denominator - second.numerator * this.denominator
                val newDenominator = this.denominator * second.denominator
                Rational(newNumerator, newDenominator)
            }
        }
    }

    operator fun times(second: Rational): Rational {
        return Rational(this.numerator * second.numerator, this.denominator * second.denominator)
    }

    operator fun div(second: Rational): Rational {
        return when {
            second.numerator == BigInteger.ZERO || this.denominator == BigInteger.ZERO -> throw IllegalArgumentException()
            second.denominator == BigInteger.ZERO || this.numerator == BigInteger.ZERO -> Rational(BigInteger.ZERO, BigInteger.ONE)
            else -> {
                Rational(this.numerator * second.denominator, this.denominator * second.numerator)
            }
        }
    }

    operator fun unaryMinus(): Rational {
        return Rational(-this.numerator, this.denominator)
    }

    override fun toString(): String {
        val simplifiedRational=this.simplify()
        return when {
            simplifiedRational.numerator == BigInteger.ZERO -> "0"
            simplifiedRational.denominator == BigInteger.ZERO -> throw IllegalArgumentException()
            simplifiedRational.denominator == BigInteger.ONE -> simplifiedRational.numerator.toString()
            simplifiedRational.denominator < BigInteger.ZERO -> "${-simplifiedRational.numerator}/${-simplifiedRational.denominator}"
            else -> "${simplifiedRational.numerator}/${simplifiedRational.denominator}"
        }
    }

    override operator fun compareTo(second: Rational): Int {
        return (this.numerator * second.denominator).compareTo(second.numerator * this.denominator)
    }

    override fun equals(second: Any?): Boolean {
        return when {
            this === second -> true
            this.numerator == (second as Rational).numerator && this.denominator == second.denominator -> true
            else -> {
                val newFirst = this.simplify()
                val newSecond = second.simplify()
                return newFirst.numerator == newSecond.numerator && newFirst.denominator == newSecond.denominator

            }
        }
    }

    fun hcf(n1: BigInteger, n2: BigInteger): BigInteger =
            if (n2 != 0.toBigInteger()) hcf(n2, n1 % n2) else n1

    fun simplify(): Rational {
        val hcf = hcf(this.numerator, this.denominator).abs()
        return Rational(this.numerator.div(hcf), this.denominator.div(hcf))
    }

}

infix fun Int.divBy(second: Int): Rational = Rational(this.toBigInteger(), second.toBigInteger())
infix fun Long.divBy(second: Long): Rational = Rational(this.toBigInteger(), second.toBigInteger())
infix fun BigInteger.divBy(second: BigInteger): Rational = Rational(this, second)

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1) == "2".toRational())
    println((-2 divBy 4) == "-1/2".toRational())
    println("117/1098".toRational() == "13/122".toRational())

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}

fun String.toRational(): Rational {
    return when {
        this.contains('/') -> Rational(this.substringBefore('/').trim().toBigInteger(), this.substringAfter('/').trim().toBigInteger())
        else -> Rational(this.trim().toBigInteger(), BigInteger.ONE)
    }
}
