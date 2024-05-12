package com.sia.gentest.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.sia.gentest.util.jackson.JsonViews;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
