package com.ralitzaraynova.transit.api.assembler;

import com.ralitzaraynova.transit.api.representation.ActionRepresentation;
import com.ralitzaraynova.transit.api.representation.input.ActionInput;
import com.ralitzaraynova.transit.domain.model.Action;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ActionAssembler {

    private final ModelMapper modelMapper;

    public Action toEntity(ActionInput actionInput){
        return modelMapper.map(actionInput, Action.class);
    }

    public ActionRepresentation toModel(Action action){
        return modelMapper.map(action, ActionRepresentation.class);
    }

    public List<ActionRepresentation> toCollectionModel(List<Action> actions){
        return actions.stream().map(this::toModel).toList();
    }

}
