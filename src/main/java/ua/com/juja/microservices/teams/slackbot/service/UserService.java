package ua.com.juja.microservices.teams.slackbot.service;

import ua.com.juja.microservices.teams.slackbot.model.User;

import java.util.List;

/**
 * @author Ivan Shapovalov
 */
public interface UserService {

    List<User> findUsersBySlackNames(List<String> slackNames);

    List<User> findUsersByUuids(List<String> uuids);
}
