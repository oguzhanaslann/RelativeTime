import RelativeTimeUtils.A_MONTH_IN_DAYS
import RelativeTimeUtils.A_WEEK_IN_DAYS
import RelativeTimeUtils.A_YEAR_IN_DAYS
import RelativeTimeUtils.A_YEAR_IN_MONTHS
import java.util.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration

sealed class RelativeTime {
    data class YearAgo(val years: Long) : RelativeTime()
    data class MonthAgo(val months: Long) : RelativeTime()
    data class WeekAgo(val weeks: Long) : RelativeTime()
    data class DayAgo(val days: Long) : RelativeTime()
    data class HourAgo(val hours: Long) : RelativeTime()
    data class MinuteAgo(val minutes: Long) : RelativeTime()
    data class SecondAgo(val seconds: Long) : RelativeTime()
    object JustNow : RelativeTime()
    object Unknown : RelativeTime()

    fun isJustNow() = this is JustNow

    companion object {
        fun from(date: Date?) : RelativeTime {
            if (date == null) return Unknown

            val now = System.currentTimeMillis()
            val nowDuration = now.toDuration(DurationUnit.MILLISECONDS)
            val dateDuration = date.time.toDuration(DurationUnit.MILLISECONDS)
            val durationDifference = nowDuration - dateDuration
            return when {
                durationDifference.inWholeDays >= A_YEAR_IN_DAYS -> YearAgo(durationDifference.inWholeDays / A_YEAR_IN_DAYS)
                durationDifference.inWholeDays >= A_MONTH_IN_DAYS -> {
                    val months = durationDifference.inWholeDays / A_MONTH_IN_DAYS
                    if (months == A_YEAR_IN_MONTHS.toLong()) YearAgo(1) else MonthAgo(months)
                }
                durationDifference.inWholeDays >= A_WEEK_IN_DAYS -> WeekAgo(durationDifference.inWholeDays / A_WEEK_IN_DAYS)
                durationDifference.inWholeDays > 0 -> DayAgo(durationDifference.inWholeDays)
                durationDifference.inWholeHours > 0 -> HourAgo(durationDifference.inWholeHours)
                durationDifference.inWholeMinutes > 0 -> MinuteAgo(durationDifference.inWholeMinutes)
                durationDifference.inWholeSeconds > 0 -> SecondAgo(durationDifference.inWholeSeconds)
                else -> JustNow
            }
        }
    }
}