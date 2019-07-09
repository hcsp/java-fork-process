package com.github.hcsp.shell;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ForkTest {
    @Test
    public void testFork() throws Exception {
        Fork.main(null);
        Path outputFile =
                Paths.get(System.getProperty("user.dir")).resolve("working-directory/output.txt");
        String output = new String(Files.readAllBytes(outputFile));
        Assertions.assertTrue(output.contains("AAA is: 123"));
        Assertions.assertTrue(output.contains("11K"));
        Assertions.assertTrue(output.contains(".hidden.txt"));
    }
}
