package com.example.todo.model;


import com.example.todo.enums.InvitationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invitation {

    @Id
    @SequenceGenerator(sequenceName = "invitation_sequence",allocationSize = 1,name = "invitation_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invitation_sequence")
    private Long id;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private InvitationStatus status;

    private LocalDateTime dateCreated;
    private LocalDateTime expiryDate;

    //acceptance date
    //rejection date
}
