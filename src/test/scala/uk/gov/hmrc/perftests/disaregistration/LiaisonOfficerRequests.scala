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

object LiaisonOfficerRequests extends ServicesConfiguration {

  val navigateToStartPage: HttpRequestBuilder =
    http("Navigate to Start Page")
      .get(s"$disaBaseUrl$disaRoute/start")
      .check(status.is(303))

  val navigateToLiaisonOfficerNamePage: HttpRequestBuilder =
    http("Navigate to Liaison Officer Name Page")
      .get(s"$disaBaseUrl$disaRoute/liaison-officer-name")
      .check(status.is(200))
      .check(
        css("form[action*='liaison-officer-name']", "action")
          .transform(_.split("id=")(1))
          .saveAs("formId")
      )
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLiaisonOfficerNameFromTheLiaisonOfficerNamePage: HttpRequestBuilder =
    http("Post Liaison Officer Name From 'The Liaison Officer Name' Page")
      .post(s"$disaBaseUrl$disaRoute/liaison-officer-name?id=#{formId}": String)
      .formParam("value", "liaison officer")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getLiaisonOfficerEmailPage: HttpRequestBuilder =
    http("Get Liaison Officer Email Page")
      .get(s"$disaBaseUrl$disaRoute/liaison-officer-email?id=#{formId}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postEmailAddressFromTheLiaisonOfficerEmailPage: HttpRequestBuilder =
    http("Post Email Address From 'The Liaison Officer Email' Page")
      .post(s"$disaBaseUrl$disaRoute/liaison-officer-email?id=#{formId}": String)
      .formParam("value", "liaisonofficer@gmail.com")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getLiaisonOfficerPhoneNumberPage: HttpRequestBuilder =
    http("Get Liaison Officer Phone Number Page")
      .get(s"$disaBaseUrl$disaRoute/liaison-officer-phone-number?id=#{formId}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postPhoneNumberFromTheLiaisonOfficerPhoneNumberPage: HttpRequestBuilder =
    http("Post Phone Number From 'The Liaison Officer Phone Number' Page")
      .post(s"$disaBaseUrl$disaRoute/liaison-officer-phone-number?id=#{formId}": String)
      .formParam("value", "07000000000")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getLiaisonOfficerCommunicationMethodPage: HttpRequestBuilder =
    http("Get Liaison Officer Communication Method Page")
      .get(s"$disaBaseUrl$disaRoute/liaison-officer-communication?id=#{formId}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postCommunicationMethodFromTheLiaisonOfficerCommunicationPage: HttpRequestBuilder =
    http("Post Communication Method From 'The Liaison Officer Communication' Page")
      .post(s"$disaBaseUrl$disaRoute/liaison-officer-communication?id=#{formId}": String)
      .formParam("value[0]", "byEmail")
      .formParam("value[1]", "byPhone")
      .formParam("value[2]", "byPost")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getAddedLiaisonOfficersCheckYourAnswersPage: HttpRequestBuilder =
    http("Get Added Liaison Officers Check Your Answers Page")
      .get(s"$disaBaseUrl$disaRoute/check-added-liaison-officer?id=#{formId}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
}
