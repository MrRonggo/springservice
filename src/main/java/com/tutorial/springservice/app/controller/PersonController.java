package com.tutorial.springservice.app.controller;

import com.tutorial.springservice.app.presenterImpl.ObjectPresenterImpl;
import com.tutorial.springservice.core.gateway.PersonGateway;
import com.tutorial.springservice.core.request.PersonCreateRequest;
import com.tutorial.springservice.core.service.PersonCreate;
import com.tutorial.springservice.persistence.entity.Person;
import com.tutorial.springservice.shared.LogUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;

import static com.tutorial.springservice.app.constant.PersonRouteConstant.*;

@Slf4j
@RestController
@RequestMapping(path = API_PERSON_PATH)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PersonController {
    private final PersonGateway personGateway;
    private final PersonCreate createService;

    @GetMapping
    public Page<Person> home(HttpServletRequest request,
                             @RequestParam(defaultValue = "0") @Min(0L) Integer page,
                             @RequestParam(defaultValue = "10") @Min(1L) Integer size) throws Exception {
        log.info(LogUtil.endpointCalled(request.getRequestURI()));
        return personGateway.find(PageRequest.of(page, size));
    }

    @GetMapping(value = ID_VAR)
    public Person get(HttpServletRequest request, @PathVariable Long id) throws Exception {
        log.info(LogUtil.endpointCalled(request.getRequestURI(), id));
        return personGateway.findById(id);
    }

    @PostMapping(value = UPDATE_URL)
    public Person update(HttpServletRequest request, @RequestBody Person person) throws Exception {
        log.info(LogUtil.endpointCalled(request.getRequestURI(), person));
        return personGateway.update(person);
    }

    @PostMapping(value = CREATE_URL)
    public Person create(HttpServletRequest request, @RequestBody PersonCreateRequest body) throws Exception {
        log.info(LogUtil.endpointCalled(request.getRequestURI(), body));
        ObjectPresenterImpl<Person> presenter = new ObjectPresenterImpl<>();
        createService.serve(body, presenter);
        return presenter.getResponse();
    }
}
