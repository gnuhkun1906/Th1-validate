package ra.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.Product;

@Repository
public interface IRepository extends PagingAndSortingRepository<Product,Long> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
    @Query("select p from Product  as p where p.name like  concat('%',:name,'%') ")
    Page<Product> findByNameProduct(@Param("name") String name, Pageable pageable);
}
