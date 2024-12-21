package com.example.kalkulator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculationController {

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("calculationRequest", new CalculationRequest());
        return "calculator";  // zwróć szablon Thymeleaf (calculator.html)
    }

    @PostMapping("/calculate")
    public String calculate(@ModelAttribute CalculationRequest request, Model model) {
        double result = 0;

        switch (request.getOperation()) {
            case "add":
                result = request.getNumber1() + request.getNumber2();
                break;
            case "subtract":
                result = request.getNumber1() - request.getNumber2();
                break;
            case "multiply":
                result = request.getNumber1() * request.getNumber2();
                break;
            case "divide":
                if (request.getNumber2() != 0) {
                    result = request.getNumber1() / request.getNumber2();
                } else {
                    model.addAttribute("error", "Cannot divide by zero");
                    return "calculator";  // zwróć stronę z błędem
                }
                break;
            default:
                model.addAttribute("error", "Invalid operation");
                return "calculator";
        }

        model.addAttribute("result", result);
        return "calculator";  // zwróć stronę z wynikiem
    }
}

class CalculationRequest {
    private double number1;
    private double number2;
    private String operation;

    // Gettery i Settery
    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
