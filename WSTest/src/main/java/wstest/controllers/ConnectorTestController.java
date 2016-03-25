package wstest.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import repo.ConnectorTest;
import wstest.AbstractRestHandler;
import wstest.services.ConnectorTestService;


@RestController
@RequestMapping(value = "/connectortests")
@Api(value = "Connector Tests", description = "Audit Changes API")
public class ConnectorTestController extends AbstractRestHandler {

	@Autowired
	protected ConnectorTestService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Deletes a data object.", notes = "You have to provide a valid data object id.")
	public @ResponseBody void delete(
			@ApiParam(value = "The ID of the data object.", required = true) @PathVariable("id") Long id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		checkResourceFound(this.service.get(id));
		this.service.delete(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a single data object.", notes = "You have to provide a valid data object id.")
	public @ResponseBody ConnectorTest get(
			@ApiParam(value = "The ID of the workflow system.", required = true) @PathVariable("id") Long id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectorTest dataObject = this.service.get(id);
		checkResourceFound(dataObject);
		// todo: http://goo.gl/6iNAkz
		return dataObject;
}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update a data object.", notes = "You have to provide a valid data object id.")
	public @ResponseBody ConnectorTest update(
			@ApiParam(value = "The ID of the data object.", required = true) @PathVariable("id") Long id,
			@RequestBody ConnectorTest dataObject, HttpServletRequest request, HttpServletResponse response) {
		ConnectorTest dataObjectFound = (ConnectorTest)this.service.get(id);
		checkResourceFound(dataObjectFound);
		return (ConnectorTest)this.service.update(dataObject);

	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create a data object.", notes = "Returns the URL of the new resource in the Location header.")
	public void create(@RequestBody ConnectorTest data, HttpServletRequest request, HttpServletResponse response) {
		ConnectorTest created = (ConnectorTest) this.service.create(data);
		response.setHeader("Location", request.getRequestURL().append("/").append(created.getId()).toString());
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a paged list of all data.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public @ResponseBody Page<ConnectorTest> getAllPaged(
			@ApiParam(value = "The page number (zero-based)", required = true) @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
			@ApiParam(value = "The page size", required = true) @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
			HttpServletRequest request, HttpServletResponse response) {
		return (Page<ConnectorTest>)this.service.getAllPaged(page, size);
	}
}
