package paolo.rossi.currency_exchange.utils.extensions

import java.math.RoundingMode


fun Double.roundedTo(scale: Int) : Double {
    return this.toBigDecimal().setScale(scale, RoundingMode.UP).toDouble()
}