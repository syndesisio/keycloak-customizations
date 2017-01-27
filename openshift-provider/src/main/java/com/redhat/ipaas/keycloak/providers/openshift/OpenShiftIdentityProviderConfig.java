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

import org.keycloak.broker.oidc.OAuth2IdentityProviderConfig;
import org.keycloak.models.IdentityProviderModel;

public class OpenShiftIdentityProviderConfig extends OAuth2IdentityProviderConfig {

  private static final String OPENSHIFT_URL = "openshiftUrl";

  public OpenShiftIdentityProviderConfig(IdentityProviderModel identityProviderModel) {
    super(identityProviderModel);
  }

  public String getOpenShiftUrl() {
    return getConfig().get(OPENSHIFT_URL);
  }

  public void setOpenShiftUrl(String openshiftUrl) {
    getConfig().put(OPENSHIFT_URL, openshiftUrl);
  }

}
