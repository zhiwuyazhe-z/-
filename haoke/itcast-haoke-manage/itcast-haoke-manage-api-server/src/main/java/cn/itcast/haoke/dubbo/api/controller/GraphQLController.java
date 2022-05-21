package cn.itcast.haoke.dubbo.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import graphql.GraphQL;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("graphql")
@CrossOrigin
public class GraphQLController {
    @Autowired
    private GraphQL graphQL;
    /*@GetMapping
    @ResponseBody
    public Object graphql(@RequestParam("query") String query)throws Exception{

        return this.graphQL.execute(query).toSpecification();
    }*/



    private static final ObjectMapper MAPPER = new ObjectMapper();


    /**
     * 实现GraphQL查询
     *
     * @param query
     * @return
     */
    @GetMapping
    @ResponseBody
    public Map<String, Object> query(@RequestParam("query") String query,@RequestParam(value = "variables",required = false) String v,@RequestParam(value = "operationName",required = false) String o)throws Exception {
        Map<String,Object>var=MAPPER.readValue(v,MAPPER.getTypeFactory().constructMapType(HashMap.class,String.class,Object.class));
        Integer temp=(Integer) var.get("id");
        if(temp!=null){
            Long l=temp.longValue();
            var.replace("id",l);
        }

        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).variables(var).operationName(o).build();
        return this.graphQL.execute(executionInput ).toSpecification();
    }

    @PostMapping
    @ResponseBody
    public Map<String, Object> postQuery(@RequestBody Map<String,Object>param) throws Exception{
            String query=(String) param.get("query");
            Map m=(Map)param.get("variables");
            String o=(String)param.get("operationName");

            ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).variables(m).operationName(o).build();
           return this.graphQL.execute(executionInput).toSpecification();
    }
}
