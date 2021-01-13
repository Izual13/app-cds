package com.template.hello.dao;

import com.template.hello.converter.JsonConverter;
import lombok.*;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Stub {
    @Id
    private String id;

    @Convert(converter = JsonConverter.class)
    private Map<String, String> jsonString;
}
