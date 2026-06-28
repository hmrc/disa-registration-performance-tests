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

object DeclarationRequests extends ServicesConfiguration {

  val getFinalCheckYourAnswersPage: HttpRequestBuilder =
    http("Get final check your answers page")
      .get(s"$disaBaseUrl$disaRoute/check-your-answers")
      .check(status.is(200))

  val postFinalCheckYourAnswersPage: HttpRequestBuilder =
    http("Post  final check your answers page")
      .post(s"$disaBaseUrl$disaRoute/check-your-answers": String)
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getDeclarationForIsaManagers: HttpRequestBuilder =
    http("Get Declaration For Isa Managers Page")
      .get(s"$disaBaseUrl$disaRoute/declaration-for-isa-managers": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postDeclarationForIsaManagers: HttpRequestBuilder =
    http("Post Declaration For Isa Managers")
      .post(s"$disaBaseUrl$disaRoute/declaration-for-isa-managers": String)
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getConfirmation: HttpRequestBuilder =
    http("Get Confirmation Page")
      .get(s"$disaBaseUrl$disaRoute/confirmation": String)
      .check(status.is(200))

}
