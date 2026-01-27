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

object DisaProductsRequests extends ServicesConfiguration {

  val navigateToISAProductsPage: HttpRequestBuilder =
    http("Navigate to ISA product Page")
      .get(s"$disaBaseUrl$disaRoute/isa-products")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postISAProducts: HttpRequestBuilder =
    http("Post ISA products")
      .post(s"$disaBaseUrl$disaRoute/isa-products": String)
      .formParam("value[3]", "stocksAndSharesJuniorIsas")
      .formParam("value[4]", "innovativeFinanceIsas")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))
      .check(
        header("location").is(s"$disaRoute/innovative-financial-products").saveAs("innovativeFinancialProductsPage")
      )

  val getInnovativeFinancialProductsPage: HttpRequestBuilder =
    http("Get Innovative Products Page")
      .get(s"$disaBaseUrl/#{innovativeFinancialProductsPage}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postInnovativeFinancialProducts: HttpRequestBuilder =
    http("Post ISA products")
      .post(s"$disaBaseUrl/#{innovativeFinancialProductsPage}": String)
      .formParam("value[1]", "peerToPeerLoansUsingAPlatformWith36HPermissions")
      .formParam("value[2]", "crowdfundedDebentures")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))
      .check(header("location").is(s"$disaRoute/peer-to-peer-loans").saveAs("peerToPeerLoans"))

  val getPeerToPeerLoansPage: HttpRequestBuilder =
    http("Get Peer to Peer Loans Page")
      .get(s"$disaBaseUrl/#{peerToPeerLoans}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postPeerToPeerLoansPlatform: HttpRequestBuilder =
    http("Post Peer To Peer Loans Platform")
      .post(s"$disaBaseUrl/#{peerToPeerLoans}": String)
      .formParam("value", "TestPlatform")
      .formParam("csrfToken", s"#{csrfToken}")
      .check(status.is(303))
      .check(header("location").is(s"$disaRoute/fca-platform-number").saveAs("fcaPlatformNumber"))

  val getFcaPlatformNumberPage: HttpRequestBuilder =
    http("Get FCA Platform Number Page")
      .get(s"$disaBaseUrl/#{fcaPlatformNumber}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postFcaPlatformNumber: HttpRequestBuilder =
    http("Post FCA Platform Number")
      .post(s"$disaBaseUrl/#{fcaPlatformNumber}": String)
      .formParam("value", "1234567")
      .formParam("csrfToken", s"#{csrfToken}")
      .check(status.is(303))
      .check(header("location").is(s"$disaRoute/isa-products-check-your-answers").saveAs("isaProductsCheckYourAnswers"))

  val getCheckYourAnswersPage: HttpRequestBuilder =
    http("Get check your answers Page")
      .get(s"$disaBaseUrl/#{isaProductsCheckYourAnswers}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
}
