package core;

public enum StatusCode {
    SUCCESS(200,"The request succeeded"),
    CREATED(201,"A new resource was created"),
    BAD_REQUEST(400,"Missing required field"),
    UNAUTHORIZED(401,"Invalid access token"),
    NOT_FOUND(404, "Cannot find requested resource"),
    NO_CONTENT(204, "No content to send in the response body");
    public final int code;
    public final String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
