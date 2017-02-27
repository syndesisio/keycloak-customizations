package com.redhat.ipaas.keycloak.authenticators.autolinkidp;

import com.google.auto.service.AutoService;
import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.List;

@AutoService(AuthenticatorFactory.class)
public class AutoLinkIDPAuthenticatorFactory implements AuthenticatorFactory {

  public static final String PROVIDER_ID = "autolink-idp-login-authenticator";

  public String getId() {
    return PROVIDER_ID;
  }

  public Authenticator create(KeycloakSession keycloakSession) {
    return new AutoLinkIDPAuthenticator();
  }

  public void init(Config.Scope scope) {

  }

  public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

  }

  public void close() {

  }

  public String getDisplayType() {
    return "Auto Link IDP Login";
  }

  public String getReferenceCategory() {
    return "Auto Link IDP Login";
  }

  public boolean isConfigurable() {
    return false;
  }

  public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
    return new AuthenticationExecutionModel.Requirement[]{
        AuthenticationExecutionModel.Requirement.REQUIRED,
        AuthenticationExecutionModel.Requirement.ALTERNATIVE,
        AuthenticationExecutionModel.Requirement.DISABLED
    };
  }

  public boolean isUserSetupAllowed() {
    return false;
  }

  public String getHelpText() {
    return "Automatically link a successful IDP login with any existing (or new) KeyCloak.";
  }

  public List<ProviderConfigProperty> getConfigProperties() {
    return null;
  }
}
