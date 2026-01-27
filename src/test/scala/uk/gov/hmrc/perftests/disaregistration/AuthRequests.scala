/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.disaregistration

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.disaregistration.constants.AppConfig.{authWizardUrl, redirectionUrl}

object AuthRequests extends ServicesConfiguration {

  val navigateToAuthLoginStubPage: HttpRequestBuilder =
    http("Navigate to auth login stub page")
      .get(authWizardUrl)
      .check(status.is(200))

  val submitLogin: HttpRequestBuilder =
    http("Sign in as an ISA Manager")
      .post(authWizardUrl)
      .formParam("redirectionUrl", redirectionUrl)
      .formParam("credentialStrength", "strong")
      .formParam("authorityId", "")
      .formParam("confidenceLevel", "50")
      .formParam("affinityGroup", "Organisation")
      .formParam("enrolment[0].name", "HMRC-DISA-ORG")
      .formParam("enrolment[0].taxIdentifier[0].name", "ZRef")
      .formParam("enrolment[0].taxIdentifier[0].value", "#{ZReference}")
      .formParam("enrolment[0].state", "Activated")
      .check(status.is(303), status.not(404), status.not(500))

}
