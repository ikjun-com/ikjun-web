package com.ikjunweb.requestdto.board;

import com.ikjunweb.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardLikeRequest {
    private User user;
    private Long boardId;
}
