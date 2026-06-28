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
import uk.gov.hmrc.perftests.disaregistration.constants.AppConfig.{disaBaseUrl, disaRoute}

object OrganisationEmailRequests extends ServicesConfiguration {

  val getOrganisationEmailAddressPage: HttpRequestBuilder =
    http("Get Organisation Email Address Page")
      .get(s"$disaBaseUrl$disaRoute/organisation-email-address": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postOrganisationEmailAddressPage: HttpRequestBuilder =
    http("Post email from Organisation Email Address Page")
      .post(s"$disaBaseUrl$disaRoute/organisation-email-address": String)
      .formParam("value", "codesent@sendcode.com")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))
      .check(
        header("location").is(s"$disaRoute/email-verification-code").saveAs("EmailVerificationCode")
      )

  val getEmailVerificationCodePage: HttpRequestBuilder =
    http("Get Email Verification Code Page")
      .get(s"$disaBaseUrl$disaRoute/email-verification-code": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postEmailVerificationCodePage: HttpRequestBuilder =
    http("Post Email Verification Code")
      .post(s"$disaBaseUrl$disaRoute/email-verification-code": String)
      .formParam("value", "ABCDEF")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getOrganisationEmailCheckYourAnswersPage: HttpRequestBuilder =
    http("Get Organisation Check Your Answers Page")
      .get(s"$disaBaseUrl$disaRoute/organisation-email-check-your-answers": String)
      .check(status.is(200))

}
