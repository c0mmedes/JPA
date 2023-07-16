package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // Persistence.xml에서 설정해준 name으로 factory를 만들어서 연결
        // 웹 서버가 올라오는 시점에 딱 1개만 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 고객의 요청이 올 때마다 썼다가 버림(em.close)
        // 그래서 쓰레드간에 절대 공유 X(사용하고 버려야됨)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // JPA의 모든 DATA 변경은 트랜잭션 안에서 실행
        // 단순 조회는 트랜잭션 필요 X
        try {
            Member member = new Member();
            member.setUsername("member1");

            em.persist(member);

            // Team Insert
            Team team = new Team();
            team.setName("TeamA");

            // 권장하지않음
            // 다대일 양방향 매핑 권장
            team.getMembers().add(member);

            em.persist(team);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
