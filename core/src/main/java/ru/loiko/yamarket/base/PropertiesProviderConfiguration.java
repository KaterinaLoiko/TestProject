package ru.loiko.yamarket.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("/properties/test.properties")
public class PropertiesProviderConfiguration {
}
