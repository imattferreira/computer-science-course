package org.nfe.interfaces;

import org.nfe.errors.EntityNotFoundException;

import java.util.ArrayList;

public interface RepositoryInterface<Entity> {
  public void populate(int qdd);

  public void create(Entity data);

  public Entity find(int id) throws EntityNotFoundException;
  public void update(int id, Entity data);
  public void exclude(int id);
}
