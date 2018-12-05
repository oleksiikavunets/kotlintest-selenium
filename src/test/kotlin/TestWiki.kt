package test.kotlin

import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.specs.StringSpec
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit
import driver.WebDriverCloseable

class TestWiki : StringSpec() {
    private val driver: WebDriver = autoClose(WebDriverCloseable(ChromeDriver()))
    private val startUrl = "https://www.wikipedia.org"

    init {
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()

        "Page Wiki can should open" {
            driver.run {
                get(startUrl)
                pageSource.shouldContain("Wikipedia")
            }
        }
    }
}