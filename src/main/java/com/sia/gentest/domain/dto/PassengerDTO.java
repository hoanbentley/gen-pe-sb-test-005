package com.sia.gentest.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.sia.gentest.util.jackson.JsonViews;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PassengerDTO {

    @JsonView({ JsonViews.Update.class, JsonViews.Read.class })
    private Long id;

    @JsonView({ JsonViews.Create.class, JsonViews.Update.class, JsonViews.Read.class })
    private String firstName;

    @JsonView({ JsonViews.Create.class, JsonViews.Update.class, JsonViews.Read.class })
    private int age;

    @JsonView({ JsonViews.Create.class, JsonViews.Update.class, JsonViews.Read.class })
    private boolean gender;


}
