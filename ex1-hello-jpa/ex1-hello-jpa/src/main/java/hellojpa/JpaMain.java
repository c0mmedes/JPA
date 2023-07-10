package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            // * insert
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//            em.persist(member);

            // * select
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getId());

            // * delete
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember);

            // * update
            // 객체에서 값만 바꿔도 되는 이유는 JPA가 관리해서 트랜잭션을
            // 커밋하는 시점에 다 체크해서 UPDATE 쿼리를 만들어서 날림 그 이후에 커밋
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
