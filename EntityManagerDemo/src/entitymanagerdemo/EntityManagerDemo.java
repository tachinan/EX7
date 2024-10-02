/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitymanagerdemo;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Address;
import model.Customer;

/**
 *
 * @author sarun
 */
public class EntityManagerDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Customer customer = new Customer(1L, "Antony", "Balla", "tballa@mail.com"); 
        //Address address = new Address(1L, "Ritherdon Rd", "London", "8QE", "UK"); 
        //address.setCustomerFk(customer);
        //customer.setAddressId(address); 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        //Customer customer = em.getReference(Customer.class, 1L);
        //Customer customer = em.find(Customer.class, 1L);
        //customer.setFirstname("Prayuth");
        //customer.setAddressId(null);
        //Address address = em.find(Address.class, 1L);
        //address.setCustomerFk(customer);
        //Address address = customer.getAddressId();
        //address.setCity("Gotham");
        //address.setCustomerFk(null);
        EntityManagerDemo demo = new EntityManagerDemo();
         // Create data
        //demo.createData(em);

        // Print all customers
        System.out.println("All Customer");
        demo.printAllCustomer(em);

        // Print customers by city
        Scanner scanner = new Scanner(System.in);
        System.out.println("Customers by city");
        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();
        demo.printCustomerByCity(em, cityName);
        
        em.getTransaction().begin();
        try {
            //em.persist(address);
            //em.persist(customer);
            //em.flush();
            //em.persist(address);
            //em.persist(customer);
            //em.refresh(customer);
            //em.detach(customer);
            //em.persist(customer);
            //em.merge(customer);
            //em.remove(customer);
            //em.persist(customer);
            //em.remove(address);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
      public void createData(EntityManager em) {
        em.getTransaction().begin();

        Customer customer1 = new Customer(1L, "John", "Henry", "jh@mail.com");
        Address address1 = new Address(1L, "123/4 Viphavadee Rd.", "Bangkok", "10900", "TH");
        customer1.setAddressId(address1);
        address1.setCustomerFk(customer1);

        Customer customer2 = new Customer(2L, "Marry", "Jane", "mj@mail.com");
        Address address2 = new Address(2L, "123/5 Viphavadee Rd.", "Bangkok", "10900", "TH");
        customer2.setAddressId(address2);
        address2.setCustomerFk(customer2);

        Customer customer3 = new Customer(3L, "Peter", "Parker", "pp@mail.com");
        Address address3 = new Address(3L, "543/21 Fake Rd.", "Nonthaburi", "20900", "TH");
        customer3.setAddressId(address3);
        address3.setCustomerFk(customer3);

        Customer customer4 = new Customer(4L, "Bruce", "Wayn", "bw@mail.com");
        Address address4 = new Address(4L, "678/90 Unreal Rd.", "Pathumthani", "30500", "TH");
        customer4.setAddressId(address4);
        address4.setCustomerFk(customer4);

        em.persist(customer1);
        em.persist(customer2);
        em.persist(customer3);
        em.persist(customer4);
        em.persist(address1);
        em.persist(address2);
        em.persist(address3);
        em.persist(address4);

        em.getTransaction().commit();
    }
      
     public void printAllCustomer(EntityManager em) {
        List<Customer> customers = em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
        for (Customer customer : customers) {
            Address address = customer.getAddressId();
            System.out.println("------------------------------------------");
            System.out.println("First Name: " + customer.getFirstname());
            System.out.println("Last Name: " + customer.getLastname());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Street: " + address.getStreet());
            System.out.println("City: " + address.getCity());
            System.out.println("Country: " + address.getCountry());
            System.out.println("Zip Code: " + address.getZipcode());
            System.out.println("------------------------------------------");
        }
    }
     
    public void printCustomerByCity(EntityManager em, String cityName) {
        List<Address> addresses = em.createNamedQuery("Address.findByCity", Address.class)
                                    .setParameter("city", cityName).getResultList();
        for (Address address : addresses) {
            Customer customer = address.getCustomerFk();
            System.out.println("------------------------------------------");
            System.out.println("First Name: " + customer.getFirstname());
            System.out.println("Last Name: " + customer.getLastname());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Street: " + address.getStreet());
            System.out.println("City: " + address.getCity());
            System.out.println("Country: " + address.getCountry());
            System.out.println("Zip Code: " + address.getZipcode());
            System.out.println("------------------------------------------");
        }
    }


    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
