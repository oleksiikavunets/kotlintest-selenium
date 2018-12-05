package test.kotlin

import io.kotlintest.data.forall
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit
import io.kotlintest.matchers.string.shouldContain
import pages.WikiStartPage
import driver.WebDriverCloseable

class TestWikiSearch : StringSpec() {

    private val driver: WebDriver = autoClose(WebDriverCloseable(ChromeDriver()))
    private val page = WikiStartPage(driver)

    init {
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()

        "Search" {
            forall(
                row("Rabbit"),
                row("Turtle"),
                row("Guitar"),
                row("Horse")
            ) { search ->
                page.run {
                    open()
                    searchInput.sendKeys(search)
                    submitBtn.click()

                    driver.pageSource.shouldContain(search)
                }
            }
        }
    }
}