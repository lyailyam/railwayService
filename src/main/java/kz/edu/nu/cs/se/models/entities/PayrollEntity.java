package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payroll", schema = "railwaysdb", catalog = "")
public class PayrollEntity {
    private int employeeId;
    private Double paymentAmount;
    @Temporal(TemporalType.DATE) private String paymentDate;

    @Id
    @Column(name = "employee_id", nullable = false)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "payment_amount", nullable = false, precision = 0)
    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Basic
    @Column(name = "payment_date", nullable = false)
    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayrollEntity that = (PayrollEntity) o;
        return employeeId == that.employeeId &&
                paymentAmount.equals(that.paymentAmount) &&
                paymentDate.equals(that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, paymentAmount, paymentDate);
    }

    @Override
    public String toString() {
        return "PayrollEntity{" +
                "employeeId=" + employeeId +
                ", paymentAmount=" + paymentAmount +
                ", paymentDate='" + paymentDate + '\'' +
                '}';
    }
}
