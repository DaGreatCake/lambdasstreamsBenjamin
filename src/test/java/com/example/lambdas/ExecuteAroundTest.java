package com.example.lambdas;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ExecuteAroundTest {
    private ExecuteAround executeAround = new ExecuteAround();

    @Test
    public void printOneLineContainsLoremIpsum() throws IOException {
        String s = executeAround.processFile();
        assertThat(s).contains("Lorem ipsum");
    }

    @Test
    public void processFileFunctionalInterface() throws IOException {
        String s = executeAround.processFile(BufferedReader::readLine);
        assertThat(s).contains("Lorem ipsum");
    }

    @Test
    public void firstLine() throws IOException {
        String s = executeAround.processFile(bufferedReader -> {
            Optional<String> firstLine = bufferedReader.lines().findFirst();

            if (firstLine.isPresent()) {
                return firstLine.get();
            } else {
                return "";
            }
        });
        assertThat(s).isEqualTo("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam congue massa auctor," +
                " dapibus odio ornare, feugiat ligula. Duis congue non sapien at faucibus." +
                " Sed a libero vitae augue molestie ornare non at nisl. Ut id vehicula urna." +
                " Vestibulum sed euismod augue. Donec luctus pulvinar dolor, id elementum quam maximus id." +
                " Fusce sagittis elit vitae hendrerit tincidunt. Phasellus nibh mauris, mollis id iaculis non," +
                " tempus et mauris. Aenean iaculis metus ac quam mattis maximus et non nulla." +
                " Ut euismod sem dictum orci venenatis dignissim. Integer ornare quis sapien malesuada tempus.");
    }

    @Test
    public void lastLine() throws IOException {
        String s = executeAround.processFile(bufferedReader -> {
            List<String> lines = bufferedReader.lines().collect(Collectors.toList());
            Collections.reverse(lines);

            if (lines.size() > 0) {
                return lines.get(0);
            } else {
                return "";
            }
        });

        assertThat(s).isEqualTo("Suspendisse sit amet odio placerat justo sagittis pellentesque nec a nisl. " +
                "Aenean ipsum diam, mattis ac neque in, rhoncus finibus orci. " +
                "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. " +
                "Ut tincidunt mi vitae purus gravida, ut bibendum justo sodales. Ut vitae diam a neque porttitor sagittis." +
                " Praesent pharetra nunc est, id dictum odio ornare hendrerit. Quisque in dictum purus. " +
                "Suspendisse non fermentum ipsum. Fusce vel malesuada elit. Vestibulum euismod magna eu est sagittis rhoncus.");
    }

    @Test
    public void longestLine() throws IOException {
        String s = executeAround.processFile(bufferedReader -> {
            String longestLine = "";
            List<String> lines = bufferedReader.lines().collect(Collectors.toList());

            for (String line : lines) {
                if (line.length() > longestLine.length()) {
                    longestLine = line;
                }
            }

            return longestLine;
        });

        assertThat(s).isEqualTo("Fusce eu leo lacus. Nulla facilisi. Sed tincidunt ante in erat pulvinar venenatis. " +
                "Cras malesuada finibus leo. Maecenas luctus condimentum mollis. Morbi id imperdiet sapien. " +
                "Nunc consectetur in tortor molestie pulvinar. Orci varius natoque penatibus et magnis dis parturient montes, " +
                "nascetur ridiculus mus. Vestibulum mattis, orci quis accumsan rutrum, sapien tellus suscipit ante, " +
                "nec volutpat justo ex ut ante. Vivamus egestas iaculis ligula, eget mattis libero lobortis a. " +
                "Maecenas at nulla elit. Etiam sit amet urna porttitor, tempus nunc sit amet, imperdiet arcu. " +
                "Donec suscipit eu sapien sit amet scelerisque. Interdum et malesuada fames ac ante ipsum primis in faucibus. " +
                "Vestibulum eget urna vitae tellus vulputate luctus.");
    }

    @Test
    public void longestWord() throws IOException {
        String s = executeAround.processFile(bufferedReader -> {
            String longestWord = "";
            List<String> lines = bufferedReader.lines().collect(Collectors.toList());

            for (String line : lines) {
                String[] words = line.split(" ");

                for (String word : words) {
                    word = word.replaceAll("\\W", "");

                    if (word.length() > longestWord.length()) {
                        longestWord = word;
                    }
                }
            }

            return longestWord;
        });

        assertThat(s).isEqualTo("Pellentesque");
    }
}
