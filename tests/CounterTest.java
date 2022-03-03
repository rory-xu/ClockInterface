import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.management.InvalidApplicationException;

import static org.junit.jupiter.api.Assertions.*;

class CounterTest {

	@Test
	void illegal_arg_exception_if_hour_in_constructor_is_negative() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			Counter check = new Counter(-1, 00, 00);
		});

		assertEquals("Invalid counter!", thrown.getMessage());
	}

	@Test
	void illegal_arg_exception_if_minute_in_constructor_is_negative() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			Counter check = new Counter(00, -1, 00);
		});

		assertEquals("Invalid counter!", thrown.getMessage());
	}

	@Test
	void illegal_arg_exception_if_second_in_constructor_is_negative() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			Counter check = new Counter(00, 00, -1);
		});

		assertEquals("Invalid counter!", thrown.getMessage());
	}

	@Test
	void hours_increments_properly() {
		Counter check = new Counter(13, 30, 30);
		Counter testCounter = new Counter(12, 30, 30);
		testCounter.incrementHour();

		assertEquals(check, testCounter);
	}

	@Test
	void minutes_increments_properly() {
		Counter check = new Counter(4, 31, 30);
		Counter testCounter = new Counter(4, 30, 30);
		testCounter.incrementMinute();

		assertEquals(check.getTotalSeconds(), testCounter.getTotalSeconds());
	}

	@Test
	void seconds_increments_properly() {
		Counter check = new Counter(4, 30, 31);
		Counter testCounter = new Counter(4, 30, 30);
		testCounter.incrementSecond();

		assertEquals(check, testCounter);
	}

	@Test
	void hours_decrements_properly() {
		Counter check = new Counter(4, 30, 30);
		Counter testCounter = new Counter(5, 30, 30);

		testCounter.decrementHour();

		assertEquals(check, testCounter);
	}

	@Test
	void minutes_decrements_properly() {
		Counter check = new Counter(4, 30, 30);
		Counter testCounter = new Counter(4, 31, 30);
		testCounter.decrementMinute();

		assertEquals(check.getTotalSeconds(), testCounter.getTotalSeconds());
	}

	@Test
	void seconds_decrements_properly() {
		Counter check = new Counter(4, 30, 30);
		Counter testCounter = new Counter(4, 30, 31);
		testCounter.decrementSecond();

		assertEquals(check, testCounter);
	}

	@Test
	void hours_increments_properly_at_23_hours() {
		Counter check = new Counter(0, 30, 30);
		Counter testCounter = new Counter(23, 30, 30);
		testCounter.incrementHour();

		assertEquals(check, testCounter);
	}

	@Test
	void minutes_increments_properly_at_59_minutes() {
		Counter check = new Counter(5, 00, 30);
		Counter testCounter = new Counter(4, 59, 30);
		testCounter.incrementMinute();

		assertEquals(check.getTotalSeconds(), testCounter.getTotalSeconds());
	}

	@Test
	void seconds_increments_properly_at_59_seconds() {
		Counter check = new Counter(4, 31, 00);
		Counter testCounter = new Counter(4, 30, 59);
		testCounter.incrementSecond();

		assertEquals(check, testCounter);
	}

	@Test
	void hours_decrements_properly_at_00_hours() {
		Counter check = new Counter(23, 30, 30);
		Counter testCounter = new Counter(0, 30, 30);

		testCounter.decrementHour();
		assertEquals(check, testCounter);
	}

	@Test
	void minutes_decrements_properly_at_00_minutes() {
		Counter check = new Counter(4, 59, 30);
		Counter testCounter = new Counter(5, 00, 30);
		testCounter.decrementMinute();

		assertEquals(check.getTotalSeconds(), testCounter.getTotalSeconds());
	}

	@Test
	void seconds_decrements_properly_at_00_seconds() {
		Counter check = new Counter(4, 30, 59);
		Counter testCounter = new Counter(4, 31, 00);
		testCounter.decrementSecond();

		assertEquals(check, testCounter);
	}

	@Test
	void carry_over_chains_properly_at_plus_one_second_23_59_59() {
		Counter check = new Counter(00, 00, 00);
		Counter testCounter = new Counter(23, 59, 59);
		testCounter.incrementSecond();

		assertEquals(check, testCounter);
	}

	@Test
	void carry_over_chains_properly_at_minus_one_second_00_00_00() {
		Counter check = new Counter(23, 59, 59);
		Counter testCounter = new Counter(00, 00, 00);
		testCounter.decrementSecond();

		assertEquals(check, testCounter);
	}

	@Test
	void displays_military_time_properly() {
		Counter testCounter = new Counter(4, 30, 30);
		testCounter.displayMilitary();

		assertEquals("04:30:30", testCounter.displayMilitary());
	}

	@Test
	void displays_standard_time_properly() {
		Counter testCounter = new Counter(4, 30, 30);
		testCounter.displayStandard();

		assertEquals("04:30:30", testCounter.displayStandard());
	}

	@Test
	void displays_military_time_properly_after_12_hours() {
		Counter testCounter = new Counter(13, 30, 30);
		testCounter.displayMilitary();

		assertEquals("13:30:30", testCounter.displayMilitary());
	}

	@Test
	void displays_standard_time_properly_after_12_hours() {
		Counter testCounter = new Counter(13, 30, 30);
		testCounter.displayStandard();

		assertEquals("01:30:30", testCounter.displayStandard());
	}

	@Test
	void displays_12_at_00_hours_standard() {
		Counter testCounter = new Counter(00, 30, 30);
		testCounter.displayStandard();

		assertEquals("12:30:30", testCounter.displayStandard());
	}
}
