package com.github.hcsp.shell;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Fork {
    public static void main(String[] args) throws Exception {
        // 请在这里使用Java代码fork一个子进程，将fork的子进程的标准输出重定向到指定文件：工作目录下名为output.txt的文件
        // 工作目录是项目目录下的working-directory目录（可以用getWorkingDir()方法得到这个目录对应的File对象）
        // 传递的命令是sh run.sh
        // 环境变量是AAA=123
        String[] envp = {"AAA=123"};
        Runtime runtime = Runtime.getRuntime();
        Process ps = runtime.exec("sh run.sh", envp, getWorkingDir());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        String readline = null;
        BufferedWriter bufferedWriter = Files.newBufferedWriter(getOutputFile().toPath());
        while ((readline = bufferedReader.readLine()) != null) {
            bufferedWriter.write(readline + "\n");

        }
        bufferedWriter.flush();
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static File getWorkingDir() {
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        return projectDir.resolve("working-directory").toFile();
    }

    private static File getOutputFile() {
        return new File(getWorkingDir(), "output.txt");
    }
}
