package com.example.demo.test.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        String filePath = "large_file.txt"; // 2G文件路径
        int topN = 100;

        try {
            // 1. 分块读取文件并统计词频
            Map<String, Integer> wordCount = new HashMap<>();
            processFileInChunks(filePath, wordCount);

            // 2. 获取Top100高频词
            List<Map.Entry<String, Integer>> topWords = wordCount.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(topN)
                    .collect(Collectors.toList());

            // 3. 输出结果
            System.out.println("Top " + topN + " 高频单词:");
            for (int i = 0; i < topWords.size(); i++) {
                Map.Entry<String, Integer> entry = topWords.get(i);
                System.out.printf("%d. %s: %d 次\n", i + 1, entry.getKey(), entry.getValue());
            }

        } catch (IOException e) {
            System.err.println("文件处理错误: " + e.getMessage());
        }
    }

    /**
     * 分块读取大文件并统计词频
     */
    private static void processFileInChunks(String filePath, Map<String, Integer> wordCount) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int chunkSize = 1024 * 1024; // 1MB块大小
            StringBuilder chunkBuffer = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                chunkBuffer.append(line).append("\n");

                // 当缓冲区达到指定大小时处理
                if (chunkBuffer.length() >= chunkSize) {
                    processChunk(chunkBuffer.toString(), wordCount);
                    chunkBuffer.setLength(0); // 清空缓冲区
                }
            }

            // 处理最后剩余的数据
            if (chunkBuffer.length() > 0) {
                processChunk(chunkBuffer.toString(), wordCount);
            }
        }
    }

    /**
     * 处理单个数据块
     */
    private static void processChunk(String content, Map<String, Integer> wordCount) {
        // 简单的文本预处理：转小写、去除标点符号
        String processedContent = content.toLowerCase()
                .replaceAll("[^a-zA-Z0-9\\s]", " ")
                .replaceAll("\\s+", " ")
                .trim();

        // 分割单词并统计
        String[] words = processedContent.split("\\s+");
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
    }

    class A {
        private int amount;
    }
    class B {

    }
}
