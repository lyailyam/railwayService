package kz.edu.nu.cs.se.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Objects;

public class PayrollEntityPK implements Serializable {
    private int employeeId;
    @Temporal(TemporalType.DATE) private String paymentDate;

    @Id
    @Column(name = "employee_id", nullable = false)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Id
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
        PayrollEntityPK that = (PayrollEntityPK) o;
        return employeeId == that.employeeId &&
                paymentDate.equals(that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, paymentDate);
    }
}
