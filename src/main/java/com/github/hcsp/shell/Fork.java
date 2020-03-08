package com.github.hcsp.shell;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Fork {
    public static void main(String[] args) throws Exception {
        // 请在这里使用Java代码fork一个子进程，将fork的子进程的标准输出重定向到指定文件：工作目录下名为output.txt的文件
        // 工作目录是项目目录下的working-directory目录（可以用getWorkingDir()方法得到这个目录对应的File对象）
        // 传递的命令是sh run.sh
        // 环境变量是AAA=123

        // 创建ProcessBuilder对象
        ProcessBuilder processBuilder = new ProcessBuilder("sh", "run.sh");
        // 指定子进程的工作目录
        processBuilder.directory(getWorkingDir());
        // 获取父进程的环境变量
        Map<String, String> envs = processBuilder.environment();
        // 添加新的环境变量
        envs.put("AAA", "123");
        // 将输出重定向，如果将此进程中启动的子进程的输出在此父进程中输出出来，应使用
        // processBuilder.inheritIO();
        processBuilder.redirectOutput(getOutputFile());
        // 启动子进程，并阻塞父进程，直到子进程退出
        processBuilder.start().waitFor();
    }

    private static File getWorkingDir() {
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        return projectDir.resolve("working-directory").toFile();
    }

    private static File getOutputFile() {
        return new File(getWorkingDir(), "output.txt");
    }
}
