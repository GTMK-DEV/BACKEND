package org.example.hmsspringboot.utils.redis

import org.springframework.stereotype.Component

@Component
class RedisCacheKey {

    fun getRobotErrorAlarmKey(mapId: Int, robotId: Int): String? {
        return "robotErrorAlarm::$mapId::$robotId"
    }
}