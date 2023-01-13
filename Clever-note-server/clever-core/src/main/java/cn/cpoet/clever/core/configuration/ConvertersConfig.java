package cn.cpoet.clever.core.configuration;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 序列化配置和扩展类型转换器
 * <br />
 * 于spring boot 2.2.8.RELEASE 中实现 {@link Converter} 不要使用Lambda表达式，否则将会出于类型推断异常。
 *
 * @author Cpoet
 * @see Converter
 */
@SuppressWarnings("all")
public class ConvertersConfig {
    private final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    private final static String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    private final static String DEFAULT_DATE_TIME_FORMAT = DEFAULT_DATE_FORMAT + " " + DEFAULT_TIME_FORMAT;

    private final DateTimeFormatter dateFormat;

    private final DateTimeFormatter timeFormat;

    private final DateTimeFormatter dateTimeFormatter;

    public ConvertersConfig(JacksonProperties jacksonProperties) {
        String jacksonDateFormat = jacksonProperties.getDateFormat();
        dateFormat = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        timeFormat = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT);
        dateTimeFormatter = DateTimeFormatter.ofPattern(StringUtils.hasLength(jacksonDateFormat) ? jacksonDateFormat :
            DEFAULT_DATE_TIME_FORMAT, Locale.CHINESE);
    }

    @Bean
    public Converter<String, LocalTime> str2localTime() {
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String str) {
                return LocalTime.parse(str, timeFormat);
            }
        };
    }

    @Bean
    public Converter<String, LocalDate> str2localDate() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String str) {
                return LocalDate.parse(str, dateFormat);
            }
        };
    }

    @Bean
    public Converter<String, LocalDateTime> str2localDatetime() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String str) {
                return LocalDateTime.parse(str, dateTimeFormatter);
            }
        };
    }

    @Bean
    @RefreshScope
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            // Local时间处理
            builder
                .serializerByType(LocalDate.class, new LocalDateSerializer(dateFormat))
                .serializerByType(LocalTime.class, new LocalTimeSerializer(timeFormat))
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter))
                .deserializerByType(LocalDate.class, new LocalDateDeserializer(dateFormat))
                .deserializerByType(LocalTime.class, new LocalTimeDeserializer(timeFormat))
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        };
    }
}
