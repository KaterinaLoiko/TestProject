package ru.loiko.yamarket.api.test.search;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.loiko.yamarket.api.test.utils.GetUtils;
import ru.loiko.yamarket.base.PropertiesProviderConfiguration;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {PropertiesProviderConfiguration.class})
public class FirstApiTest {

    @Autowired
    Environment environment;

    private static String url;

    private static boolean preconditionForAll = true;

    @BeforeEach
    public void preconditions() {
        if (preconditionForAll) {
            url = environment.getProperty("url");
            preconditionForAll = false;
        }
    }

    @Description("Тест api для проверки поиска")
    @Test
    public void searchTest() {
        Response response = GetUtils.getSearchResult(url, "2", "игр");
        String responseString = response.getBody().asString();
        response.then().statusCode(200);
    }
}
