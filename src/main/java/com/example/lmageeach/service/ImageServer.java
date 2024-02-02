package com.example.lmageeach.service;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageServer {
    public static void main(String[] args) throws IOException {
        int port = 8081; // 后端服务器监听的端口号
        String imageDirectory = new File("").getAbsolutePath()+"\\image\\";

        // 创建一个 HttpServer 实例，并绑定端口
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // 创建一个上下文，并指定处理请求的处理器
        server.createContext("/images", new ImageHandler(imageDirectory));

        // 启动服务器
        server.start();

        System.out.println("服务器已启动，监听端口：" + port);
    }

    // 自定义处理器
    static class ImageHandler implements HttpHandler {
        private String imageDirectory;

        public ImageHandler(String imageDirectory) {
            this.imageDirectory = imageDirectory;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestedImage = exchange.getRequestURI().getQuery(); // 获取请求中的图片名称

            if (requestedImage == null) {
                exchange.sendResponseHeaders(400, 0); // 如果请求中没有指定图片名称，返回错误状态码
                System.out.println("请指定图片名称");
                return;
            }

            String imagePath = imageDirectory + requestedImage;

            // 读取图片文件数据
            Path imageFile = Paths.get(imagePath);

            if (!Files.exists(imageFile)) {
                exchange.sendResponseHeaders(404, 0); // 如果请求的图片不存在，返回错误状态码
                System.out.println("图片不存在");
                return;
            }

            byte[] imageData = Files.readAllBytes(imageFile);

            // 设置响应头
            exchange.getResponseHeaders().set("Content-Type", "image/jpeg");
            exchange.sendResponseHeaders(200, imageData.length);

            // 获取输出流，并将图片数据写入响应
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(imageData);
            outputStream.close();
        }
    }
}
