package com.ikjunweb.repository.comment;

import com.ikjunweb.entity.comment.Comment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ikjunweb.entity.comment.QComment.comment;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Comment> findCommentInBoard(Long boardId) {
        return jpaQueryFactory
                .select(comment)
                .where(comment.board.id.eq(boardId))
                .from(comment)
                .orderBy(comment.createDate.desc())
                .fetch();
    }

    @Override
    public long findCommentNum(Long boardId) {
        return jpaQueryFactory
                .select(comment)
                .where(comment.board.id.eq(boardId))
                .from(comment)
                .fetchCount();
    }
}
