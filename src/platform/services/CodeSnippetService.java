package platform.services;

import org.springframework.stereotype.Service;
import platform.models.CodeSnippet;
import platform.util.Utils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CodeSnippetService {
    private final SortedMap<Integer, CodeSnippet> codeSnippetMap;

    public CodeSnippetService(){
        this.codeSnippetMap = new TreeMap<>();
    }

    public CodeSnippet getCodeSnippet(int id) {
        return codeSnippetMap.get(id);
    }

    public void addCodeSnippet(CodeSnippet codeSnippet, int id) {
        codeSnippet.updateDate();
        this.codeSnippetMap.put(id,codeSnippet);
    }

    public List<CodeSnippet> getLatestTen(){
        List<CodeSnippet> list = new ArrayList<>();
        int n = codeSnippetMap.size();
        int count = 0;
        for(int i=n; i > 0; i--){
            list.add(codeSnippetMap.get(i));
            if(++count == 10)
                break;
        }
        return list;
    }
    public int getNextId(){
        return codeSnippetMap.size() + 1;
    }
}
