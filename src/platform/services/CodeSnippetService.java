package platform.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import platform.models.CodeSnippet;
import platform.repositories.CodeSnippetRepository;
import java.util.*;

@Service
public class CodeSnippetService {
    private final CodeSnippetRepository codeSnippetRepository;

    @Autowired
    public CodeSnippetService(CodeSnippetRepository codeSnippetRepository){
        this.codeSnippetRepository = codeSnippetRepository;
    }

    public Optional<CodeSnippet> getCodeSnippetById(String id) {
        Optional<CodeSnippet> optional = codeSnippetRepository.findById(UUID.fromString(id));
        if(optional.isPresent()){
            CodeSnippet codeSnippet = optional.get();
            if(codeSnippet.isDeletionRequiredAfterRestrictionsUpdate()){
                //Here we should remove the snippet
                codeSnippetRepository.delete(codeSnippet);
                //If we reached expiration time, we return 404
                if(codeSnippet.isExpired())
                    return Optional.empty();
            }else{
                //Update the snippet
                codeSnippetRepository.save(codeSnippet);
            }
        }
        return optional;
    }

    public CodeSnippet addCodeSnippet(CodeSnippet codeSnippet) {
        codeSnippet.setRestrictions();
        return codeSnippetRepository.save(codeSnippet);
    }

    public List<CodeSnippet> getLatestTen(){
        return codeSnippetRepository.findAllNonRestrictedSortByDateDesc(PageRequest.of(0,10));
    }
}
