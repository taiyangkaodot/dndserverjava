package com.zy.dndserver.entities;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("player")
public class Player {
    @Id
    private Long Uid;

}
