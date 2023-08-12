package org.nfe.repositories;

import org.nfe.errors.EntityNotFoundException;
import org.nfe.implementations.Nfe;
import org.nfe.interfaces.RepositoryInterface;

import java.util.ArrayList;
import java.util.Comparator;

public class NfesRepository implements RepositoryInterface<Nfe> {
  static ArrayList<Nfe> repository = new ArrayList<Nfe>();

  public void populate(int qdd) {
    for (int i = 0; i < qdd; i++) {
      Nfe data = new Nfe();

      repository.add(data);
    }
  }

  public void create(Nfe data) {
    repository.add(data);
  }

  public Nfe find(int id) throws EntityNotFoundException {
    for (int i = 0; i < repository.size(); i++) {
      if (repository.get(i).getId() == id) {
        return repository.get(i);
      }
    }

    throw new EntityNotFoundException("Nfe not found");
  }

  public Nfe findByClientCompanyName(String companyName) throws EntityNotFoundException {
    for (int i = 0; i < repository.size(); i++) {
      if (repository.get(i).getBusinessClient().getCompanyName().equals(companyName)) {
        return repository.get(i);
      }
    }

    throw new EntityNotFoundException("Nfe not found");
  }

  public Nfe findByClientCnpjOrCpf(String cnpjOrCpf) throws EntityNotFoundException {
//    for (int i = 0; i < repository.size(); i++) {
//      if (repository.get(i).companyName.equals(companyName)) {
//        return repository.get(i);
//      }
//    }

    throw new EntityNotFoundException("Nfe not found");
  }

  public Nfe findByTotalValue(double total) throws EntityNotFoundException {
    for (int i = 0; i < repository.size(); i++) {
      if (repository.get(i).getBankVoucher().getTotal() == total) {
        return repository.get(i);
      }
    }

    throw new EntityNotFoundException("Nfe not found");
  }

  public ArrayList<Nfe> findRangeByNumber(int number, int qty) {
    ArrayList<Nfe> sortedNfes = repository;

    sortedNfes.sort(Comparator.comparing(Nfe::getId));

    ArrayList<Nfe> result = new ArrayList<Nfe>();

    for (int i = 0; i < sortedNfes.size(); i++) {
      if (sortedNfes.get(i).getId() >= number && result.size() < qty) {
        result.add(sortedNfes.get(i));
      }
    }

    return result;
  }

  public void update(int id, Nfe data) {
    int position = -1;

    for (int i = 0; i < repository.size(); i++) {
      if (repository.get(i).getId() == id) {
        position = i;
      }
    }

    if (position == -1) {
      return;
    }

    repository.set(position, data);
  }

  public void exclude(int id) {
    ArrayList<Nfe> newRepo = new ArrayList<Nfe>();

    for (int i = 0; i < repository.size(); i++) {
      if (repository.get(i).getId() != id) {
        newRepo.add(repository.get(i));
      }
    }

    repository = newRepo;
  }
}
