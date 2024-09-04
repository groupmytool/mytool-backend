package group.mytool.backend.core.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

import static group.mytool.backend.core.util.Constant.NORM_DATETIME_PATTERN;
import static group.mytool.backend.core.util.Constant.NORM_DATE_PATTERN;
import static group.mytool.backend.core.util.Constant.NORM_TIME_PATTERN;

/**
 * Jackson 日期格式化配置
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Configuration
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JackJsonConfiguration {

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper()
        .setLocale(Locale.CHINA)
        .setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
        .registerModule(javaTimeModule())
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .findAndRegisterModules();
  }


  private JavaTimeModule javaTimeModule() {
    JavaTimeModule module = new JavaTimeModule();
    module.addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(NORM_DATETIME_PATTERN)));
    module.addSerializer(new LocalTimeSerializer(DateTimeFormatter.ofPattern(NORM_TIME_PATTERN)));
    module.addSerializer(new LocalDateSerializer(DateTimeFormatter.ofPattern(NORM_DATE_PATTERN)));
    module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(NORM_DATETIME_PATTERN)));
    module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(NORM_TIME_PATTERN)));
    module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(NORM_DATE_PATTERN)));
    return module;
  }


}
