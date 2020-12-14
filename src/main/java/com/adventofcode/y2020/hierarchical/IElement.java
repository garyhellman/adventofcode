package com.adventofcode.y2020.hierarchical;

public interface IElement<R> {

  R getId();

  R getParentId();

  default boolean checkId(R id) {
    return this.getId().equals(id);
  }
}

