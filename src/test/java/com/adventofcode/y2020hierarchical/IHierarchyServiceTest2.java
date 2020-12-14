package com.adventofcode.y2020hierarchical;

import com.adventofcode.y2020.hierarchical.HierarchyService;
import com.adventofcode.y2020.hierarchical.IElement;
import com.adventofcode.y2020.hierarchical.IHierarchyService;
import com.adventofcode.y2020.hierarchical.TreeNode;
import com.adventofcode.y2020hierarchical.haversackUnit.HaversackUnit;
import com.adventofcode.y2020hierarchical.haversackUnit.HaversackUnitGenerator;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class IHierarchyServiceTest2 {

  private IHierarchyService<HaversackUnit, Long> service;
  private HaversackUnitGenerator haversackUnitGenerator;

  @BeforeEach
  void setUp() {
    this.haversackUnitGenerator = new HaversackUnitGenerator();
//    this.service = new HierarchyService<>(haversackUnitGenerator.generate());
  }

//  @RepeatedTest(10)
//  public void findAll() {
//    final Collection<HaversackUnit> all = this.service.findAll();
//
//    assertNotNull(all);
//    assertFalse(all.isEmpty());
//  }
//
//  @RepeatedTest(10)
//  public void findById() {
//    final Long id = 1L;
//    final HaversackUnit found = this.service.findById(id);
//
//    assertNotNull(found);
//    assertEquals(id, found.getId());
//  }
//
//  @RepeatedTest(10)
//  void getAncestors() {
//    final HaversackUnit element = this.haversackUnitGenerator.getRandomElement(this.service.findAll());
//    final Collection<HaversackUnit> ancestors = this.service.getAncestors(element);
//    assertTrue(ancestors.stream().allMatch(ancestor -> this.service.isAncestral(ancestor, element)));
//  }
//
//  @RepeatedTest(10)
//  public void getDescendants() {
//    final HaversackUnit ancestral = this.haversackUnitGenerator.getRandomElement(this.service.findAll());
//    final Collection<HaversackUnit> descendants = this.service.getDescendants(ancestral);
//    assertTrue(descendants.stream().allMatch(element -> this.service.isDescendant(element, ancestral)));
//  }
//
//  @RepeatedTest(10)
//  public void getTree() {
//    final Collection<TreeNode<HaversackUnit>> trees = this.service.getRoots().stream()
//        .map(this.service::getTree)
//        .collect(Collectors.toSet());
//
//    assertNotNull(trees);
//    assertFalse(trees.isEmpty());
//  }
//
//  @RepeatedTest(10)
//  public void getRoots() {
//    final Collection<HaversackUnit> roots = this.service.getRoots();
//
//    assertNotNull(roots);
//    assertFalse(roots.isEmpty());
//    assertTrue(roots.stream()
//        .map(IElement::getParentId)
//        .allMatch(Objects::isNull));
//  }
//
//  @RepeatedTest(10)
//  public void isRoot() {
//    final HaversackUnit element = Mockito.mock(HaversackUnit.class);
//
//    when(element.getParentId()).thenReturn(null);
//    assertTrue(this.service.isRoot(element));
//
//    when(element.getParentId()).thenReturn(1L);
//    assertFalse(this.service.isRoot(element));
//  }
}
