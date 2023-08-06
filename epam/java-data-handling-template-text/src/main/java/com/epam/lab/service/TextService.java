package com.epam.lab.service;

public interface TextService {

    String removeString(String base, String remove);

    boolean isQuestionString(String text);

    String concatenate(String... elements);

    String toJumpCase(String text);

    boolean isPalindrome(String string);
}
