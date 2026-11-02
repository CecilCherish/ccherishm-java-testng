package id.co.juaracoding.selenium;

import id.co.juaracoding.selenium.pages.ForgotPasswordPage;
import id.co.juaracoding.util.TestConfig;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * LupaPasswordTest menguji alur reset password pada Simple Apps.
 * Menguji submit email terdaftar (berhasil) dan email yang tidak terdaftar (gagal).
 */
public class LupaPasswordTest extends BaseSeleniumTest {

    private ForgotPasswordPage forgotPasswordPage;

    @BeforeMethod
    public void initPage() {
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Test(priority = 1, description = "Verifikasi alur reset password berhasil dengan email yang terdaftar")
    public void test_lupa_password_berhasil() {
        driver.get(TestConfig.FORGOT_PASSWORD_URL);

        forgotPasswordPage.submitForgotPassword(TestConfig.VALID_EMAIL);

        Assert.assertTrue(
                forgotPasswordPage.isResetProcessTriggered(),
                "Proses reset password seharusnya ter-trigger saat memasukkan email yang terdaftar."
        );
    }

    @Test(priority = 2, description = "Verifikasi pesan error saat submit email yang tidak terdaftar")
    public void test_lupa_password_gagal_email_tidak_terdaftar() {
        driver.get(TestConfig.FORGOT_PASSWORD_URL);

        forgotPasswordPage.submitForgotPassword(TestConfig.UNREGISTERED_EMAIL);

        Assert.assertTrue(
                forgotPasswordPage.isErrorMessageDisplayed(),
                "Pesan kesalahan seharusnya muncul saat memasukkan email yang tidak terdaftar."
        );
    }
}

