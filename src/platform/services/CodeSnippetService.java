package platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import platform.models.CodeSnippet;
import platform.repositories.CodeSnippetRepository;
import platform.util.Utils;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CodeSnippetService {
    private final CodeSnippetRepository codeSnippetRepository;

    @Autowired
    public CodeSnippetService(CodeSnippetRepository codeSnippetRepository){
        this.codeSnippetRepository = codeSnippetRepository;
    }

    public Optional<CodeSnippet> getCodeSnippetById(Long id) {
        return codeSnippetRepository.findById(id);
    }

    public CodeSnippet addCodeSnippet(CodeSnippet codeSnippet) {
        return codeSnippetRepository.save(codeSnippet);
    }

    public List<CodeSnippet> getLatestTen(){
        return codeSnippetRepository.findAllSortByDateDesc(PageRequest.of(0,10));
    }
}
