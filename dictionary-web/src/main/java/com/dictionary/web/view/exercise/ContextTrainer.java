package com.dictionary.web.view.exercise;

import com.dictionary.web.domain.Size;
import com.dictionary.web.domain.Trainer;
import com.dictionary.web.view.TextRecordField;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

import java.util.UUID;

public class ContextTrainer extends Trainer {

    {
        setWidthFull();
        setAlignItems(Alignment.CENTER);
        getStyle().set("border-radius", "10px");
        getStyle().set("border", "3px solid #504F51");
    }

    private final TextField textField;

    public ContextTrainer(String example, String explanation, int maxLength) {
        String inputId = UUID.randomUUID().toString();
        String outputId = UUID.randomUUID().toString();
        Html header = createExample(example, outputId, maxLength);
        add(header);
        add(createExplanation(explanation));
        textField = createTextField(inputId, maxLength);
        add(textField);
        header.getElement().executeJs(getScript(inputId, outputId, maxLength));
    }

    private static String getScript(String inputId, String outputId, int maxLength) {
        return "const input = document.getElementById('" + inputId + "');\n" +
                "input.addEventListener('input', function(event) {\n" +
                "var text = event.target.value?event.target.value:'';\n" +
                "text=text.length>" + maxLength + "?text:text+'_'.repeat(" + maxLength + "-text.length);\n" +
                "const outputElement = document.getElementById('" + outputId + "');\n" +
                "outputElement.textContent = text;\n" +
                "});";
    }

    private static Span createExplanation(String explanation) {
        var span = new Span(explanation);
        span.getStyle().set("text-align", "center");
        return span;
    }

    private static Html createExample(String example, String outputId, int maxLength) {
        String part1 = example.substring(0, example.indexOf("{"));
        String part2 = example.substring(example.indexOf("}") + 1);
        String span = "<H1 style='text-align:center;'> " + part1 + " <span id='" + outputId + "' style=\"color:red\"><font>" + "_".repeat(maxLength) + "</span> " + part2 + "</H1>";
        return new Html(span);
    }

    private static TextField createTextField(String id, int maxLength) {
        var field = new TextRecordField();
        field.setId(id);
        field.setMaxLength(maxLength);
        field.getStyle().set("font-size", "18px");
        field.setWidth(Size.PERCENT_50);
        field.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        return field;
    }

    @Override
    public String getValue() {
        return textField.getValue();
    }
}
