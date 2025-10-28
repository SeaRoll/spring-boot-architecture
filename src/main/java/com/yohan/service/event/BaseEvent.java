package com.yohan.service.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = BaseEvent.NewRandomEvent.class, name = "random"),
})
public interface BaseEvent {
  public record NewRandomEvent(Integer hello) implements BaseEvent {}
}
