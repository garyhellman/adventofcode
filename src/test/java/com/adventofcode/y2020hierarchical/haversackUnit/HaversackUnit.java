package com.adventofcode.y2020hierarchical.haversackUnit;

import com.adventofcode.y2020.hierarchical.IElement;
import java.util.Objects;

public class HaversackUnit implements IElement<Long> {

  private final Long id;

  private final String description;

  private final Long parentId;

  public HaversackUnit(Long id, String description) {
    this.id = id;
    this.description = description;
    this.parentId = null;
  }

  public HaversackUnit(Long id, String description, Long parentId) {
    this.id = id;
    this.description = description;
    this.parentId = parentId;
  }

  @Override
  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public Long getParentId() {
    return parentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HaversackUnit that = (HaversackUnit) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(description, that.description) &&
        Objects.equals(parentId, that.parentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, parentId);
  }
}
