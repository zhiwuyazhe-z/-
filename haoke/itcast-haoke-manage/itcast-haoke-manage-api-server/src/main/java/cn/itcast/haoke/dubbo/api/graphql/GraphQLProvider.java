package cn.itcast.haoke.dubbo.api.graphql;

import cn.itcast.haoke.dubbo.api.controller.HouseResourcesController;
import cn.itcast.haoke.dubbo.api.mapper.housemapper;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.apache.dubbo.common.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class GraphQLProvider {
    @Autowired
    private List<MyDataFetcher> myDataFetcherList;

    private GraphQL graphQL;
@Autowired
    private cn.itcast.haoke.dubbo.api.mapper.housemapper housemapper;
    @PostConstruct
    public void init()throws Exception{
        InputStream inputStream=GraphQLProvider.class.getClassLoader().getResourceAsStream("haoke.graphqls");
        StringBuffer buffer=new StringBuffer();
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String str=null;
        while((str=bufferedReader.readLine())!=null){
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        String c=buffer.toString();
        GraphQLSchema graphQLSchema= buildSchema(c);
        this.graphQL=GraphQL.newGraphQL(graphQLSchema).build();
    }
    private GraphQLSchema buildSchema(String str){
        TypeDefinitionRegistry typeDefinitionRegistry=new SchemaParser().parse(str);
        RuntimeWiring runtimeWiring=buildWiring();
        SchemaGenerator schemaGenerator=new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);
    }
    private RuntimeWiring buildWiring(){
//        return RuntimeWiring.newRuntimeWiring().type("HaokeQuery",builder -> builder.dataFetcher("HouseResources",dataFetchingEnvironment -> {
//            Long id=dataFetchingEnvironment.getArgument("id");
//            return this.housemapper.selectById(id);
//        })).build();
        return RuntimeWiring.newRuntimeWiring().type("HaokeQuery",builder -> {
            for(MyDataFetcher myDataFetcher:myDataFetcherList){
                builder.dataFetcher(myDataFetcher.fieldName(),dataFetchingEnvironment -> myDataFetcher.dataFetcher(dataFetchingEnvironment));
            }
            return builder;
        }).build();
    }
    @Bean
    public GraphQL graphQL(){
        return this.graphQL;
    }
}
