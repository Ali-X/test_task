package test_task.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_boss_id")
    private Employee boss;

    @OneToMany(mappedBy = "boss", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> workers;
    private String name;
    private BigDecimal salary;

    public Employee(Department department, Employee boss, String name, BigDecimal salary) {
        this.department = department;
        this.boss = boss;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%-5s%-20s%-15s", id, name, salary);
    }
}
