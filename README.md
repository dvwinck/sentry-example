# Sentry Java Example

This is a simple Java example demonstrating how to use Sentry for error tracking and logging in a Java application.

## Prerequisites

- Java 11 or higher
- Maven
- A Sentry account and DSN

## Setup

1. Clone this repository
2. Replace `YOUR_DSN_HERE` in `SentryExample.java` with your actual Sentry DSN
3. Build the project:
   ```bash
   mvn clean package
   ```
4. Run the example:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.SentryExample"
   ```

## Features Demonstrated

- Sentry initialization and configuration
- Logging with SLF4J and Logback
- Custom event capture
- Exception tracking
- Performance monitoring

## What to Expect

When you run the example, you'll see:
1. A successful factorial calculation
2. Log messages in the console
3. A custom event sent to Sentry
4. An error event sent to Sentry when trying to calculate factorial of a negative number

## Sentry Integration

The example shows how to:
- Initialize Sentry with your DSN
- Capture custom messages
- Track exceptions
- Configure logging with Sentry
- Close Sentry properly
