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

        // 进程构建器：创建一个具有指定操作系统程序和参数的进程构造器
        ProcessBuilder pb=new ProcessBuilder("sh","run.sh");
        // 设置此进程的工作目录，执行start()方法启动的子进程将以这个为工作目录
        pb.directory(getWorkingDir());
        // pb.environment()方法返回此进程构建器的环境字符串映射
        // 这里是获得了引用，所以对envs的修改会影响环境变量
        Map<String,String> envs=pb.environment();
        // 将题目中的环境变量添加到进程构建器中
        envs.put("AAA","123");
        // 将次进程构建器的标准输入源设置为目标文件
        // 也就是把结果输出到参数文件中
        pb.redirectInput(getOutputFile());
        // 开始进程,并且等待其执行完成
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
