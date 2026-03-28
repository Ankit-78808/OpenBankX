package com.cts.openbankx.repository;

import com.cts.openbankx.model.Consent;
import com.cts.openbankx.enums.ConsentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, Long> {
    List<Consent> findByUser_UserId(Long userId);
    List<Consent> findByTppApp_TppAppId(Long tppAppId);
    List<Consent> findByStatus(ConsentStatus status);
    List<Consent> findByUser_UserIdAndTppApp_TppAppId(Long userId, Long tppAppId);
}
