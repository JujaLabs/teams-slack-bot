package ua.com.juja.microservices.teams.slackbot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class ApiError {
    /**
     * The status is duplicate http httpStatus internalErrorCode
     */
    private int httpStatus;
    /**
     * The code is internal error code for this exception
     */
    private String internalErrorCode;
    /**
     * The message for user
     */
    private String clientMessage;
    /**
     * The message  for developer
     */
    private String developerMessage;
    /**
     * The message  in exception
     */
    private String exceptionMessage;
    /**
     * List of detail error message
     */
    private List<String> detailErrors;
}