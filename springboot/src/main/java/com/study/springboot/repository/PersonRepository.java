package com.study.springboot.repository;

import com.study.springboot.entity.Person;
import com.study.springboot.entity.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Cdu
 * @discription:
 * @create 2020-08-26 21:14
 */
public interface PersonRepository extends JpaRepository<Person, String> {
    /**
     * 查询出年龄小于等于22岁的人
     *
     * @param age
     * @return
     */
    List<Person> findAllByPageIsLessThanEqual(Integer age);

    /**
     * 查询出年龄在20-22岁并且性别是男的人
     *
     * @param lowAge
     * @param highAge
     * @param sex
     * @return
     */
    List<Person> findAllByPageBetweenAndPsexEquals(Integer lowAge, Integer highAge, String sex);

    /**
     * 查询出已婚且性别是男的人
     *
     * @param sex
     * @return
     */
    List<Person> findAllByGetmarriedIsTrueAndPsexEquals(String sex);

    /**
     * 根据pname模糊删除一个person数据
     * @param pname
     */
    @Modifying
    @Transactional
    @Query(value = "delete from Person where pname like %:pname%")
    void deleteByName(@Param("pname") String pname);

    /**
     * 使用HQL或者是sql来书写一个查询语句，查询年龄是20-22岁且性别是女的人
     * @return
     */
//    @Query(value = "select * from Person where page between 20 and 22 and psex = '女'", nativeQuery = true)
    @Query(value = "select p from Person p where p.page between 20 and 22 and p.psex = '女'")
    List<Person> findPerson();

    /**
     * 使用spel表达式来完成person表的修改操作
     */
    @Modifying
    @Transactional
    @Query(value = "update person set pname=:#{#person.pname}, psex=:#{#person.psex}, page=:#{#person.page} " +
            "where pid=:#{#person.pid}", nativeQuery = true)
    void updatePerson(@Param("person") Person person);

    /**
     * 联表查询-根据书名查询书籍拥有者
     * @param bname
     * @return
     */
    @Query(value = "select p from Person p inner join Book b on p.pid=b.pid where b.bname=:bname")
    Person findPersonByBname(@Param("bname") String bname);

    //联表查询-根据personid查询person和book
    @Query(value = "select p.pid as pid,p.pname as pname,p.psex as psex,p.getmarried as getmarried," +
            "b.bid as bid,b.bname as bname,b.bprice as bprice from Person p inner join Book b on p.pid=b.pid " +
            "where p.pid=:pid")
    List<PersonInfo> findAllInfo(@Param("pid") String pid);

    @Query(value = "select p.pid as pid,p.pname as pname,p.psex as psex,p.getmarried as getmarried," +
            "b.bid as bid,b.bname as bname,b.bprice as bprice from Person p inner join Book b on p.pid=b.pid " +
            "where p.pid=:pid")
    List<Object> findAllInfo1(@Param("pid") String pid);

    @Query(value = "select p.pid as pid,p.pname as pname,p.psex as psex,p.getmarried as getmarried," +
            "b.bid as bid,b.bname as bname,b.bprice as bprice from Person p inner join Book b on p.pid=b.pid " +
            "where p.pid=:pid")
    List<Map<String,Object>> findAllInfo2(@Param("pid") String pid);

}