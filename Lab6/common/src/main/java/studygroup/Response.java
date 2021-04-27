package studygroup;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = -5622461857486946378L;

    private String message;
    private boolean success;
    private boolean studyGroupRequired;

    public Response(String message, boolean b, boolean b2) {
        this.message = message;
        this.success = b;
        this.studyGroupRequired = b2;
    }

    public Response() {
        message = "";
        success = true;
        studyGroupRequired = false;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isStudyGroupRequired() {
        return studyGroupRequired;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean b) {
        this.success = b;
    }

    public void setStudyGroupRequired(boolean studyGroupRequired) {
        this.studyGroupRequired = studyGroupRequired;
    }
}
