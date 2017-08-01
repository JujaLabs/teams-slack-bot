package ua.com.juja.microservices.teams.slackbot.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.juja.microservices.teams.slackbot.model.Team;
import ua.com.juja.microservices.teams.slackbot.model.TeamRequest;
import ua.com.juja.microservices.teams.slackbot.repository.TeamRepository;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Ivan Shapovalov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceTest {

    @Rule
    final public ExpectedException expectedException = ExpectedException.none();

    @MockBean
    private TeamRepository teamRepository;

    @Inject
    private TeamService teamService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void activateTeamIfTeamRequestNotNullExecutedCorrectly() {
        Set<String> members = new LinkedHashSet<>(Arrays.asList("uuid1", "uuid2", "uuid3", "uuid4"));
        TeamRequest teamRequest = new TeamRequest(members);
        Team expected = new Team(members);
        given(teamRepository.activateTeam(teamRequest)).willReturn(expected);

        Team actual = teamService.activateTeam(teamRequest);

        assertEquals(expected, actual);
        verify(teamRepository).activateTeam(teamRequest);
        verifyNoMoreInteractions(teamRepository);
    }

    @Test
    public void activateTeamIfTeamRequestIsNullThrowsException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Team request must not be null!");

        teamService.activateTeam(null);

        verifyNoMoreInteractions(teamRepository);
    }

}