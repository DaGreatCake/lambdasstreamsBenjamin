package com.example.lambdas;

import com.example.lambdas.functionalinterfaces.BufferedReaderProcessor;

import java.io.*;

public class ExecuteAround {
    private static final String projectRoot = new File("").getAbsolutePath() + File.separator;

    public String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("data.txt")))) {
            String oneLineFromFile = br.readLine(); // this should be parameterized
            System.out.println(oneLineFromFile); // this one too?
            return oneLineFromFile;
        }
    }

    public String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(projectRoot + "src/test/resources/com/example/lambdas/data.txt"))) {
            return processor.process(bufferedReader);
        }
    }
}
