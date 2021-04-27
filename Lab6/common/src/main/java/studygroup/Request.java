package studygroup;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = -4287447999382808577L;

    private RequestType type;
    private String userString;
    private StudyGroup studyGroup;

    public Request(RequestType type, String userString, StudyGroup studyGroup) {
        this.type = type;
        this.userString = userString;
        this.studyGroup = studyGroup;
    }

    public String getUserString() {
        return userString;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }


    public RequestType getType() {
        return type;
    }

    public void setUserString(String userString) {
        this.userString = userString;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }


    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + userString + '\'' +
                ", studygroup=" + studyGroup +
                '}';
    }
}
