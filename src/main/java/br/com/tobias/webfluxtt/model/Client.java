package br.com.tobias.webfluxtt.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "clients")
public class Client {

    @Id
    private String id = new ObjectId().toString();
    private String name;
    private Integer age;
    @Indexed(unique = true, background = true)
    private String email;

    public Client(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}