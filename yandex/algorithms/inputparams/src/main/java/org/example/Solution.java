package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<Integer> arr;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().strip());
            arr = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i : arr) {
            writer.write(String.valueOf(i));
            writer.write(" ");
        }
        writer.flush();
    }

}