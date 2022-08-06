package ru.loiko.yamarket.ui.test.testData;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestArguments {

    public static Stream<Arguments> provideCategoriesWithUnderCategories() {
        return Stream.of(
                Arguments.of("Электроника", "Смартфоны", "Умные часы и браслеты", "Наушники и Bluetooth-гарнитуры", "Портативная акустика", "Аудиотехника"),
                Arguments.of("Бытовая техника", "Холодильники", "Обогреватели", "Микроволновые печи", "Соковыжималки", "Блендеры")
        );
    }

    public static Stream<Arguments> provideSearchArguments() {
        return Stream.of(
                Arguments.of("buh", "игровой ноутбук"),
                Arguments.of("игр", "игрушки")
        );
    }
}
