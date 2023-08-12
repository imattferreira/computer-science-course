package org.nfe.repositories;

import org.nfe.errors.EntityNotFoundException;
import org.nfe.implementations.*;
import org.nfe.interfaces.RepositoryInterface;

import java.util.ArrayList;
import java.util.Comparator;

public class NfesRepository implements RepositoryInterface<Nfe> {
  static ArrayList<Nfe> repository = new ArrayList<Nfe>();

  public void populate(int qdd) {
    for (int i = 0; i < qdd; i++) {
      ArrayList<Product> products = new ArrayList<Product>();
      ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

      Vehicle vehicle = new Vehicle(
              "plate " + i,
              "uf " + i
      );

      vehicles.add(vehicle);

      Address address = new Address(
              "12342324" + i,
              "312312563" + i,
              "city " + i,
              i,
              "road " + i,
              "state " + i
      );
      Phone phone = new Phone(
              i * 10,
              534 * i
      );
      ShippingCompany shippingCompany = new ShippingCompany(
              "company " + i,
              "state " + i,
              i * 1200,
              vehicles,
              address,
              phone
      );

      Product product = new Product();
      BusinessClient businessClient = new BusinessClient(
              "company " + i,
              "cnpj " + i,
              "state " + i,
              address,
              phone
      );
      PrivateIndividualClient privateIndividualClient = new PrivateIndividualClient(
              "name " + i,
              "cpf " + i,
              address,
              phone
      );
      Supplier supplier = new Supplier(
              "cnpj " + i,
              "company name" + i,
              "state " + i,
              address,
              phone
      );
      Shipping shipping = new Shipping(
              shippingCompany,
              i * 654,
              i * 65,
              businessClient,
              privateIndividualClient,
              supplier,
              vehicle
      );
      BankVoucher bankVoucher = new BankVoucher(
              i * 123,
              "date " + i,
              "accessKey " + i,
              "protocol " + i,
              "expirationDate " + i,
              i * 987,
              i * 1245
      );

      products.add(product);

      Nfe data = new Nfe(
              i,
              products,
              shipping,
              address,
              businessClient,
              privateIndividualClient,
              bankVoucher,
              supplier
      );

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
    for (int i = 0; i < repository.size(); i++) {
      if (repository.get(i).getPrivateIndividualClient().getCpf().equals(cnpjOrCpf) || repository.get(i).getBusinessClient().getCnpj().equals(cnpjOrCpf)) {
        return repository.get(i);
      }
    }

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
