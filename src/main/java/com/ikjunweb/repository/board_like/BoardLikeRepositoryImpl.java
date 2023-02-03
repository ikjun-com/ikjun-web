package com.ikjunweb.repository.board_like;

import com.ikjunweb.entity.board.BoardLike;
import com.ikjunweb.repository.user.UserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.ikjunweb.entity.board.QBoardLike.boardLike;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BoardLikeRepositoryImpl implements BoardLikeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final UserRepository userRepository;

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

    @Override
    public List<BoardLike> findBoardByLike(Long userId) {
        return jpaQueryFactory
                .select(boardLike)
                .where(isUserLike(userId))
                .from(boardLike)
                .fetch();
    }

    @Transactional
    private BooleanExpression isUserLike(Long userId) {
        return boardLike.user.id.eq(userId);
    }
}
