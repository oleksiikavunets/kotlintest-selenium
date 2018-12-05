package test.kotlin

import io.kotlintest.specs.StringSpec
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import pages.WikiStartPage
import java.util.concurrent.TimeUnit
import io.kotlintest.Description
import io.kotlintest.Spec
import driver.WebDriverCloseable

class TestWikiOpen : StringSpec() {
    private val driver: WebDriver = autoClose(WebDriverCloseable(ChromeDriver()))
    private val page = WikiStartPage(driver)

    override fun afterSpec(description: Description, spec: Spec) {
        super.afterSpec(description, spec)
        driver.quit()
    }

    init {
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()

        "Start page can open" {
            page.run {
                open()
                verifyUrl()
            }
        }
    }
}