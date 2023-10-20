package ru.liga.batisMapper;

import org.apache.ibatis.annotations.Mapper;
import ru.liga.entities.MenuItem;
import java.util.List;

@Mapper
public interface MenuItemMapper {
    List<MenuItem> getMenuItems();
}
