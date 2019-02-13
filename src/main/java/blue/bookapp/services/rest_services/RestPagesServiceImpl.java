package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.mapping.PagesMapper;
import blue.bookapp.api.v1.model.PagesDTO;
import blue.bookapp.repositories.PagesRepository;
import blue.bookapp.services.PagesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestPagesServiceImpl implements RestPagesService {

    private final PagesRepository pagesRepository;
    private final PagesMapper pagesMapper;
    private final PagesService pagesService;

    public RestPagesServiceImpl(PagesRepository pagesRepository, PagesMapper pagesMapper, PagesService pagesService) {
        this.pagesRepository = pagesRepository;
        this.pagesMapper = pagesMapper;
        this.pagesService = pagesService;
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

    @Override
    public PagesDTO getPagesById(Long id) {
        if (pagesRepository.findById(id).isPresent())
        {
            return pagesMapper.pagesToPagesDTO(pagesRepository.findById(id).get());
        } else return null;
    }

    @Override
    public String deletePagesById(Long id) {
        if (pagesRepository.findById(id).isPresent())
        {
            PagesDTO pagesDTO = pagesMapper.pagesToPagesDTO(pagesRepository.findById(id).get());
            pagesService.removeById(pagesDTO.getBookId(), pagesDTO.getId());
            return "Page " + id + " Deleted.\n" +
                    "Page Number: " + pagesDTO.getPage() +
                    "\nBook Title: " + pagesDTO.getBookTitle();
        } else return "Page not found.";
    }
}
