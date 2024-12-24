

import java.util.*

class UtilMethod {
    companion object {
        fun generateShortUUID(): String {
            val uuid = UUID.randomUUID()
            val byteArray = ByteArray(16)
            val buffer = java.nio.ByteBuffer.wrap(byteArray)
            buffer.putLong(uuid.mostSignificantBits)
            buffer.putLong(uuid.leastSignificantBits)
            return Base64.getUrlEncoder().withoutPadding().encodeToString(byteArray)
        }
    }
}