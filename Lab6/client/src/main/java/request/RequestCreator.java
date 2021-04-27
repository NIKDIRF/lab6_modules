package request;

import studygroup.Request;
import studygroup.RequestType;
import studygroup.StudyGroup;

public class RequestCreator {
    public Request createStudyGroupRequest(String userString) {
        return new Request(RequestType.STUDYGROUP_REQUEST, userString, null);
    }

    public Request createCommandRequest(String userString, StudyGroup studyGroup) {
        return new Request(RequestType.COMMAND_REQUEST, userString, studyGroup);
    }
}
