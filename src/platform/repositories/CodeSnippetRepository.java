package platform.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.models.CodeSnippet;

import java.util.List;
import java.util.UUID;

@Repository
public interface CodeSnippetRepository extends CrudRepository<CodeSnippet, UUID> {
    @Query("SELECT C FROM CodeSnippet C ORDER BY C.date DESC")
    List<CodeSnippet> findAllSortByDateDesc(Pageable pageable);

    @Query("SELECT C FROM CodeSnippet C WHERE C.isViewsRestricted = false AND C.isTimeRestricted = false ORDER BY C.date DESC")
    List<CodeSnippet> findAllNonRestrictedSortByDateDesc(Pageable pageable);
}
