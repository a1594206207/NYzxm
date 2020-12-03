package com.nzxmmp.nzxm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nzxmmp.nzxm.Mapper.NzxmbdMapping;
import com.nzxmmp.nzxm.Mapper.OldnzxmbdMapper;
import com.nzxmmp.nzxm.Service.ServiceImpl.NzxmqxServiceImpl;
import com.nzxmmp.nzxm.Service.ServiceImpl.before.NzxmService;
import com.nzxmmp.nzxm.Service.ServiceImpl.SeeState;
import com.nzxmmp.nzxm.entity.*;
import com.nzxmmp.nzxm.entity.before.Oldnzxmbd;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Find;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalApplicationListener;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class text2 extends NzxmApplicationTests{


    @Autowired
    @Qualifier("NzxmServiceImpl2")
    private NzxmService nzxmService;
    @Autowired
    private NzxmbdMapping nzxmbdMapping;

    @Test
    public void testselectall(){

        IPage<Nzxmbd> nzxmbdIPage = nzxmService.pageFind(2);

        List<Nzxmbd> records = nzxmbdIPage.getRecords();
        records.forEach(System.out::println);

    }

    @Test
    public void insert(){
        Nzxmbd nzxmbd = new Nzxmbd();
        nzxmbd.setXmmc("打滴滴");
        nzxmbd.setTzfmc("和平路");

        String insert = nzxmService.insert(nzxmbd);
        System.out.println(insert);

    }

    @Test
    public void move(){
        boolean move = nzxmService.move(29);
        System.out.println(move);
    }
    @Test
    public void insert2(){
        Nzxmbd nzxmbd = new Nzxmbd();
        nzxmbd.setXmmc("沐恩");
        nzxmbd.setTzfmc("和平路");
        int insert = nzxmbdMapping.insert(nzxmbd);


        System.out.println(insert);
    }

    /**
     * 时间格式转换
     */
    @Test
    public void yysd(){

        Xserentity xserentity = new Xserentity();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        Date parse = null;
        try {
            parse = simpleDateFormat.parse("2020/12/1");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        xserentity.setTime(parse);
        System.out.println(simpleDateFormat.format(xserentity.getTime()));


    }

    @Test
    public void Jsont() throws JsonProcessingException {
        SearchNzxmbd searchNzxmbd = new SearchNzxmbd();
        searchNzxmbd.setXmmc("一");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(searchNzxmbd);
        System.out.println(s);

    }
    @Test
    public void asda() throws Exception{

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date create = simpleDateFormat.parse("2020/11/23");
        Date endt = simpleDateFormat.parse("2020/11/25");

        SearchNzxmbd searchNzxmbd = new SearchNzxmbd();
        searchNzxmbd.setEndtime(endt);
        searchNzxmbd.setCreattime(create);
        IPage<SearchNzxmbd> search = nzxmService.search(searchNzxmbd, 1);
        List<SearchNzxmbd> records = search.getRecords();
        records.forEach(System.out::println);
        System.out.println("==============================");

    }

    @Test
    public void asduqowe(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
        Date create=null;
        Date endt=null;
        try {
            create =simpleDateFormat.parse("2020/11/22");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
             endt= simpleDateFormat.parse("2021/1/1");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SearchNzxmbd searchNzxmbd = new SearchNzxmbd();
        searchNzxmbd.setCreattime(create);
        searchNzxmbd.setEndtime(endt);
        IPage<SearchNzxmbd> result=nzxmService.search(searchNzxmbd,1);


    }


    @Autowired
    private SeeState seeState;

    @Test
    public  void asasda(){
//
//        TheState state = seeState.state();
//        System.out.println(state.toString());
    }
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void ess(){

        redisTemplate.opsForValue().set("redistexe","textesdf");
        Set<String> keys = redisTemplate.keys("*");
        System.out.println("----keys---");
        System.out.println(keys.toString());
        System.out.println("----keys---");

        Jedis jedis = new Jedis("localhost", 6379);
        String select = jedis.select(0);
        String set = jedis.set("sda", "qweqwr");
//        String s = jedis.flushAll();
        System.out.println("-----jedis---");
        System.out.println("select:"+select);
        System.out.println("set:"+set);
        System.out.println("s:");
        System.out.println("-----jedis---");



    }

    @Autowired
    private OldnzxmbdMapper oldnzxmbdMapper;
    @Test
    public void uuidtest(){
        UUID uuid = UUID.randomUUID();
        String strId=uuid.toString().replaceAll("-","");//将uuid值的“-”改为“”
        Oldnzxmbd oldnzxmbd = new Oldnzxmbd();//实体类对象
        oldnzxmbd.setId(strId);
        oldnzxmbdMapper.insert(oldnzxmbd);



    }

    @Test
    public void IPageto(){

        Page<Nzxmbd> nzxmbdPage = new Page<>(1, 20);

        IPage<Nzxmbd> iPage = nzxmbdMapping.selectPage(nzxmbdPage,null);

        List<Nzxmbd> nzxmbds = iPage.getRecords();
        List<FindAll> finds=new ArrayList<>();
        for (Nzxmbd nzxmbd : nzxmbds) {
            FindAll findAll = new FindAll();
            BeanUtils.copyProperties(nzxmbd,findAll);
            finds.add(findAll);
        }
        NzxmqxServiceImpl nzxmqxService = new NzxmqxServiceImpl();

    }



    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
    protected boolean sw1(Oldnzxmbd oldnzxmbd){

            QueryWrapper queryWrapper=new QueryWrapper<Oldnzxmbd>();
            queryWrapper.eq("xmmc",oldnzxmbd.getXmmc());
            Oldnzxmbd oldnzxmbd2 = oldnzxmbdMapper.selectOne(queryWrapper);
            if(!(oldnzxmbd2==null)) {
                return false;
            }

            int insert = oldnzxmbdMapper.insert(oldnzxmbd);

            if (!(insert>0)){
               return false;
            }

        return  true;
    }


    @Transactional(rollbackFor = Exception.class)
    protected  boolean sw2(List<Oldnzxmbd> oldnzxmbds){
        try{
            for (Oldnzxmbd oldnzxmbd : oldnzxmbds) {

                QueryWrapper queryWrapper=new QueryWrapper<Oldnzxmbd>();
                queryWrapper.eq("xmmc",oldnzxmbd.getXmmc());
                Oldnzxmbd oldnzxmbd2 = oldnzxmbdMapper.selectOne(queryWrapper);
                if(!(oldnzxmbd2==null)) {
                    return false;
                }
                int insert = oldnzxmbdMapper.insert(oldnzxmbd);
                if (!(insert>0)){
                    throw new RuntimeException();
                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }
    @Test
    public void sw3(){
        List<Oldnzxmbd> oldnzxmbds = new ArrayList<Oldnzxmbd>();
        Oldnzxmbd oldnzxmbd = new Oldnzxmbd();
        Oldnzxmbd oldnzxmbd2 = new Oldnzxmbd();
        Oldnzxmbd oldnzxmbd3 = new Oldnzxmbd();

        oldnzxmbd.setXmmc("事务测试6");
        oldnzxmbd2.setXmmc("事务测试2");
        oldnzxmbd3.setXmmc("事务测试");

        oldnzxmbds.add(oldnzxmbd);
        oldnzxmbds.add(oldnzxmbd2);
        oldnzxmbds.add(oldnzxmbd3);

        boolean b = sw2(oldnzxmbds);

        System.out.println(b);
    }
    @Test
    @Transactional(rollbackFor = Exception.class)
    public boolean asdqowe(List<Oldnzxmbd> oldnzxmbds){
        try{
            for(Oldnzxmbd oldnzxmbd:oldnzxmbds){
                QueryWrapper<Oldnzxmbd> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("xmmc", oldnzxmbd.getXmmc());
                Oldnzxmbd oldnzxmbd2= oldnzxmbdMapper.selectOne(queryWrapper);
                if(oldnzxmbd2!=null){
                    return false;
                }
                int insert = oldnzxmbdMapper.insert(oldnzxmbd);
                if(!(insert>0)){
                    throw new RuntimeException();
                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }




}
