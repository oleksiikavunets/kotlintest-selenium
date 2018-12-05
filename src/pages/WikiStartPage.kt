package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

class WikiStartPage(private val driver: WebDriver) {

    private val pageUrl = "https://www.wikipedia.org/"

    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(id = "searchInput")
    lateinit var searchInput: WebElement

    @FindBy(css = "[type=\"submit\"]")
    lateinit var submitBtn: WebElement

    fun open() = driver.get(pageUrl)

    fun verifyUrl() {
        WebDriverWait(driver, 30).until { it.currentUrl == pageUrl }
    }
}