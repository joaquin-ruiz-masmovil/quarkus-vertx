package com.masmovil.phoneapp.domain.event.service;

import com.masmovil.phoneapp.domain.event.DomainEvent;

public interface DomainEventPublisher<T> {

  void publish(DomainEvent<T> domainEvent);

}
