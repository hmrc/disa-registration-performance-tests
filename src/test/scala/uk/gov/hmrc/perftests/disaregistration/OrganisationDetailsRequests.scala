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

object OrganisationDetailsRequests extends ServicesConfiguration {

  val getRegisteredToManageISAsPage: HttpRequestBuilder =
    http("Get Registered to Manage ISAsPage")
      .get(s"$disaBaseUrl$disaRoute/registered-isa-manager": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postYesFromIsOrganisationRegisteredToManageISAsPage: HttpRequestBuilder =
    http("Post Yes From Registered To Manage ISAs Page")
      .post(s"$disaBaseUrl$disaRoute/registered-isa-manager": String)
      .formParam("value", "yes")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))
      .check(
        header("location").is(s"$disaRoute/z-reference-number").saveAs("ZRefPage")
      )

  val getZReferencePage: HttpRequestBuilder =
    http("Get Z reference Page")
      .get(s"$disaBaseUrl$disaRoute/z-reference-number": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postZReferencePage: HttpRequestBuilder =
    http("Post Z ref number from 'Z reference' Page")
      .post(s"$disaBaseUrl$disaRoute/z-reference-number": String)
      .formParam("value", "Z1234")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getTradingUsingDifferentNamePage: HttpRequestBuilder =
    http("Get Trading using different mame Page")
      .get(s"$disaBaseUrl$disaRoute/trading-using-different-name": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postTradingUsingDifferentNamePage: HttpRequestBuilder =
    http("Post Yes from Trading using different mame Page")
      .post(s"$disaBaseUrl$disaRoute/trading-using-different-name": String)
      .formParam("value", "yes")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getTradingNamePage: HttpRequestBuilder =
    http("Get Trading using different mame Page")
      .get(s"$disaBaseUrl$disaRoute/trading-name": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postTradingNamePage: HttpRequestBuilder =
    http("Post trading name from Trading mame Page")
      .post(s"$disaBaseUrl$disaRoute/trading-name": String)
      .formParam("value", "Trading Name")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getFirmReferenceNumberPage: HttpRequestBuilder =
    http("Get Firm reference number Page")
      .get(s"$disaBaseUrl$disaRoute/firm-reference-number": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postFirmReferenceNumberPage: HttpRequestBuilder =
    http("Post FRN from Firm reference number Page")
      .post(s"$disaBaseUrl$disaRoute/firm-reference-number": String)
      .formParam("value", "7777777")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getRegisteredAddressCorrespondencePage: HttpRequestBuilder =
    http("Get Registered Address Correspondence")
      .get(s"$disaBaseUrl$disaRoute/registered-address-correspondence": String)
      .check(status.is(200))

  val postRegisteredAddressCorrespondencePage: HttpRequestBuilder =
    http("Post No from Registered Address Correspondence Page")
      .post(s"$disaBaseUrl$disaRoute/registered-address-correspondence": String)
      .formParam("value", "no")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getAddAnotherAddressPage: HttpRequestBuilder =
    http("Get Add another address Page")
      .get(s"$disaBaseUrl$disaRoute/add-another-address": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postAddAnotherAddressPage: HttpRequestBuilder =
    http("Post FRN from Firm reference number Page")
      .post(s"$disaBaseUrl$disaRoute/add-another-address": String)
      .formParam("postcode", "ZZ22 2ZZ")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getChooseAddressPage: HttpRequestBuilder =
    http("Get Choose address Page")
      .get(s"$disaBaseUrl$disaRoute/choose-address": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postChooseAddressPage: HttpRequestBuilder =
    http("Post FRN from Firm reference number Page")
      .post(s"$disaBaseUrl$disaRoute/choose-address": String)
      .formParam("value", "0")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getConfirmCorrespondenceAddressPage: HttpRequestBuilder =
    http("Get Confirm correspondence address Page")
      .get(s"$disaBaseUrl$disaRoute/confirm-correspondence-address": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getEnterYourOrganisationAddressPage: HttpRequestBuilder =
    http("Get Enter Your Organisation Address Page")
      .get(s"$disaBaseUrl$disaRoute/enter-your-organisation-address": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postEnterYourOrganisationAddressPage: HttpRequestBuilder =
    http("Post Enter Your Organisation Address Page")
      .post(s"$disaBaseUrl$disaRoute/enter-your-organisation-address": String)
      .formParam("addressLine1", "10 Test Street")
      .formParam("townOrCity", "Test town")
      .formParam("townOrCity", "Test town")
      .formParam("postcode", "ZZ22 2ZZ")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val postConfirmCorrespondenceAddressPage: HttpRequestBuilder =
    http("Post Confirm correspondence address Page")
      .post(s"$disaBaseUrl$disaRoute/confirm-correspondence-address": String)
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getOrganisationTelephoneNumberPage: HttpRequestBuilder =
    http("Get Organisation telephone number Page")
      .get(s"$disaBaseUrl$disaRoute/organisation-telephone-number": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postOrganisationTelephoneNumberPage: HttpRequestBuilder =
    http("Post Confirm correspondence address Page")
      .post(s"$disaBaseUrl$disaRoute/organisation-telephone-number": String)
      .formParam("value", "09393939393")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getOrganisationCheckYourAnswersPage: HttpRequestBuilder =
    http("Get Organisation Check Your Answers Page")
      .get(s"$disaBaseUrl$disaRoute/organisation-details-check-your-answers": String)
      .check(status.is(200))

}
