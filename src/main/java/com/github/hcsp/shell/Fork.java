package com.github.hcsp.shell;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Fork {
    public static void main(String[] args) throws Exception {
        File workDir = getWorkingDir();
        //可执行环境，参数
        ProcessBuilder pb = new ProcessBuilder("sh", "run.sh");
        //设置工作目录
        pb.directory(workDir);
        //设置环境变量
        Map<String, String> envs = pb.environment();
        envs.put("AAA", "123");

        pb.redirectOutput(getOutputFile());

        pb.start().waitFor();
    }

    private static File getWorkingDir() {
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        return projectDir.resolve("working-directory").toFile();
    }

    private static File getOutputFile() {
        return new File(getWorkingDir(), "output.txt");
    }
}
