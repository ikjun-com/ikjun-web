package com.ikjunweb.repository.board_tag;

import com.ikjunweb.entity.board.BoardTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTagRepository extends JpaRepository<BoardTag, Long>, BoardTagCustomRepository {
}
