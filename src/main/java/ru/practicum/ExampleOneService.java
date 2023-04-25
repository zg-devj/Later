package ru.practicum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExampleOneService {
    private static final Logger log = LoggerFactory.getLogger(ExampleOneService.class);

    public int sum(int a, int b) {
        log.info("Got a={}, b={}", a, b);
        return a + b;
    }
}
