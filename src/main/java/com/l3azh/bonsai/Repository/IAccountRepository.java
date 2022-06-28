package com.l3azh.bonsai.Repository;

import com.l3azh.bonsai.Entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, String> {
}
