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

object CertificateOfAuthorityRequests extends ServicesConfiguration {

  val navigateToEligibilityToManageISAsPage: HttpRequestBuilder =
    http("Navigate to Eligibility To Manage ISAs Page")
      .get(s"$disaBaseUrl$disaRoute/eligibility-to-manage-isas")
      .check(status.is(200))

  val navigateToCertificatesOfAuthorityPage: HttpRequestBuilder =
    http("Navigate to Certificates Of Authority Page")
      .get(s"$disaBaseUrl$disaRoute/certificates-of-authority")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postYesFromTheCertificatesOfAuthorityPage: HttpRequestBuilder =
    http("Post Yes From The Certificates Of Authority Page")
      .post(s"$disaBaseUrl$disaRoute/certificates-of-authority": String)
      .formParam("value", "yes")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))
      .check(
        header("location").is(s"$disaRoute/fca-articles").saveAs("fcaArticlesPage")
      )

  val getFCAArticlesPage: HttpRequestBuilder =
    http("Get FCA Articles Page")
      .get(s"$disaBaseUrl/#{fcaArticlesPage}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postSelectionsFromTheFCAArticles: HttpRequestBuilder =
    http("Post selection from the FCA Articles Page")
      .post(s"$disaBaseUrl/#{fcaArticlesPage}": String)
      .formParam("value[5]", "article39G")
      .formParam("value[8]", "article51ZA")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))
      .check(
        header("location")
          .is(s"$disaRoute/certificates-of-authority-check-your-answers")
          .saveAs("certificatesOfAuthorityCheckYourAnswersPage")
      )

  val getCertificatesOfAuthorityCheckYourAnswersPage: HttpRequestBuilder =
    http("Get Certificates Of Authority Check Your Answers Page")
      .get(s"$disaBaseUrl/#{certificates-of-authority-check-your-answers}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
}
