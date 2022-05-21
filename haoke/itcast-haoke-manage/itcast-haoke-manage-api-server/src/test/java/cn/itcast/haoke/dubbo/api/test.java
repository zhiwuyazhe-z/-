package cn.itcast.haoke.dubbo.api;

import cn.itcast.haoke.dubbo.api.mapper.housemapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    private cn.itcast.haoke.dubbo.api.mapper.housemapper housemapper;
    @Test
    public void tes(){
        System.out.println(housemapper.selectList(null));
    }
}
