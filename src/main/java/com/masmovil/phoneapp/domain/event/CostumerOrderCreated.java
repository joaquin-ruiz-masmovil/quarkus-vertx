package com.masmovil.phoneapp.domain.event;

import com.masmovil.phoneapp.domain.model.CostumerOrder;

import java.time.Instant;

public class CostumerOrderCreated extends DomainEvent<CostumerOrder> {

  public CostumerOrderCreated(CostumerOrder costumerOrder) {
    this.payload = costumerOrder;
    this.creationInstant = Instant.now();
  }

}
