package calculatoria_tests;

import common.BaseTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Cookie;
import page_object.calculatoria_pages.CalculatorPage;
import page_object.utilities.PropertyReader;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ElementaryCalculationsTest extends BaseTest {

    @Parameterized.Parameters
    public static Collection<Object> parametersForCalculations() {
        return Arrays.asList(
                new String[]{"3", "7", "+"},
                new String[]{"9", "6", "-"},
                new String[]{"4", "5", "*"},
                new String[]{"8", "2", "/"});
    }

    @Parameterized.Parameter(0)
    public String operandOne;

    @Parameterized.Parameter(1)
    public String operandTwo;

    @Parameterized.Parameter(2)
    public String operator;

    @Test
    public void checkElementaryCalculationsTest() {
        PropertyReader propertyReader = new PropertyReader();
        final String calculatoriaMainUrl = propertyReader.readProperty("calculatoria.main.page.url");
        getWebDriver().get(calculatoriaMainUrl);

        CalculatorPage calculatorPage = new CalculatorPage(getWebDriver());
        calculatorPage.clickCalculatorButtonForOperand(operandOne);
        calculatorPage.clickOperatorButton(operator);
        calculatorPage.clickCalculatorButtonForOperand(operandTwo);
        final String calculationCondition = calculatorPage.saveCalculationCondition();
        calculatorPage.clickResultButton();

        final Integer expectedCalculationResult = calculatorPage.countExpectedCalculationResult(operandOne,
                operator, operandTwo);
        final Integer actualCalculationResult = calculatorPage.getActualCalculationResult();

        final String calculatorMathExpression = calculatorPage.getCalculatorMathExpression(calculationCondition,
                actualCalculationResult);
        final Cookie cookieForTextAreaData = getWebDriver().manage().getCookieNamed("zf_obspaska1");
        final String notesValue = cookieForTextAreaData.getValue();
        final String notesMathExpression = calculatorPage.getDataFromNotes(notesValue);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(expectedCalculationResult)
                .as("Expected and actual calculation results are not equal")
                .isEqualTo(actualCalculationResult);

        softAssertions.assertThat(calculatorMathExpression)
                .as("Math expressions on calculator and notes are not equal")
                .isEqualTo(notesMathExpression);

        softAssertions.assertAll();
    }
}
