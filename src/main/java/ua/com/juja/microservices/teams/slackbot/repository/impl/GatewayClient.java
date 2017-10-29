package ua.com.juja.microservices.teams.slackbot.repository.impl;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.juja.microservices.teams.slackbot.model.teams.ActivateTeamRequest;
import ua.com.juja.microservices.teams.slackbot.model.teams.DeactivateTeamRequest;
import ua.com.juja.microservices.teams.slackbot.model.teams.Team;
import ua.com.juja.microservices.teams.slackbot.model.users.User;
import ua.com.juja.microservices.teams.slackbot.model.users.UserSlackNameRequest;
import ua.com.juja.microservices.teams.slackbot.model.users.UserUuidRequest;

import java.util.List;

/**
 * @author Ivan Shapovalov
 */
@FeignClient(name = "${gateway.name}")
public interface GatewayClient {
    @RequestMapping(method = RequestMethod.POST, value = "${teams.endpoint.activateTeam}", consumes =
            MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Team activateTeam(ActivateTeamRequest request);

    @RequestMapping(method = RequestMethod.PUT, value = "${teams.endpoint.deactivateTeam}", consumes =
            MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Team deactivateTeam(DeactivateTeamRequest deactivateTeamRequest);

    @RequestMapping(method = RequestMethod.GET, value = "${teams.endpoint.getTeam}/{uuid}", consumes =
            MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Team getTeam(@RequestParam(value = "uuid") String uuid);

    @RequestMapping(method = RequestMethod.POST, value = "${users.endpoint.usersBySlackNames}", consumes =
            MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<User> findUsersBySlackNames(UserSlackNameRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "${users.endpoint.usersByUuids}", consumes =
            MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<User> findUsersByUuids(UserUuidRequest request);
}