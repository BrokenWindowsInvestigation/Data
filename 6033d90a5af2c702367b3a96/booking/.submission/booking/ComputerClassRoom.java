package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ComputerClassRoom extends ClassRoom{
	
	private final int opensAt, closesAt;
	
	public ComputerClassRoom(String roomName, String roomDesc, boolean hasProjector, int opensAt, int closesAt){
			super(roomName, roomDesc, hasProjector);
			this.opensAt = opensAt;
			this.closesAt = closesAt;
		}
		
	 public Optional<Booking> book(Interval i, User u) {
	 	if (i.getStart().getHour() < this.opensAt || i.getEnd().getHour() > this.closesAt){
	 		return Optional.empty();
	 	}else{
	 		return super.book(i,u);
	 	}
	 }

}