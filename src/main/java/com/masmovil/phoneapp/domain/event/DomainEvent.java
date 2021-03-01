package com.masmovil.phoneapp.domain.event;

import java.time.Instant;

public abstract class DomainEvent<T> {

  protected T payload;
  protected Instant creationInstant;

  public T getPayload() {
    return payload;
  }

  public Instant getCreationInstant() {
    return creationInstant;
  }

}
