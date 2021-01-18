package org.evrim.spring.exam.mvc.springboot.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("echo")
public class EchoController {

    @Autowired
    private TaskExecutor taskExecutor;


    @GetMapping("info")
    public HttpEntity<String> info(WebRequest webRequest) {
        String user = webRequest.getRemoteUser();
        String contextPath = webRequest.getContextPath();
        List<String> headers = new ArrayList<>();
        webRequest.getHeaderNames().forEachRemaining(s -> headers.add(s));

        String response = String.format("User: %s, Path: %s, Headers: %s", user, contextPath, String.join(",", headers));

        return new HttpEntity<>(response);

    }

    @RequestMapping
    public void echo(InputStream inputStream, OutputStream outputStream) throws IOException {
        inputStream.transferTo(outputStream);
        outputStream.write(10);
    }


    @RequestMapping("deferred")
    public DeferredResult<String> deferred(String message) {
        DeferredResult<String> result = new DeferredResult<>();

        taskExecutor.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.setResult(message);
        });

        return result;
    }

    @RequestMapping("callable")
    public Callable<String> callable(String message) {
        return () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return message;
        };
    }

    @RequestMapping("streaming")
    public StreamingResponseBody streaming(String message) {
        return new StreamingResponseBody() {
            @SneakyThrows
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                for(int i=1; i<11; i++) {
                    outputStream.write((message+i).getBytes());
                    Thread.sleep(1000);
                }
            }
        };

    }


}
