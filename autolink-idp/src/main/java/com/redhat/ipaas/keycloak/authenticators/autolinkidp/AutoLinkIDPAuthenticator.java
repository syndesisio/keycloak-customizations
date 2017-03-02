package com.redhat.ipaas.keycloak.authenticators.autolinkidp;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.authenticators.broker.AbstractIdpAuthenticator;
import org.keycloak.authentication.authenticators.broker.util.SerializedBrokeredIdentityContext;
import org.keycloak.broker.provider.BrokeredIdentityContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class AutoLinkIDPAuthenticator extends AbstractIdpAuthenticator {

  protected void authenticateImpl(AuthenticationFlowContext context, SerializedBrokeredIdentityContext serializedCtx, BrokeredIdentityContext brokerContext) {
    UserModel authenticatedUser = context.getClientSession().getAuthenticatedUser();

    if(authenticatedUser != null) {
      context.setUser(authenticatedUser);
      context.success();
    } else {
      context.failure(AuthenticationFlowError.UNKNOWN_USER);
    }
  }

  protected void actionImpl(AuthenticationFlowContext context, SerializedBrokeredIdentityContext serializedCtx, BrokeredIdentityContext brokerContext) {

  }

  public boolean requiresUser() {
    return true;
  }

  public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
    return true;
  }
}
