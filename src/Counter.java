/**
 * Counter class that displays an adjustable timer in standard or military time
 * @author Rory Xu
 */

public class Counter{
	public static final int SECONDS_PER_HOUR = 3600;
	public static final int SECONDS_PER_MINUTE = 60;
	public static final int SECONDS_PER_DAY = 24 * 60 * 60;
	private int totalSeconds;

	/**
	 * Initializes counter at desired time
	 * @param hour Initialized hour
	 * @param minute Initialized minute
	 * @param second Initialized second
	 */
	public Counter(int hour, int minute, int second) {
		if (hour < 0 || minute < 0 || second < 0 || hour > 23 || minute > 59 || second > 59) {
			throw new IllegalArgumentException("Invalid time!");
		}
		totalSeconds = hour * 3600 + minute * 60 + second;
	}

	public int getTotalSeconds() {
		return totalSeconds;
	}

	/**
	 * Increments hour counter by one
	 */
	public void incrementHour() {
		addSeconds(SECONDS_PER_HOUR);
	}

	/**
	 * Decrements hour counter by one
	 */
	public void decrementHour() {
		addSeconds(-SECONDS_PER_HOUR);
	}

	/**
	 * Increments minute counter by one
	 */
	public void incrementMinute() {
		addSeconds(SECONDS_PER_MINUTE);
	}

	/**
	 * Decrements minute counter by one
	 */
	public void decrementMinute() {
		addSeconds(-SECONDS_PER_MINUTE);
	}

	/**
	 * Increments second counter by one
	 */
	public void incrementSecond() {
		addSeconds(1);
	}

	/**
	 * Decrements second counter by one
	 */
	public void decrementSecond() {
		addSeconds(-1);
	}

	/**
	 * Displays the counter in military time
	 * @return A string showing the counter in military time
	 */
	public String displayMilitary() {
		return display(totalSeconds / SECONDS_PER_HOUR);
	}

	/**
	 * Displays the counter in standard time
	 * @return A string showing the counter in standard time
	 */
	public String displayStandard() {
		return display((totalSeconds / SECONDS_PER_HOUR) % 12 == 0 ? 12 : (totalSeconds / SECONDS_PER_HOUR) % 12);
	}

	/**
	 * Adds an integer amount of seconds to the counter
	 * Compatible with both positive and negative numbers for increment/decrement respectively
	 * @param seconds Number of seconds to be added
	 */
	private void addSeconds(int seconds) {
		totalSeconds += seconds;
		if (totalSeconds < 0) totalSeconds += SECONDS_PER_DAY;
		else if (totalSeconds >= SECONDS_PER_DAY) totalSeconds -= SECONDS_PER_DAY;
	}

	/**
	 * Displays the counter in ##:##:## format
	 * @param hour The format of the hour counter depending on standard or military display
	 * @return A string showing the counter in ##:##:## format
	 */
	private String display(int hour) {
		return String.format("%02d:%02d:%02d", hour,
				totalSeconds % SECONDS_PER_HOUR / SECONDS_PER_MINUTE,
				totalSeconds % SECONDS_PER_MINUTE);
	}

	/**
	 * Compares two counters to see if they are the same counter
	 * @param obj A counter object
	 * @return True if they are the same, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Counter)) return false;
		return (totalSeconds == ((Counter) obj).getTotalSeconds());
	}
}
