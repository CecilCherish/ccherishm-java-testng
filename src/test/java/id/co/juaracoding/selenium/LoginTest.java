package id.co.juaracoding.selenium;

import id.co.juaracoding.selenium.pages.LoginPage;
import id.co.juaracoding.util.TestConfig;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * LoginTest menguji skenario autentikasi pada Simple Apps.
 * Menggunakan pola Page Object Model dan method @Test dengan format snake_case.
 */
public class LoginTest extends BaseSeleniumTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, description = "Verifikasi login berhasil dengan kredensial seed yang valid")
    public void test_login_berhasil() {
        driver.get(TestConfig.LOGIN_URL);

        loginPage.login(TestConfig.VALID_EMAIL, TestConfig.VALID_PASSWORD);

        Assert.assertTrue(
                loginPage.isDashboardDisplayed(),
                "Pengguna seharusnya berhasil login dan diarahkan ke halaman dashboard."
        );
    }

    @Test(priority = 2, description = "Verifikasi login gagal saat memasukkan password salah")
    public void test_login_gagal_password_salah() {
        driver.get(TestConfig.LOGIN_URL);

        loginPage.login(TestConfig.VALID_EMAIL, TestConfig.INVALID_PASSWORD);

        // Validasi: tetap di halaman login atau muncul pesan kesalahan
        boolean isFailed = loginPage.isErrorMessageDisplayed() || driver.getCurrentUrl().contains("login");
        Assert.assertTrue(
                isFailed,
                "Sistem seharusnya menolak login dengan password salah dan menampilkan pesan kesalahan."
        );
    }
}

