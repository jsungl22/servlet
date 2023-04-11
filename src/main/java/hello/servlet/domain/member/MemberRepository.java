package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용
    private static final MemberRepository instance = new MemberRepository();
    public static MemberRepository getInstance() {
        return instance;
    }
    private MemberRepository() {
    }
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    public Member findById(Long id) {
        return store.get(id);
    }
    public List<Member> findAll() {
        // store의 값들을 모두 꺼내서 ArrayList를 새로 만들어서 반환한다
        // 이렇게 한 이유는 new ArrayList에 값을 넣거나 밖에서 조작해도 store에 있는 값들을 건들고 싶지 않아서
        return new ArrayList<>(store.values());
    }
    // 테스트에서만 사용(초기화)
    public void clearStore() {
        store.clear();
    }
}
