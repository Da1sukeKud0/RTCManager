package rtcManager;

import java.util.Optional;

/**
 *
 */
public class RTC {
	private final int source;
	private final int destination;
	private final int period;
	private int initialPhase;

	/**
	 * @param source
	 * @param destination
	 * @param period
	 */
	public RTC(int source, int destination, int period) {
		this.source = source;
		this.destination = destination;
		this.period = period;
	}

	public void setSchedule(int initialPhase) {
		this.initialPhase = initialPhase;
	}

	/**
	 * @return source MAC Address
	 */
	public int getSource() {
		return this.source;
	}

	/**
	 * @return destination MAC Address
	 */
	public int getDestination() {
		return this.destination;
	}

	/**
	 * @return communication period
	 */
	public int getPeriod() {
		return this.period;
	}

	/**
	 * @return initial_phase
	 */
	public Optional<Integer> getInitial_phase() {
		return Optional.ofNullable(this.initialPhase);
	}
}
