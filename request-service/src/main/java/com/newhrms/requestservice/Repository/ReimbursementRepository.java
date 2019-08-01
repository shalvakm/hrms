package com.newhrms.requestservice.Repository;

import com.newhrms.requestservice.Model.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement,Long> {
}
