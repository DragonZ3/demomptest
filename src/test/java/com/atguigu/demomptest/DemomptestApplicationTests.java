package com.atguigu.demomptest;

import com.atguigu.demomptest.entity.User;
import com.atguigu.demomptest.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class DemomptestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //mp实现复杂的查询
    @Test
    public void testSelect(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("name","xyy");
//        User user = userMapper.selectOne(wrapper);
        wrapper.between("age",18,20);
        Integer integer = userMapper.selectCount(wrapper);

        System.out.println(integer);


    }



    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void getUserById() {
        User user = userMapper.selectById(1529089621399498754L);
        System.out.println(user);
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setAge(20);
        user.setName("甄姬");
        user.setEmail("123@qq.com");
        int i = userMapper.insert(user);

    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1529089621399498754L);
        user.setName("美羊羊");
        int i = userMapper.updateById(user);
    }

    @Test
    public void testOptimisticLocker() {
        User user = userMapper.selectById(1529099670570168321L);
        user.setName("甄姬");
        userMapper.update(user, null);
    }

    //多个id批量查询
    @Test
    public void testSelect1() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    //简单条件查询
    @Test
    public void testSelect2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "xyy");
        map.put("age", 20);
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }

    //分页查询
    @Test
    public void testSelectPage() {
        Page<User> page = new Page(1, 3);
        Page<User> userPage = userMapper.selectPage(page, null);
        //返回对象得到分页所有数据
        long pages = userPage.getPages(); //总页数
        long current = userPage.getCurrent(); //当前页
        List<User> records = userPage.getRecords(); //查询数据集合
        long total = userPage.getTotal(); //总记录数
        boolean hasNext = userPage.hasNext();  //下一页
        boolean hasPrevious = userPage.hasPrevious(); //上一页

        System.out.println(pages);
        System.out.println(current);
        System.out.println(records);
        System.out.println(total);
        System.out.println(hasNext);

    }

    @Test
    public void testDeleteById() {
        int i = userMapper.deleteById(1529106901902606338L);
        System.out.println(i);
    }


}
