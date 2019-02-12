package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.mapping.PagesMapper;
import blue.bookapp.api.v1.model.PagesDTO;
import blue.bookapp.repositories.PagesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestPagesServiceImpl implements RestPagesService {

    private final PagesRepository pagesRepository;
    private final PagesMapper pagesMapper;

    public RestPagesServiceImpl(PagesRepository pagesRepository, PagesMapper pagesMapper) {
        this.pagesRepository = pagesRepository;
        this.pagesMapper = pagesMapper;
    }

    @Override
    public List<PagesDTO> getPages() {
        List<PagesDTO> pagesDTOS = new ArrayList<>();
        pagesRepository.findAll().forEach(pages ->
        {
            pagesDTOS.add(pagesMapper.pagesToPagesDTO(pages));
        });
        return pagesDTOS;
    }
}
