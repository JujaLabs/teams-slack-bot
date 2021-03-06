package ua.com.juja.microservices.teams.slackbot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.juja.microservices.teams.slackbot.model.users.User;
import ua.com.juja.microservices.teams.slackbot.repository.UserRepository;
import ua.com.juja.microservices.teams.slackbot.service.UserService;
import ua.com.juja.microservices.teams.slackbot.util.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ivan Shapovalov
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findUsersBySlackIds(List<String> slackIds) {
        Utils.checkNull(slackIds, "SlackIds must not be null!");
        List<User> users = userRepository.findUsersBySlackIds(slackIds);
        log.info("Found '{}' users in User repository", users.size());
        return users;
    }

    @Override
    public List<User> findUsersByUuids(List<String> uuids) {
        Utils.checkNull(uuids, "Uuids must not be null!");
        List<User> users = userRepository.findUsersByUuids(uuids);
        log.info("Found '{}' users in User repository", users.size());
        return users;
    }

    @Override
    public String replaceUuidsBySlackIdsInExceptionMessage(String message) {
        Set<String> uuids = Utils.extractUuidsFromExceptionMessage(message);
        if (!uuids.isEmpty()) {
            List<User> users = findUsersByUuids(new ArrayList<>(uuids));
            Set<String> slackIds = new LinkedHashSet<>(users.stream()
                    .map(User::getSlackId)
                    .collect(Collectors.toList()));
            message = Utils.replaceUuidsBySlackIdsInExceptionMessage(message, slackIds);
        }
        return message;
    }
}
