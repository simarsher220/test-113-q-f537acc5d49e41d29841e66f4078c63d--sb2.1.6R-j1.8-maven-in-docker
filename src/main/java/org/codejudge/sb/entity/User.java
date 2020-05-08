package org.codejudge.sb.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.codejudge.sb.model.QuizSolution;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@ToString
@Getter
@Setter
@NoArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "auth_token", unique = true)
    private String authToken;

    @Column(name = "options", columnDefinition = "json")
    @Type(type = "jsonb")
    private QuizSolution results;

    @Column(name = "score")
    private Integer score;
}
