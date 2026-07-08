import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.*;
public class UiUxAutomationTest {
    static final String BASE_URL = "https://bstackdemo.com";
    static final Map<String, String> PAGES = new LinkedHashMap<>();
    static {
        PAGES.put("login", BASE_URL + "/signin");
        PAGES.put("home", BASE_URL + "/");
        PAGES.put("offers", BASE_URL + "/offers");
        PAGES.put("orders", BASE_URL + "/orders");
        PAGES.put("favourites", BASE_URL + "/favourites");
        PAGES.put("cart", BASE_URL + "/cart");
    }
    static final double MIN_CONTRAST_RATIO = 4.5;   // WCAG AA
    static final double MIN_FONT_SIZE_PX = 12.0;
    static final long MAX_LOAD_TIME_MS = 7000;
    static WebDriver driver;
    static int passCount = 0, failCount = 0;
    static int tcNumber = 1;
    SoftAssert softAssert;
    @BeforeSuite
    public void beforeSuite() {
        printHeader();
    }
    @BeforeClass
    public void setUp() throws Exception {
        startSession("Main");
        openPage(BASE_URL);
        login();
    }
    @BeforeMethod
    public void beforeMethod() {
        softAssert = new SoftAssert();
    }
    @Test(priority = 1)
    public void runUiUxChecksAcrossAllPages() {
        for (Map.Entry<String, String> entry : PAGES.entrySet()) {
            String pageName = entry.getKey();
            String url = entry.getValue();
            openPage(url);
            if (pageName.equals("cart")) {
                prepareCart();
            }
            runCheck(pageName, "Color contrast", () -> checkContrast());
            runCheck(pageName, "Font readability", () -> checkFontSize());
            runCheck(pageName, "Layout organization", () -> checkLayoutOverlap());
            runCheck(pageName, "Element visibility (no zoom needed)", () -> checkVisibility());
            runCheck(pageName, "Zoom in/out functionality", () -> checkZoom());
            runCheck(pageName, "Page response speed", () -> checkPageLoadSpeed());
        }
        softAssert.assertAll();
    }
    @AfterClass
    public void tearDown() {
        endSession();
    }
    @AfterSuite
    public void afterSuite() {
        printSummary();
    }
    static void startSession(String pageName) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    static void endSession() {
        if (driver != null) driver.quit();
    }
    static void openPage(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            driver.navigate().refresh();
            driver.get(url);
        }
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(d ->
                ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }
    /** TC_UI/UX_001-006: color clarity & contrast (real WCAG contrast-ratio math) */
    static String checkContrast() {
        String script =
            "function luminance(r,g,b){var a=[r,g,b].map(function(v){v/=255; return v<=0.03928?v/12.92:Math.pow((v+0.055)/1.055,2.4);});" +
            "  return a[0]*0.2126+a[1]*0.7152+a[2]*0.0722;}" +
            "function parseColor(c){var m=c.match(/\\d+(\\.\\d+)?/g); return m?m.map(Number):[255,255,255];}" +
            "function effectiveBg(el){while(el){var bg=getComputedStyle(el).backgroundColor;" +
            "  if(bg && bg!=='rgba(0, 0, 0, 0)' && bg!=='transparent') return bg; el=el.parentElement;} return 'rgb(255,255,255)';}" +
            "var el=document.body; var fg=parseColor(getComputedStyle(el).color); var bg=parseColor(effectiveBg(el));" +
            "var l1=luminance(fg[0],fg[1],fg[2])+0.05; var l2=luminance(bg[0],bg[1],bg[2])+0.05;" +
            "return l1>l2 ? l1/l2 : l2/l1;";
        double ratio = ((Number) js().executeScript(script)).doubleValue();
        boolean pass = ratio >= MIN_CONTRAST_RATIO;
        return result(pass, String.format("contrast ratio %.2f:1 (min %.1f:1)", ratio, MIN_CONTRAST_RATIO));
    }
    /** TC_UI/UX_007-012: font readability (computed font-size vs. minimum) */
    static String checkFontSize() {
        double size = ((Number) js().executeScript("return parseFloat(getComputedStyle(document.body).fontSize);")).doubleValue();
        boolean pass = size >= MIN_FONT_SIZE_PX;
        return result(pass, String.format("font-size %.1fpx (min %.1fpx)", size, MIN_FONT_SIZE_PX));
    }
    /** TC_UI/UX_013-018: layout organization (overlapping element detection) */
    static String checkLayoutOverlap() {
        String script =
            "var els=Array.from(document.querySelectorAll('button, a, img, input, .shelf-item'))" +
            "  .filter(function(e){\n" +
                    "    var r=e.getBoundingClientRect();\n" +
                    "    return r.width>20 && r.height>20;\n" +
                    "});" +
            "var overlaps=0;" +
            "for(var i=0;i<els.length;i++){for(var j=i+1;j<els.length;j++){" +
            "  var a=els[i].getBoundingClientRect(); var b=els[j].getBoundingClientRect();" +
            "  var xOverlap=Math.max(0, Math.min(a.right,b.right)-Math.max(a.left,b.left));" +
            "  var yOverlap=Math.max(0, Math.min(a.bottom,b.bottom)-Math.max(a.top,b.top));" +
            "  if(xOverlap>30 && yOverlap>30) overlaps++; }}" +
            "return overlaps;";
        long overlaps = ((Number) js().executeScript(script)).longValue();
        boolean pass = overlaps == 0;
        return result(pass, overlaps + " overlapping element pair(s) found");
    }
    /** TC_UI/UX_019-024: all UI elements visible without needing to zoom */
    static String checkVisibility() {
        List<WebElement> elements =
                driver.findElements(By.cssSelector(
                        "button,a,input,img,.shelf-item"));
        boolean pass = true;
        for (WebElement e : elements) {
            if (!e.isDisplayed()) {
                pass = false;
                break;
            }
        }
        return result(pass,
                pass ?
                        "all UI elements are visible"
                        :
                        "some UI elements are hidden");
    }
    /** TC_UI/UX_025-030: zoom in/out without breaking the UI */
    static String checkZoom() {
        boolean survivedIn = zoomAndCheck(1.5);
        boolean survivedOut = zoomAndCheck(0.7);
        boolean pass = survivedIn && survivedOut;
        return result(pass, "zoom-in ok=" + survivedIn + ", zoom-out ok=" + survivedOut);
    }
    static boolean zoomAndCheck(double level) {
        js().executeScript(
                "document.body.style.zoom=arguments[0]",
                String.valueOf(level));
        try {
            Thread.sleep(500);
        } catch (Exception ignored) {
        }
        List<WebElement> elements =
                driver.findElements(
                        By.cssSelector(
                                "button,a,input,img,.shelf-item"));
        boolean ok = false;
        for (WebElement e : elements) {
            if (e.isDisplayed()) {
                ok = true;
                break;
            }
        }
        js().executeScript(
                "document.body.style.zoom='1'");
        return ok;
    }
    /** TC_UI/UX_031: website response speed (Navigation Timing API) */
    static String checkPageLoadSpeed() {
        String script = "var t=performance.timing; return t.loadEventEnd - t.navigationStart;";
        long loadMs = ((Number) js().executeScript(script)).longValue();
        boolean pass = loadMs >= 0 && loadMs <= MAX_LOAD_TIME_MS;
        return result(pass, loadMs + "ms (max " + MAX_LOAD_TIME_MS + "ms)");
    }
    static void login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("signin"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("username"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[text()='demouser']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[text()='testingisfun99']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-btn"))).click();
    }
    static void prepareCart() {
        driver.get(BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".shelf-item__buy-btn"))).click();
    }
    static JavascriptExecutor js() {
        return (JavascriptExecutor) driver;
    }
    static String result(boolean pass, String detail) {
        return (pass ? "PASS" : "FAIL") + " - " + detail;
    }
    interface CheckFn {
        String run();
    }
    void runCheck(String pageName, String checkName, CheckFn check) {
        String outcome;
        try {
            outcome = check.run();
        } catch (Exception e) {
            outcome = "FAIL - error: " + e.getMessage();
        }
        boolean passed = outcome.startsWith("PASS");
        if (passed) passCount++; else failCount++;
        System.out.printf("TC_UI/UX_%03d ", tcNumber++);
        System.out.printf("[%-11s] %-38s -> %s%n", pageName, checkName, outcome);

        softAssert.assertTrue(passed, "[" + pageName + "] " + checkName + " -> " + outcome);
    }
    static void printHeader() {
        System.out.println("============================================================");
        System.out.println(" UI/UX Automation Suite - bstackdemo.com (BrowserStack Android)");
        System.out.println("============================================================");
    }
    static void printSummary() {
        System.out.println("------------------------------------------------------------");
        System.out.printf("TOTAL: %d passed, %d failed%n", passCount, failCount);
        System.out.println("------------------------------------------------------------");
    }
}