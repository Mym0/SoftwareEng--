package dev.memo.movieist.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Test
    public void testRegister() throws Exception {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .firstname("memo")
                .lastname("elgendy")
                .email("memo.elgendy@yahoo.com")
                .password("1234")
                .build();

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token("dummyToken")
                .build();

        when(authenticationService.register(ArgumentMatchers.any(RegisterRequest.class)))
                .thenReturn(authenticationResponse);

        ResponseEntity<AuthenticationResponse> expectedResponse = ResponseEntity.ok(authenticationResponse);

        ResponseEntity<AuthenticationResponse> actualResponse = authenticationController.register(registerRequest);

        assert actualResponse != null;
        assert actualResponse.getStatusCode() == HttpStatus.OK;
        assert actualResponse.getBody() != null;
        assert actualResponse.getBody().getToken().equals(expectedResponse.getBody().getToken());

        verify(authenticationService, times(1)).register(ArgumentMatchers.any(RegisterRequest.class));
    }

    @Test
    public void testAuthenticate() throws Exception {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("memo.elgendy@yahoo.com")
                .password("1234")
                .build();

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token("dummyToken")
                .build();

        when(authenticationService.authenticate(ArgumentMatchers.any(AuthenticationRequest.class)))
                .thenReturn(authenticationResponse);

        ResponseEntity<AuthenticationResponse> expectedResponse = ResponseEntity.ok(authenticationResponse);

        ResponseEntity<AuthenticationResponse> actualResponse = authenticationController
                .authenticate(authenticationRequest);

        assert actualResponse != null;
        assert actualResponse.getStatusCode() == HttpStatus.OK;
        assert actualResponse.getBody() != null;
        assert actualResponse.getBody().getToken().equals(expectedResponse.getBody().getToken());

        verify(authenticationService, times(1)).authenticate(ArgumentMatchers.any(AuthenticationRequest.class));
    }

}