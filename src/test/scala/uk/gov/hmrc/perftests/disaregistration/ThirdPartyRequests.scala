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

object ThirdPartyRequests extends ServicesConfiguration {



  val getProductsManagedByThirdParty: HttpRequestBuilder =
    http("Navigate to products managed by third party")
      .get(s"$disaBaseUrl$disaRoute/products-managed-by-third-party")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postProductsManagedByThirdParty: HttpRequestBuilder =
    http("Post Yes From products managed by third party")
      .post(s"$disaBaseUrl$disaRoute/products-managed-by-third-party": String)
      .formParam("value", "yes")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))
      .check(
        header("location").is(s"$disaRoute/third-party-details").saveAs("ThirdPartyDetails")
      )

  val getThirdPartyDetails: HttpRequestBuilder =
    http("Get Third Party details Page")
      .get(s"$disaBaseUrl$disaRoute/third-party-details?id=#{formId}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))


  val postThirdPartyDetails: HttpRequestBuilder =
    http("Get Third Party details from Third party details Page")
      .post(s"$disaBaseUrl$disaRoute/third-party-details?id=#{formId}": String)
      .formParam("thirdPartyName", "Third Party")
      .formParam("frn", "7377373")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getIsaReturnsManagedByThirdParty: HttpRequestBuilder =
    http("Get Isa Returns Managed By Third Party Page")
      .get(s"$disaBaseUrl$disaRoute/isa-returns-managed-by-third-party?id=#{formId}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIsaReturnsManagedByThirdParty: HttpRequestBuilder =
    http("Post Isa Returns Managed By Third Party")
      .post(s"$disaBaseUrl$disaRoute/isa-returns-managed-by-third-party?id=#{formId}": String)
      .formParam("value", "yes")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getInvestorsFundsUsedByThirdParty: HttpRequestBuilder =
    http("Get Isa Returns Managed By Third Party Page")
      .get(s"$disaBaseUrl$disaRoute/investor-funds-used-by-third-party?id=#{formId}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postInvestorsFundsUsedByThirdParty: HttpRequestBuilder =
    http("Post Isa Returns Managed By Third Party")
      .post(s"$disaBaseUrl$disaRoute/investor-funds-used-by-third-party?id=#{formId}": String)
      .formParam("value", "yes")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getPercentageOfInvestorsFundsUsed: HttpRequestBuilder =
    http("Get Isa Returns Managed By Third Party Page")
      .get(s"$disaBaseUrl$disaRoute/percentage-of-investor-funds-used?id=#{formId}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postPercentageOfInvestorsFundsUsed: HttpRequestBuilder =
    http("Post Isa Returns Managed By Third Party")
      .post(s"$disaBaseUrl$disaRoute/percentage-of-investor-funds-used?id=#{formId}": String)
      .formParam("value", "20")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getAddedThirdPartyCheckYourAnswersPage: HttpRequestBuilder =
    http("Get Added Third Party Organisation Check Your Answers Page")
      .get(s"$disaBaseUrl$disaRoute/check-added-third-party?id=#{formId}": String)
      .check(status.is(200))

}
