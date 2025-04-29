package com.example;

import io.sentry.Sentry;
import io.sentry.SentryLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SentryExample {
    private static final Logger logger = LoggerFactory.getLogger(SentryExample.class);

    public static void main(String[] args) {
        // Initialize Sentry - it will automatically load sentry.properties from
        // classpath
        Sentry.init();

        try {
            logger.info("Starting factorial calculation example");

            // Example of calculating factorial
            int number = 2;
            long result = calculateFactorial(number);

            logger.info("Etapa 1 - Factorial of {} is {}", number, result);

            // Example of calculating factorial
            number = 3;
            result = calculateFactorial(number);

            logger.info("Etapa 2 - Factorial of {} is {}", number, result);

            // Example of calculating factorial
            number = 5;
            result = calculateFactorial(number);

            logger.info("Etapa 3 - Factorial of {} is {}", number, result);

            // Example of capturing a custom event
            Sentry.captureMessage("Factorial calculation completed successfully", SentryLevel.INFO);

            // Example of error handling with Sentry
            try {
                // This will throw an exception
                calculateFactorial(-1);
            } catch (IllegalArgumentException e) {
                logger.error("Error calculating factorial", e);
                Sentry.captureException(e);
            }

        } finally {
            // Ensure all events are sent before closing
            Sentry.close();
        }
    }

    private static long calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}