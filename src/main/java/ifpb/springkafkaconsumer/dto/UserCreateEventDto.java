package ifpb.springkafkaconsumer.dto;

import java.time.Instant;

public record UserCreateEventDto (
        String eventId,
        String action,
        Long id,
        String email,
        Instant timestamp
){}
