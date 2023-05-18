package test;

import data.DataHelper;
import data.SQLHelper;
import page.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static data.SQLHelper.cleanDataBase;
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
