# Welcome

Note:
- If you are using any of my code, please add a link to me/my website/ or **this** github repo. Thumbs up for the supporting devs 
# Playwright simple test

### Info
- A small simple test using the Playwright and JUnit library for searching the word "wortelsap" on wikipedia.

**Source: Test with Playwright and JUnit**
```java
   
   public class TestExample {
    // Shared between all tests in this class
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }
    @AfterAll
    static void closeBrowser(){
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage(){
        context=browser.newContext();
        page=context.newPage();
    }
    @AfterEach
    void closeContext(){
        context.close();
    }

    @Test
    void shouldSearchWiki(){
        page.navigate("https://wikipedia.org/");
        page.locator("input[name=\"search\"]").click();
        page.locator("input[name=\"search\"]").fill("wortelsap");
        page.locator("input[name=\"search\"]").press("Enter");
        assertEquals("https://nl.wikipedia.org/w/index.php?go=Go&search=wortelsap&title=Speciaal:Zoeken&ns0=1",
                page.url());
    }
}
```