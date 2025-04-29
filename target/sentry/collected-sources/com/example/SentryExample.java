package com.example;

import io.sentry.Sentry;
import io.sentry.SentryLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SentryExample {
    private static final Logger logger = LoggerFactory.getLogger(SentryExample.class);

    public static void main(String[] args) {
        // Load properties from sentry.properties
        Properties props = new Properties();
        try (InputStream input = SentryExample.class.getClassLoader().getResourceAsStream("sentry.properties")) {
            if (input == null) {
                logger.error("Unable to find sentry.properties");
                return;
            }
            props.load(input);
        } catch (IOException e) {
            logger.error("Error loading sentry.properties", e);
            return;
        }

        // Get DSN from properties
        String dsn = props.getProperty("dsn");
        if (dsn == null || dsn.isEmpty()) {
            logger.error("DSN not found in sentry.properties");
            return;
        }

        // Initialize Sentry
        Sentry.init(options -> {
            options.setDsn(dsn);
            options.setTracesSampleRate(Double.parseDouble(props.getProperty("traces-sample-rate", "1.0")));
            options.setDebug(Boolean.parseBoolean(props.getProperty("debug", "false")));
            options.setEnvironment(props.getProperty("environment", "development"));
        });

        try {
            logger.info("Starting factorial calculation example");
            
            // Example of calculating factorial
            int number = 5;
            long result = calculateFactorial(number);
            
            logger.info("Factorial of {} is {}", number, result);
            
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