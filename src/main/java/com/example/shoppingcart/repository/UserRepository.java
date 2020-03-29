package com.example.shoppingcart.repository;

import com.example.shoppingcart.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    // Khi được gắn @Query, thì tên của method không còn tác dụng nữa
    // JPQL
    @Query("select u from User u where u.email = ?1")
    User myCustomQuery(String email);

    // Native SQL
    @Query(value = "select * from user u where u.email = ?1", nativeQuery = true)
    User myCustomQuery2(String email);

    @Query(value = "SELECT * FROM user u WHERE u.name LIKE %:name%", nativeQuery = true)
    Page<User> findUserByName(@Param("name") String name, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE user SET name = ?1, avatar = ?2, phone = ?3, birthday = ?4 WHERE id = ?5", nativeQuery = true)
    void updateProfile(String name, String avatar, String phone, Date birthday, int id);

    @Query(nativeQuery = true, name = "getUserInfo")
    User getUserInfo(int id);

    @Transactional(readOnly = true)
    @Query("select u from User u where u.email = :email")
    Iterable<User> findByUseremail(@Param("email") String email);
}
