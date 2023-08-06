package com.epam.lab.service;

public interface RegExpService {

    String maskSensitiveData();

    String replacePlaceholders(double paymentAmount, double balance);
}
