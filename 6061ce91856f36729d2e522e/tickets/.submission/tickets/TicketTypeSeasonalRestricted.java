package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {

    private final Set<User.Occupation> validOcupations;

    public TicketTypeSeasonalRestricted(String name, double price, Set<Zone> validZones, Season validSeason, Set<User.Occupation> validOcupations) {
        super(name, price, validZones, validSeason);
        this.validOcupations = validOcupations;
    }

    @Override
    public boolean isValidFor(Trip trip, User user) {
        return super.isValidFor(trip, user)
            && this.getValidOcupations().contains(user.getOccupation());
    }

    public Set<User.Occupation> getValidOcupations() {
        return EnumSet.copyOf(this.validOcupations);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.getValidOcupations(), ((TicketTypeSeasonalRestricted) o).getValidOcupations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(), this.getValidOcupations()
        );
    }
}
