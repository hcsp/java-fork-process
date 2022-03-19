package com.github.hcsp.shell;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Fork {
    public static void main(String[] args) throws Exception {
        // 请在这里使用Java代码fork一个子进程，将fork的子进程的标准输出重定向到指定文件：工作目录下名为output.txt的文件
        ProcessBuilder process = new ProcessBuilder("sh", "run.sh");
        // 工作目录是项目目录下的working-directory目录（可以用getWorkingDir()方法得到这个目录对应的File对象）
        process.directory(getWorkingDir());
        // 传递的命令是sh run.sh
        // 环境变量是AAA=123
        Map<String, String> evn = process.environment();
        evn.put("AAA", "123");
        process.inheritIO();
        process.redirectError(getOutputFile());
        process.start().waitFor();
    }

    private static File getWorkingDir() {
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        return projectDir.resolve("working-directory").toFile();
    }

    private static File getOutputFile() {
        return new File(getWorkingDir(), "output.txt");
    }
}
