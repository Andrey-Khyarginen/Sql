package Test;

import Data.DataHelper;
import Data.SQLHelper;
import Page.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static Data.SQLHelper.cleanDataBase;
import static com.codeborne.selenide.Selenide.open;

public class BankTest{
    @AfterAll
    static void teardown() {
        cleanDataBase();
    }

    @Test
    void shouldSuccessfullyLogin() {


        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyAuthCodePageVisibility();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());

    }
}
