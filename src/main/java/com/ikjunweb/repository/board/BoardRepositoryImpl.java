package com.ikjunweb.repository.board;

import com.ikjunweb.entity.SearchCondition;
import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.type.MajorType;
import com.ikjunweb.entity.type.SubjectType;
import com.ikjunweb.entity.user.User;
import com.ikjunweb.repository.user.UserRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static com.ikjunweb.entity.board.QBoard.board;
import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryImpl implements BoardCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final UserRepository userRepository;

    @Override
    public List<Board> search(SearchCondition searchCondition) {
        return jpaQueryFactory
                .select(board)
                .where(isSearchable(searchCondition))
                .from(board)
                .orderBy(board.createDate.desc())
                .fetch();
    }

    private BooleanBuilder isSearchable(SearchCondition searchCondition) {
        MajorType majorType = searchCondition.getMajorType();
        SubjectType subjectType = searchCondition.getSubjectType();
        String content = searchCondition.getContent();

        if (majorType == null && subjectType == null && content != null) {
            return titleCt(content);
        } else if (majorType != null && subjectType == null && content == null) {
            return majorEq(majorType);
        } else if (majorType != null && subjectType != null && content == null) {
            return majorEq(majorType).and(subjectEq(subjectType));
        } else if (majorType != null && subjectType == null && content != null) {
            return majorEq(majorType).and(titleCt(content));
        } else {
            return majorEq(majorType).and(subjectEq(subjectType)).and(titleCt(content));
        }
    }

    private BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (Exception e) {
            return new BooleanBuilder();
        }
    }

    private BooleanBuilder titleCt(String content) {
        return nullSafeBuilder(() -> board.title.contains(content));
    }

    private BooleanBuilder majorEq(MajorType majorType) {
        if (majorType == MajorType.COMPUTER) {
            return nullSafeBuilder(() -> board.majorType.eq(MajorType.COMPUTER));
        } else if (majorType == MajorType.SOFTWARE) {
            return nullSafeBuilder(() -> board.majorType.eq(MajorType.SOFTWARE));
        } else {
            return nullSafeBuilder(() -> board.majorType.eq(MajorType.GLOBAL_MEDIA));
        }
    }

    private BooleanBuilder subjectEq(SubjectType subjectType) {
        if (subjectType == SubjectType.PROGRAMMING_1) {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.PROGRAMMING_1));
        } else if (subjectType == SubjectType.PROGRAMMING_2) {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.PROGRAMMING_2));
        } else if (subjectType == SubjectType.COMPUTER_MATH_1) {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.COMPUTER_MATH_1));
        } else if (subjectType == SubjectType.COMPUTER_MATH_2) {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.COMPUTER_MATH_2));
        } else if (subjectType == SubjectType.COMPUTER_CALCULUS) {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.COMPUTER_CALCULUS));
        } else if (subjectType == SubjectType.PROBABILITY_STATISTICS) {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.PROBABILITY_STATISTICS));
        } else if (subjectType == SubjectType.COMPUTER_ENGINEERING) {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.COMPUTER_ENGINEERING));
        } else if (subjectType == SubjectType.PHYSICS) {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.PHYSICS));
        } else if (subjectType == SubjectType.DISCRETE_MATH) {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.DISCRETE_MATH));
        } else if (subjectType == SubjectType.LINUX_SYSTEM) {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.LINUX_SYSTEM));
        } else if (subjectType == SubjectType.ENGINEERING_DESIGN) {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.ENGINEERING_DESIGN));
        } else if (subjectType == SubjectType.ART_TECHNOLOGY) {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.ART_TECHNOLOGY));
        } else {
            return nullSafeBuilder(() -> board.subjectType.eq(SubjectType.MEDIA));
        }
    }
}
