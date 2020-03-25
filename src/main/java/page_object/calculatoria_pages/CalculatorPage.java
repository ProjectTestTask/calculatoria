package page_object.calculatoria_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page_object.common.AbstractPage;

import java.util.List;

public class CalculatorPage extends AbstractPage {
    @FindBys({
            @FindBy(xpath = "//a[@class='abtn2 zabtn']")
    })
    private List<WebElement> calculatorOperandsButtons;


    @FindBy(xpath = "//div[@id='btn107']/a")
    private WebElement calculatorAdditionButton;

    @FindBy(xpath = "//div[@id='btn109']/a")
    private WebElement calculatorSubtractionButton;

    @FindBy(xpath = "//div[@id='btn106']/a")
    private WebElement calculatorMultiplicationButton;

    @FindBy(xpath = "//div[@id='btn111']/a")
    private WebElement calculatorDivisionButton;

    @FindBy(xpath = "//div[@id='btn13']/a")
    private WebElement calculatorSumButton;

    @FindBy(xpath = "//input[@name='expr']")
    private WebElement hiddenCalculationDisplayInput;


    public CalculatorPage(final WebDriver webDriver) {
        super(webDriver);
    }


    public void clickCalculatorButtonForOperand(final String operandButtonValue) {
        for (WebElement button: calculatorOperandsButtons) {
            if (button.getText().trim().equals(operandButtonValue)) {
                button.click();
                break;
            }
        }
    }

    public void clickOperatorButton(final String operator) {
        switch (operator) {
            case "+":
                getWebDriverWait().until(ExpectedConditions.visibilityOf(calculatorAdditionButton));
                calculatorAdditionButton.click();
                break;
            case "-":
                getWebDriverWait().until(ExpectedConditions.visibilityOf(calculatorSubtractionButton));
                calculatorSubtractionButton.click();
                break;
            case "*":
                getWebDriverWait().until(ExpectedConditions.visibilityOf(calculatorMultiplicationButton));
                calculatorMultiplicationButton.click();
                break;
            case"/":
                getWebDriverWait().until(ExpectedConditions.visibilityOf(calculatorDivisionButton));
                calculatorDivisionButton.click();
                break;
            default:
                throw new IllegalStateException("Not applicable operand");
        }
    }

    public void clickResultButton() {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(calculatorSumButton));
        calculatorSumButton.click();
    }

    public Integer countExpectedCalculationResult(final String operandOne,
                                                  final String operator,
                                                  final String operandTwo) {
        switch (operator) {
            case "+":
                return Integer.parseInt(operandOne) + Integer.parseInt(operandTwo);
            case "-":
                return Integer.parseInt(operandOne) - Integer.parseInt(operandTwo);
            case "*":
                return Integer.parseInt(operandOne) * Integer.parseInt(operandTwo);
            case "/":
                return Integer.parseInt(operandOne) / Integer.parseInt(operandTwo);
            default:
                throw new IllegalStateException("Not applicable operand");
        }
    }

    public Integer getActualCalculationResult() {
        return Integer.valueOf(hiddenCalculationDisplayInput.getAttribute("value"));
    }

    public String getCalculatorMathExpression(final String calculationCondition, final Integer actualCalcualtionResult) {
        return String.format("%s%s%s", calculationCondition, "=", actualCalcualtionResult);
    }

    public String saveCalculationCondition() {
        return hiddenCalculationDisplayInput.getAttribute("value");
    }

    public String getDataFromNotes(final String notesValue) {
        final String addedEqualSignToString = notesValue.replace("%3D", "=");
        return addedEqualSignToString.replace("%0A", "");
    }
}
