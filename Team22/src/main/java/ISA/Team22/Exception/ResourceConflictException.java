package ISA.Team22.Exception;

public class ResourceConflictException extends RuntimeException {

	private static final long serialVersionUID = 1791564636123821405L;

	private String resourceId;

    public ResourceConflictException(String resourceId) {
        this.resourceId = resourceId;
    }

    public ResourceConflictException(String message, String resourceId) {
        super(message);
        this.resourceId = resourceId;
    }

    public ResourceConflictException(String message, Throwable cause, String resourceId) {
        super(message, cause);
        this.resourceId = resourceId;
    }

    public ResourceConflictException(Throwable cause, String resourceId) {
        super(cause);
        this.resourceId = resourceId;
    }

    public ResourceConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String resourceId) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.resourceId = resourceId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

}
