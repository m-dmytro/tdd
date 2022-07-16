package com.hw.tdd.extensions;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DisableOnMondayExtension implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();

        ConditionEvaluationResult result;
        if (dayOfWeek == DayOfWeek.MONDAY) {
            result = ConditionEvaluationResult.disabled("Today is Monday. It's hard to work");
        } else if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            result = ConditionEvaluationResult.disabled("Are you joking? WEEKENDS");
        } else {
            result = ConditionEvaluationResult.enabled("Need to work");
        }

        return result;
    }

}
