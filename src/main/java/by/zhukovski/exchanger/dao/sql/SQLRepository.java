package by.zhukovski.exchanger.dao.sql;

import by.zhukovski.exchanger.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SQLRepository extends JpaRepository<Currency, String> {

}
