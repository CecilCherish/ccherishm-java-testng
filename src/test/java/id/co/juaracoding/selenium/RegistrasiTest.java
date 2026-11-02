package id.co.juaracoding.selenium;

import id.co.juaracoding.selenium.pages.RegisterPage;
import id.co.juaracoding.util.TestConfig;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * RegistrasiTest menguji skenario pembuatan akun pada Simple Apps.
 * Menguji skenario data valid dan validasi KTP/NPWP harus 16 digit.
 */
public class RegistrasiTest extends BaseSeleniumTest {

    private RegisterPage registerPage;

    @BeforeMethod
    public void initPage() {
        registerPage = new RegisterPage(driver);
    }

    @Test(priority = 1, description = "Verifikasi registrasi berhasil dengan data lengkap dan valid")
    public void test_registrasi_berhasil() {
        driver.get(TestConfig.REGISTER_URL);

        String uniqueEmail = TestConfig.generateUniqueEmail();
        registerPage.register(
                TestConfig.REG_NAME,
                uniqueEmail,
                TestConfig.REG_PASSWORD,
                TestConfig.VALID_KTP_NPWP // 16 digit valid
        );

        Assert.assertTrue(
                registerPage.isRegistrationSuccessful(),
                "Akun baru dengan data valid seharusnya berhasil ter-registrasi."
        );
    }

    @Test(priority = 2, description = "Verifikasi registrasi gagal jika nomor KTP/NPWP bukan 16 digit")
    public void test_registrasi_gagal_ktp_tidak_valid() {
        driver.get(TestConfig.REGISTER_URL);

        String uniqueEmail = TestConfig.generateUniqueEmail();
        registerPage.register(
                TestConfig.REG_NAME,
                uniqueEmail,
                TestConfig.REG_PASSWORD,
                TestConfig.INVALID_KTP_NPWP // Tidak 16 digit
        );

        // Validasi: form menolak registrasi dan/atau memunculkan pesan validasi kesalahan
        boolean isValidationTriggered = registerPage.isErrorMessageDisplayed() || driver.getCurrentUrl().contains("register");
        Assert.assertTrue(
                isValidationTriggered,
                "Form harus menolak nomor KTP/NPWP yang bukan 16 digit dan menampilkan pesan kesalahan."
        );
    }
}

