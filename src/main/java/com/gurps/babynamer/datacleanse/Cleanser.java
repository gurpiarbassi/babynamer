package com.gurps.babynamer.datacleanse;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * This utility was used to generate the cleansed_names.txt file from the raw_names.txt file.
 */
public class Cleanser {

    private final static String FILENAME = "/raw_names.txt";

    public static void main(String[] args) throws IOException, URISyntaxException {
        final URI resource = Cleanser.class.getResource(FILENAME).toURI();
        File file = new File(resource);

        final List<String> result;

        try (Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()))) {
            result = lines
                .map(Cleanser::toFirstWord)
                .collect(toList());
        }

        Files.write(Paths.get("cleansed_names.txt"), result, Charset.forName("UTF-8"));
    }

    public static String toFirstWord(String longString) {
        return longString.substring(0, longString.indexOf("\t"));
    }
}
