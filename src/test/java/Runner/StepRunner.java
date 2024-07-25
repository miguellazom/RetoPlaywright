package Runner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import Step.StepOrangeHRM;

import static org.junit.jupiter.api.Assertions.assertEquals;
@UsePlaywright
public class StepRunner {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    static Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void test1(Page page) {
        Page StepOrangeHRM = browser.newPage();
        StepOrangeHRM searchPage = new StepOrangeHRM(page);
        searchPage.navigate();
        searchPage.login("Admin","admin123");
        searchPage.addUsers("Juan","Luis","Garcia");
        searchPage.addDetails("333","123456","2025-12-30");
        searchPage.validateEmployeeList();
    }

}
