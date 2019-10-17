package kz.edu.nu.cs.se.security;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AutoApplySession;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Custom implementation of {@link HttpAuthenticationMechanism} to provide authentication with Auth0.
 */
@ApplicationScoped
@AutoApplySession
public class Auth0AuthenticationMechanism implements HttpAuthenticationMechanism {

    private final AuthenticationController authenticationController;
    private final Auth0JwtIdentityStore auth0JwtIdentityStore;

    @Inject
    Auth0AuthenticationMechanism(AuthenticationController authenticationController, Auth0JwtIdentityStore auth0JwtIdentityStore) {
        this.authenticationController = authenticationController;
        this.auth0JwtIdentityStore = auth0JwtIdentityStore;
    }

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse,
                                                HttpMessageContext httpMessageContext) {

        if (isCallbackRequest(httpServletRequest)) {

            try {
                Tokens tokens = authenticationController.handle(httpServletRequest);
                Auth0JwtCredential auth0JwtCredential = new Auth0JwtCredential(tokens.getIdToken());
                CredentialValidationResult result = auth0JwtIdentityStore.validate(auth0JwtCredential);
                return httpMessageContext.notifyContainerAboutLogin(result);
            } catch (IdentityVerificationException e) {
                return httpMessageContext.responseUnauthorized();
            }
        }

        return httpMessageContext.doNothing();
    }

    private boolean isCallbackRequest(HttpServletRequest request) {
        return request.getRequestURI().equals("/callback") && request.getParameter("code") != null;
    }

}
