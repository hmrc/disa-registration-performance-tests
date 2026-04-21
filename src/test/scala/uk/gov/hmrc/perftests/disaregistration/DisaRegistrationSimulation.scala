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

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.disaregistration.AuthRequests.{navigateToAuthLoginStubPage, submitLogin}
import uk.gov.hmrc.perftests.disaregistration.CertificateOfAuthorityRequests._
import uk.gov.hmrc.perftests.disaregistration.DisaProductsRequests._
import uk.gov.hmrc.perftests.disaregistration.LiaisonOfficerRequests._
import uk.gov.hmrc.perftests.disaregistration.SignatoryRequests.{getAddedSignatoryCheckYourAnswersPage, getSignatoryJobTitlePage, navigateToSignatoryNamePage, postJobTitleFromTheSignatoryJobTitlePage, postSignatoryNameFromSignatoryNamePage}

class DisaRegistrationSimulation extends PerformanceTestRunner {

  setup("ISA-Manager-Registration", "ISA Manager Registration - DISA Products Journey") withRequests (
    navigateToAuthLoginStubPage,
    submitLogin("isa-products"),
    navigateToISAProductsPage,
    postISAProducts,
    getInnovativeFinancialProductsPage,
    postInnovativeFinancialProducts,
    getPeerToPeerLoansPage,
    postPeerToPeerLoansPlatform,
    getFcaPlatformNumberPage,
    postFcaPlatformNumber,
    getCheckYourAnswersPage
  )

  setup(
    "ISA-Manager-Registration-Certificate-Of-Authority",
    "ISA Manager Registration - Certificate Of Authority Journey"
  ) withRequests (
    navigateToAuthLoginStubPage,
    submitLogin("eligibility-to-manage-isas"),
    navigateToEligibilityToManageISAsPage,
    navigateToCertificatesOfAuthorityPage,
    postYesFromTheCertificatesOfAuthorityPage,
    getFCAArticlesPage,
    postSelectionsFromTheFCAArticles,
    getCertificatesOfAuthorityCheckYourAnswersPage
  )

  setup(
    "ISA-Manager-Registration-Liaison-Officer",
    "ISA Manager Registration - Liaison Officer Journey"
  ) withRequests (
    navigateToAuthLoginStubPage,
    submitLogin("start"),
    navigateToStartPage,
    navigateToLiaisonOfficerNamePage,
    postLiaisonOfficerNameFromTheLiaisonOfficerNamePage,
    getLiaisonOfficerEmailPage,
    postEmailAddressFromTheLiaisonOfficerEmailPage,
    getLiaisonOfficerPhoneNumberPage,
    postPhoneNumberFromTheLiaisonOfficerPhoneNumberPage,
    getLiaisonOfficerCommunicationMethodPage,
    postCommunicationMethodFromTheLiaisonOfficerCommunicationPage,
    getAddedLiaisonOfficersCheckYourAnswersPage
  )

  setup(
    "ISA-Manager-Registration-Signatory",
    "ISA Manager Registration - Signatory Journey"
  ) withRequests (
    navigateToAuthLoginStubPage,
    submitLogin("start"),
    navigateToStartPage,
    navigateToSignatoryNamePage,
    postSignatoryNameFromSignatoryNamePage,
    getSignatoryJobTitlePage,
    postJobTitleFromTheSignatoryJobTitlePage,
    getAddedSignatoryCheckYourAnswersPage
  )

  runSimulation()
}
