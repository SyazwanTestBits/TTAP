import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

import internal.GlobalVariable

class TestRetryListener {
    // Max number of retries
    static final int MAX_RETRIES = 2

    @BeforeTestSuite
    def beforeTestSuite(TestSuiteContext testSuiteContext) {
        GlobalVariable.RETRY_COUNTER = 0
    }

    @BeforeTestCase
    def beforeTestCase(TestCaseContext testCaseContext) {
        if (GlobalVariable.RETRY_COUNTER < MAX_RETRIES) {
            // Reset the skip flag
            GlobalVariable.SKIP_REMAINING_TESTS = false
        } else {
            // Skip the test case if it has already been retried once
            testCaseContext.skipThisTestCase()
        }
    }

    @AfterTestCase
    def afterTestCase(TestCaseContext testCaseContext) {
        if (testCaseContext.testCaseStatus == 'FAILED' && GlobalVariable.RETRY_COUNTER < MAX_RETRIES) {
            // Increment the retry counter
            GlobalVariable.RETRY_COUNTER++
        } else {
            // Set the flag to skip the remaining tests if any test fails after retry
            GlobalVariable.SKIP_REMAINING_TESTS = true
        }
    }
}