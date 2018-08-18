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

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.util.stream.Collectors.toList;

public class NameSearch {
    private final static String FILENAME = "/cleansed_names.txt";

    public static void main(String[] args) throws IOException, URISyntaxException {
        final String nameStartsWith = args[0];

        final URI resource = Cleanser.class.getResource(FILENAME).toURI();
        File file = new File(resource);

        final List<String> result;

        try (Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()))) {
            result = lines
                .filter(word -> word.startsWith(nameStartsWith))
                .collect(toList());
        }

        Files.write(Paths.get("target/name_search_results.txt"), result, Charset.forName("UTF-8"), CREATE, TRUNCATE_EXISTING);
    }
}
