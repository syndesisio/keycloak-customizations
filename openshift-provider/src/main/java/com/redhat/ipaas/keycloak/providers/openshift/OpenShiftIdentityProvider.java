/**
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.ipaas.keycloak.providers.openshift;

import com.fasterxml.jackson.databind.JsonNode;
import org.keycloak.broker.oidc.AbstractOAuth2IdentityProvider;
import org.keycloak.broker.oidc.mappers.AbstractJsonUserAttributeMapper;
import org.keycloak.broker.oidc.util.JsonSimpleHttp;
import org.keycloak.broker.provider.BrokeredIdentityContext;
import org.keycloak.broker.provider.IdentityBrokerException;
import org.keycloak.broker.provider.util.SimpleHttp;
import org.keycloak.models.KeycloakSession;

import java.net.URL;

public class OpenShiftIdentityProvider extends AbstractOAuth2IdentityProvider<OpenShiftIdentityProviderConfig> {

  public static final String DEFAULT_SCOPE = "user:info";

  public OpenShiftIdentityProvider(KeycloakSession session, OpenShiftIdentityProviderConfig config) {
    super(session, config);
    try {
      URL openshiftUrl = new URL(config.getOpenShiftUrl());
      config.setAuthorizationUrl(new URL(openshiftUrl, "/oauth/authorize").toString());
      config.setTokenUrl(new URL(openshiftUrl, "/oauth/token").toString());
      config.setUserInfoUrl(new URL(openshiftUrl, "/oapi/v1/users/~").toString());
    } catch (Exception e) {
      throw new IdentityBrokerException("Could not configure OpenShift identity provider.", e);
    }
  }

  @Override
  protected BrokeredIdentityContext doGetFederatedIdentity(String accessToken) {
    try {
      JsonNode profile = JsonSimpleHttp.asJson(SimpleHttp.doGet(getConfig().getUserInfoUrl()).header("Authorization", "Bearer " + accessToken));

      JsonNode metadata = profile.get("metadata");

      BrokeredIdentityContext user = new BrokeredIdentityContext(getJsonProperty(metadata, "uid"));

      String username = getJsonProperty(metadata, "name");
      user.setUsername(username);
      user.setName(getJsonProperty(profile, "fullName"));
      user.setIdpConfig(getConfig());
      user.setIdp(this);

      AbstractJsonUserAttributeMapper.storeUserProfileForMapper(user, profile, getConfig().getAlias());

      return user;
    } catch (Exception e) {
      throw new IdentityBrokerException("Could not obtain user profile from OpenShift.", e);
    }
  }

  @Override
  protected String getDefaultScopes() {
    return DEFAULT_SCOPE;
  }
}
