package employee.model;

import javax.persistence.*;

/**
 * Created by employee on 12/6/16.
 */
@Entity
@Table(name = "employees", catalog = "employeemanager")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "lastname")
    String lastName;



}
