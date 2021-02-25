package tickets;

import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal{
	
	private final Set<User.Occupation> validOccupation;
	
	public TicketTypeSeasonalRestricted(
		String name
		, double price
		, Set<Zone> validZones
		, Season validSeason
		, Set<User.Occupation> validOccupation
	){
        super(name, price, validZones, validSeason);
        this.validOccupation = validOccupation;
    }
    
    
    /*
    Make sure that the user has the correct occupation.
     */
    @Override
    public boolean isValidFor(Trip trip, User user) {
        return super.isValidFor(trip, user)
            && validOccupation.contains(user.getOccupation());
    }

    public Set<User.Occupation> getValidOccupation() {
        return this.validOccupation;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.getValidOccupation(), ((TicketTypeSeasonalRestricted) o).getValidOccupation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(), this.getValidOccupation()
        );
    }
}