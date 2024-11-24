
package gtmk.server.utils.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
@Service
class RedisService(private val stringRedisTemplate: StringRedisTemplate) {


    fun isKeyAbsentAndResetTimeout(key: String?, value: String, timeout: Long): Boolean {
        val errorKey = getStringData(key)
        if (errorKey != null && errorKey == value) {
            return true
        }
        saveStringData(key, value, timeout)
        return false
    }
    fun getStringData(key: String?): String? {
        return stringRedisTemplate.opsForValue()[key!!]
    }

    fun saveStringData(key: String?, value: String?, timeout: Long) {
        stringRedisTemplate.opsForValue()[key!!, value!!, timeout] = TimeUnit.MILLISECONDS
    }

    fun deleteByKey(key: String) {
        val keys = stringRedisTemplate.keys(key)
        stringRedisTemplate.delete(keys)
    }
}