package ru.practicum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration // помечает класс как java-config для контекста приложения
@EnableWebMvc // призывает импортировать дополнительную конфигурацию для веб-приложений
//@PropertySource(value = "file:C:\\myapp\\myapp.properties", ignoreResourceNotFound = true) // пример для Windows
//@PropertySource(value = "file:/etc/myapp/myapp.properties", ignoreResourceNotFound = true) // пример для Unix-like
public class WebConfig {
}
