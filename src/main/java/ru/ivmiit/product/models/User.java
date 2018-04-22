package ru.ivmiit.product.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.*;
import java.sql.ResultSet;


/**
 * 04.04.2018
 *
 * @author Robert Bagramov.
 */
@Entity
@Table(name = "kfu_user")
@Data
@Builder
public class User {
    public static RowMapper<User> userRowMapper = (ResultSet rs, int rowNum) ->
            User.builder()
                    .id(rs.getInt("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .build();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String login;
    @Column
    private String password;

}
