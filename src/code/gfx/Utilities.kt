package code.gfx

/**
 * This utility class allows tying four values together.
 * It is used by the [Colour] class
 * Created by brian on 9/1/17.
 * @param a First value
 * @param b Second value
 * @param c Third value
 * @param d Fourth value
 */
data class Quadruple<A, B, C, D>(val a: A, val b: B, val c: C, val d: D)


/**
 * This converts all the singleColour/Short into Ints
 */
fun Quadruple<Short, Short, Short, Short>.toInt(): Quadruple<Int, Int, Int, Int> {
	return Quadruple(a.toInt(), b.toInt(), c.toInt(), d.toInt())
}



//I eventually want to use a typealias instead to remove overhead
typealias Coordinate = Pair<Int, Int>

/**
 * Used for storing a coordinate
 * @param X The X-coordinate
 * @param Y The Y-coordinate
 * @property X The X-coordinate
 * @property Y The Y-coordinate
 */
data class LegacyCoordinate(val X: Int, val Y: Int) {
	fun toCoordinate(): Coordinate = Coordinate(X, Y)
}


/**
 * This type is for clarifications that it's for storing a colour value
 */
typealias Colour12_bit = Short

/**
 * This serves for Inversions of sprites.
 * @author Brian Gaucher
 * @since 0.1.0.2.3
 */
enum class Inversion(val X: Boolean, val Y: Boolean) {
	NONE(false, false), X_AXIS(true, false), Y_AXIS(false, true), ROTATE_180(true, true)
	
}

/**
 * This converts a 12 bit colour into a 24 bit colour
 * @author Brian Gaucher
 * @since 0.1.0.2.6
 */
fun bit_12Tobit_24(bit_12: Int): Int {
	val bit_24 = (bit_12 and 0xf00) shl 0x4 or (bit_12 and 0x0f0) shl 0x4 or (bit_12 and 0x00f) shl 0x4
	return bit_24
}