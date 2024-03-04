package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor

public class Student {

 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE)
 //@Getter
 @Setter(AccessLevel.NONE)
 private Long id;

 @NotNull(message = "First name can not be null")
 @NotBlank(message = "First name can not be white space")
 @Size(min = 2, max = 25, message = "First name '${validatedValue}' must be between {min} and {max} long")
 @Column(nullable = false, length = 25)
 /*final*/ private String name;

 @Column(nullable = false, length = 25)
 /*final*/ private String lastName;

 //@Column // Optional
 /*final*/ private Integer grade;

 @Column(nullable = false, length = 50, unique = true)
 @Email(message = "Provide valid email")
 /*final*/ private String email; // xxx@yyy.com // sss.com

 /*final*/ private String phoneNumber; // xxx-xxx-xx-xx

 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss", timezone = "Turkey")
 @Setter(AccessLevel.NONE)
 private LocalDateTime createDate = LocalDateTime.now(); //


}