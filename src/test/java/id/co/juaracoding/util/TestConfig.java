package id.co.juaracoding.util;

/**
 * TestConfig adalah satu-satunya tempat nilai konfigurasi test boleh berada.
 * Dilarang menyebar nilai yang sama (base URL, kredensial) langsung sebagai literal di banyak file test.
 */
public class TestConfig {

    // Base URL aplikasi Simple Apps (dapat dioverride via system property: -DbaseUrl=http://...)
    // Default port Vue/Vite pada umumnya adalah 3000, 5173, atau 8080
    public static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:3000");

    // Endpoint URL halaman
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String REGISTER_URL = BASE_URL + "/register";
    public static final String FORGOT_PASSWORD_URL = BASE_URL + "/forgot-password";
    public static final String DASHBOARD_URL = BASE_URL + "/dashboard";

    // Kredensial akun seed valid untuk Login
    public static final String VALID_USERNAME = "admin";
    public static final String VALID_EMAIL = "admin@example.com";
    public static final String VALID_PASSWORD = "Password123!";

    // Kredensial invalid untuk skenario negatif Login
    public static final String INVALID_PASSWORD = "wrongPassword999";

    // Data form Registrasi valid
    public static final String REG_NAME = "Test User JuaraCoding";
    public static final String REG_PASSWORD = "Password123!";
    // KTP / NPWP valid persis 16 digit sesuai spesifikasi soal
    public static final String VALID_KTP_NPWP = "3171012345670001";

    // Data form Registrasi invalid (KTP / NPWP bukan 16 digit)
    public static final String INVALID_KTP_NPWP = "12345";

    // Email untuk skenario Lupa Password (belum terdaftar)
    public static final String UNREGISTERED_EMAIL = "bukan_member_juaracoding@example.com";

    // Waktu tunggu (detik)
    public static final int IMPLICIT_WAIT_SECONDS = 10;
    public static final int EXPLICIT_WAIT_SECONDS = 10;
    public static final int PAGE_LOAD_TIMEOUT_SECONDS = 30;

    /**
     * Helper untuk menghasilkan email registrasi unik agar tidak conflict jika ditest berulang
     */
    public static String generateUniqueEmail() {
        return "qa_" + System.currentTimeMillis() + "@testmail.com";
    }
}

