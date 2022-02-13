package pl.maciejowsky.employeemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.maciejowsky.employeemanagement.dao.entity.Title;

import java.util.List;


@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {


    List<Title> findAllByEmployeeIdIn(List<Long> ids);
}
