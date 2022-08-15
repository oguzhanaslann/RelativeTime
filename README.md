# Relative Time #
This is a simple kotlin based library for calculating Relative time by current time.

| Time title | Returned when                                           |
|------------|---------------------------------------------------------|
| YearAgo    | Given date difference from now is longer than a _year_  |
| MonthAgo   | Given date difference from now is longer than a _month_ |
| WeekAgo    | Given date difference from now is longer than a _week_  |
| DayAgo     | Given date difference from now is longer than a _day_   |
| HourAgo    | Given date difference from now is longer than a _hour_  |
| JustNow    | Given date is same as now                               |
| Unknown    | Given date cannot be processed                          |
### Be careful ###
for month differences, 11.x months still counted as a whole year 

## How to use ##
    RelativeTime.from( <java.util.Date> ) 
when you call the 'from' function with a Date object, it returns a Relative time object. After that, you can map the 
object by your own use case.

Every relative time object contains a corresponding difference value of corresponding time. For instance,
DayAgo(6) means there are 6 days between now and given Date object. Only JustNow and Unknown types do not contain 
such values. 

