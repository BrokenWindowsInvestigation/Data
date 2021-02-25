package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.Optional;

public class ComputerClassRoom extends ClassRoom {

    private final int openingHour;
    private final int closingHour;

    public ComputerClassRoom(
        String name,
        String description,
        boolean hasProjector,
        int openingHour,
        int closingHour
    ) {
        super(name, description, hasProjector);
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }


    public int getOpeningHour() {
        return this.openingHour;
    }

    public int getClosingHour() {
        return this.closingHour;
    }

    /*
    Only allows bookings that start on the hour. (Floors second and microsecond values).
    */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        LocalDateTime bookingStart = interval.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        LocalDateTime bookingEnd = interval.getEnd()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        if (
            bookingStart.toLocalDate() != bookingEnd.toLocalDate() ||
            bookingStart.getHour() < this.getOpeningHour() ||
            bookingEnd.getHour() > this.getClosingHour()
        ) {
            return Optional.empty();
        }

        return super.book(new Interval(bookingStart, bookingEnd), customer);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.getClosingHour(), ((ComputerClassRoom) o).getClosingHour())
            && Objects.equals(this.getOpeningHour(), ((ComputerClassRoom) o).getOpeningHour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getOpeningHour(), this.getClosingHour());
    }
}
