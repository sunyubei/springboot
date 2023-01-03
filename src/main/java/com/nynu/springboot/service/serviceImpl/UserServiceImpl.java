package com.nynu.springboot.service.serviceImpl;

import com.nynu.springboot.dao.UserMapper;
import com.nynu.springboot.entity.User;
import com.nynu.springboot.service.UserService;
import com.nynu.springboot.utils.MathUtil;
import com.nynu.springboot.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    //检测用户是否存在，如果不存在返回true，存在返回false
    @Override
    public boolean checkUser(User user) {

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", user.getUserName());
        User entity = userMapper.selectOneByExample(example);
        if (entity != null) {
            return false;
        }
        return true;

    }

    @Override
    public void register(User user) {
        //为用户生产一个长度为20的随机字符串的主键id
        user.setId(MathUtil.createId());
        //对密码进行加密
        user.setPassWord(new BCryptPasswordEncoder().encode(user.getPassWord()));
        userMapper.insertSelective(user);
    }

    @Override
    public boolean login(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", user.getUserName());
        User entity = userMapper.selectOneByExample(example);
        //如果用户存在返回true
        return entity == null ? false : (new BCryptPasswordEncoder().matches(user.getPassWord(), entity.getPassWord()));
    }

    @Override
    public User getUser(String userName) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName);
        User user = userMapper.selectOneByExample(example);
        return user;
    }

    @Override
    public String getAndSetToken(User user) {
        String token = TokenUtil.creatToken(user.getUserName(), user.getPassWord());
        redisTemplate.opsForValue().set(user.getUserName(), token);
        return token;
    }

    /**
     * @Author: 王纪勇
     * @Date: 2022/4/8 15:17
     * @Description: 分页查询
     */
    @Override
    public List<User> getPageList(User user) {
        List<User> list = userMapper.getPageList(user);
        return list;
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
//    @Transactional
    public void executerMethod1() {
        System.err.println("节点1开始");
        User user = new User("1","王大锤","hahaha");
        userMapper.insert(user);

        System.err.println("节点1结束");
    }

    @Override
//    @Transactional
    public void executerMethod2() {
        System.err.println("节点2开始");
        User user = new User("2","曾小贤","hahaha");
        userMapper.insert(user);
        int i = 1/0;
        System.err.println("节点2结束");
    }

    @Override
//    @Transactional
    public void executerMethod3() {
        System.err.println("节点3开始");
        User user = new User("3","吕小布","hahaha");
        userMapper.insert(user);
        System.err.println("节点3结束");
    }


}
