
package tickets;

import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {
	
	private final Set<User.Occupation> validOccupations;

    public TicketTypeSeasonalRestricted(String name, double price, Set<Zone> validZones, Season validSeason, Set<User.Occupation> validOccupations) {
        super(name, price, validZones, validSeason);
        this.validOccupations = validOccupations;
    }

    @Override
    public boolean isValidFor(Trip trip, User user) {
        return super.isValidFor(trip, user)
            && super.getValidSeason().isDateWithin(trip.getTripStartTime())
            && this.getValidOccupations().contains(user.getOccupation());
    }

    public Set<User.Occupation> getValidOccupations() {
        return this.validOccupations;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.getValidOccupations(), ((TicketTypeSeasonalRestricted) o).getValidOccupations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(), this.getValidOccupations()
        );
    }
}
