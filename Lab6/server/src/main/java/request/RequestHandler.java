package request;

import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import studygroup.Request;
import studygroup.Response;

public interface RequestHandler {
    Response handleRequest(Request request) throws CommandNotFoundException, CommandExecutionException;
}
