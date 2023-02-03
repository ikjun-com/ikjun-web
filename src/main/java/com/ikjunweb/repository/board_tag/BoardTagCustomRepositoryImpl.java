package com.ikjunweb.repository.board_tag;

import com.ikjunweb.entity.board.BoardTag;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static com.ikjunweb.entity.board.QBoardTag.boardTag;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardTagCustomRepositoryImpl implements BoardTagCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardTag> findBoardTag(Long boardId) {
        return jpaQueryFactory
                .select(boardTag)
                .where(boardTag.board.id.eq(boardId))
                .from(boardTag)
                .fetch();
    }
}
