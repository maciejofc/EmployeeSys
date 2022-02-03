package pl.maciejowsky.employeemanagement.dto;

import org.mapstruct.Mapper;

import pl.maciejowsky.employeemanagement.dao.entity.Title;

@Mapper(componentModel = "spring")
public interface TitleMapper {

    TitleDTO toDto(Title title);
    Title toModel(TitleDTO titleDTO);
}
