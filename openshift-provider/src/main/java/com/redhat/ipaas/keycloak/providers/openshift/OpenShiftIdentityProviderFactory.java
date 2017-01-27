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

import com.google.auto.service.AutoService;
import org.keycloak.broker.provider.AbstractIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.models.KeycloakSession;

@AutoService(org.keycloak.broker.provider.IdentityProviderFactory.class)
public class OpenShiftIdentityProviderFactory extends AbstractIdentityProviderFactory<OpenShiftIdentityProvider> {

  public static final String PROVIDER_ID = "openshift";

  public String getId() {
    return PROVIDER_ID;
  }

  public String getName() {
    return "OpenShift";
  }

  public OpenShiftIdentityProvider create(KeycloakSession session, IdentityProviderModel model) {
    return new OpenShiftIdentityProvider(session, new OpenShiftIdentityProviderConfig(model));
  }

}
