
import java.util.*
import java.util.concurrent.TimeUnit


fun main(){
   val times = TimeZone.getAvailableIDs()
    for(time in times){
        println(time)

    }
}
fun displayTimeZone(tz: TimeZone) {
    val hours: Long = TimeUnit.MILLISECONDS.toHours(tz.rawOffset.toLong())
    var minutes: Long = (TimeUnit.MILLISECONDS.toMinutes(tz.rawOffset.toLong())
            - TimeUnit.HOURS.toMinutes(hours))
    // avoid -4:-30 issue
    minutes = Math.abs(minutes)
    var result: String? = ""
    result = if (hours > 0) {
        java.lang.String.format("(GMT+%d:%02d) %s", hours, minutes, tz.id)
    } else {
        java.lang.String.format("(GMT%d:%02d) %s", hours, minutes, tz.id)
    }
    println(result)
}
