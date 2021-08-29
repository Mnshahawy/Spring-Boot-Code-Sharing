package platform.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.models.CodeSnippet;

import java.util.List;

@Repository
public interface CodeSnippetRepository extends CrudRepository<CodeSnippet, Long> {
    @Query("SELECT C FROM CodeSnippet C ORDER BY C.date DESC")
    List<CodeSnippet> findAllSortByDateDesc(Pageable pageable);
}
