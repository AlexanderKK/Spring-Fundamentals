package org.softuni.mobilele.repository;

import org.softuni.mobilele.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findByName(String brandName);

    @EntityGraph(
            value = "brandWithModels",
            attributePaths = "models")
    @Query("SELECT b FROM BrandEntity b")
//    @Query("SELECT b FROM BrandEntity b JOIN FETCH b.models")
    List<BrandEntity> getAllBrands();

}
