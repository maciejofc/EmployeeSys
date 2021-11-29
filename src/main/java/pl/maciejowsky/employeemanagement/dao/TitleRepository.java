package pl.maciejowsky.employeemanagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.maciejowsky.employeemanagement.dao.entity.Title;

import java.util.List;

@Repository
public interface TitleRepository extends CrudRepository<Title,Long> {



}
