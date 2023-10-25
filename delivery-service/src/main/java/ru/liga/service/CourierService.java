package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.CourierDTO;
import ru.liga.entities.Courier;
import ru.liga.repositories.CourierRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;

    public List<CourierDTO> dtoList () {
        return StreamSupport.stream(courierRepository.findAll().spliterator(), false).map(this::courierToDTO).collect(Collectors.toList());
    }

    public CourierDTO getDtoById(Integer id) {
        return courierToDTO(getById(id));
    }

    public Courier getById (Integer id){
        return courierRepository.findById(id).orElseThrow(() -> new RuntimeException("Courier with id = " + id + " is not exists"));
    }

    public void convertAndSave (CourierDTO courierDTO) {
        courierRepository.save(courierToEntity(courierDTO));
    }

    public void deleteById (Integer id) {
        courierRepository.deleteById(id);
    }

    public CourierDTO courierToDTO (Courier courier) {
        return new CourierDTO(courier.getId(), courier.getPhone(), courier.getStatus(), courier.getCoordinates());
    }

    public Courier courierToEntity (CourierDTO courierDTO) {
        return new Courier(courierDTO.getPhone(), courierDTO.getStatus(), courierDTO.getCoordinates());
    }
}
