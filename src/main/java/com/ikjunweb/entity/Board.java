package com.ikjunweb.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Entity
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
