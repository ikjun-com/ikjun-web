package com.ikjunweb.repository.board;

import com.ikjunweb.entity.board.BoardLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static com.ikjunweb.entity.board.QBoardLike.boardLike;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BoardLikeRepositoryImpl implements BoardLikeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<BoardLike> exist(Long userId, Long boardId) {
        BoardLike bLike = jpaQueryFactory
                .selectFrom(boardLike)
                .where(boardLike.board.id.eq(boardId),
                        boardLike.user.id.eq(userId))
                .fetchFirst();
        return Optional.ofNullable(bLike);
    }

    @Override
    public long findPostLikeNum(Long boardId) {
        return jpaQueryFactory
                .selectFrom(boardLike)
                .where(boardLike.board.id.eq(boardId))
                .fetchCount();
    }
}
