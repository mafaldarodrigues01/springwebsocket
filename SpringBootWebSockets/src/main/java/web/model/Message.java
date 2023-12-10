package web.model;

import java.time.Instant;

public record Message(UserWeb userWeb, String receiverId, String comment, Action action, Instant timestamp) {

}
